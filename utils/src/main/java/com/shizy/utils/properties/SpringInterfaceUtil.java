package com.shizy.utils.properties;

import com.shizy.utils.file.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * urlTestUtil 获取spring项目中的接口信息，及代码上的注释信息
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2020/12/24 11:20
 */
public class SpringInterfaceUtil {

    public static void getInterfaceInfo(WebApplicationContext applicationContext) {

        String[] paths = {
                "E:\\AszyIdea\\gfp.gceb\\gfp.gceb.account.web\\src\\main\\java\\com\\midea\\jr\\gfp\\gceb\\account",
                "E:\\AszyIdea\\gfp.gceb\\gfp.gceb.admin.web\\src\\main\\java\\com\\midea\\jr\\gfp\\gceb\\admin",
                "E:\\AszyIdea\\gfp.gceb\\gfp.gceb.base.web\\src\\main\\java\\com\\midea\\jr\\gfp\\gceb\\base",
                "E:\\AszyIdea\\gfp.gceb\\gfp.gceb.bill.web\\src\\main\\java\\com\\midea\\jr\\gfp\\gceb\\bill",
                "E:\\AszyIdea\\gfp.gceb\\gfp.gceb.pay.web\\src\\main\\java\\com\\midea\\jr\\gfp\\gceb\\pay",
                "E:\\AszyIdea\\gfp.gceb\\gfp.gceb.portal.web\\src\\main\\java\\com\\midea\\jr\\gfp\\gceb",
                "E:\\AszyIdea\\gfp.gceb\\gfp.gceb.sett.web\\src\\main\\java\\com\\midea\\jr\\gfp\\gceb\\sett",
        };

        //从spring bean中获取所有接口url信息
        List<Map<String, String>> urlList = getUrlList(applicationContext);

        //过滤不需要的接口
        urlList = trimUrlList(urlList);

        //从url信息中读取对应.java文件，获得方法上的注释，存入urlList
        addComment2List(urlList, paths, "gceb");

        //将urlList输出到txt文件，然后自行导入到数据库方便使用
        output2File(urlList, "D:\\MyData\\shizy19\\Desktop\\zzz.txt");
    }

    private static List<Map<String, String>> trimUrlList(List<Map<String, String>> urlList) {
        return urlList.stream().filter(map -> {
            if (!map.containsKey("type") || map.get("type").equals("null")) {
                return false;
            }
            if (!map.get("className").contains("gfp.gceb.account") &&
                    !map.get("className").contains("gfp.gceb.admin") &&
                    !map.get("className").contains("gfp.gceb.base") &&
                    !map.get("className").contains("gfp.gceb.bill") &&
                    !map.get("className").contains("gfp.gceb.pay") &&
                    !map.get("className").contains("gfp.gceb.portal") &&
                    !map.get("className").contains("gfp.gceb.sett")) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
    }

    private static List<Map<String, String>> getUrlList(WebApplicationContext applicationContext) {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> map1 = new HashMap<String, String>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            //获取当前方法所在类名
            Class<?> bean = method.getBeanType();
            //使用反射获取当前类注解内容
//			Api api = bean.getAnnotation(Api.class);
            RequestMapping requestMapping = bean.getAnnotation(RequestMapping.class);
            String[] value = requestMapping.value();
            if (value.length != 0) {
                map1.put("parent", value[0]);
            } else {
                System.out.println();
            }
            //获取方法上注解以及注解值
//			ApiOperation methodAnnotation = method.getMethodAnnotation(ApiOperation.class);
//			String privilegeName = methodAnnotation.notes();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                map1.put("url", url);
            }
            map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            map1.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                map1.put("type", requestMethod.toString());
            }
            list.add(map1);
        }
        return list;
    }

    private static void addComment2List(List<Map<String, String>> urlList, String[] paths, String sys) {

        //从urlListlist中取要读取的文件，distinct，然后存到fileList
        Map<String, String> classNameMap = cacheFile(urlList, paths);

        //遍历urlList，根据className、method，从文件文本中截取comment，存入urlList
        for (Map<String, String> map : urlList) {
            String className = map.get("className");
            String method = map.get("method");
            String fileContent = classNameMap.get(className);
            String comment = getMethodComment(method, fileContent, className);
            map.put("comment", comment);
            map.put("sys", sys);

            String[] split = className.split("\\.");
            map.put("classSimpleName", split[split.length - 1]);
        }
    }

    private static String getMethodComment(String method, String fileContent, String className) {
        String key = " " + method + "(";
        if (fileContent.split(" " + method + "\\(").length > 2) {
//            throw new RuntimeException("类[" + className + "]搜索关键字[" + key + "]，结果大于1条");
            return null;
        }

        //读取注释的范围：搜索的方法名到上一个'}'/';'处
        int rangeEnd = fileContent.indexOf(key);
        if (rangeEnd == -1) {
//            throw new RuntimeException("类[" + className + "]搜索关键字[" + key + "]，结果为0条");
            return null;
        }
        int rangeStart = fileContent.substring(0, rangeEnd).lastIndexOf("}");//最后一个'{'
        if (rangeStart == -1) {
            rangeStart = fileContent.substring(0, rangeEnd).lastIndexOf(";");//最后一个';'
        }
        String commentRange = fileContent.substring(rangeStart, rangeEnd);

        //在range中用正则匹配出注释内容
        String commentOrgin = null;//原本的注释
        Matcher matcher = Pattern.compile("/\\*(\\s|.)*?\\*/").matcher(commentRange);
        if (matcher.find()) {
            commentOrgin = matcher.group(0);
        } else {
            //注释不存在/匹配失败
//            throw new RuntimeException("类[" + className + "]搜索关键字[" + key + "]，注释正则匹配失败");
            return null;
        }

        //取/** 后面下一行的字
        String commentTrim = null;//处理过后的注释，不带@Param这种
        Matcher matcher2 = Pattern.compile("/\\*\\*\\s*\\*(\\s|.)*?\\*").matcher(commentOrgin);
        if (matcher2.find()) {
            commentTrim = matcher2.group(0);
        } else {
            //注释不存在/匹配失败
//            throw new RuntimeException("类[" + className + "]搜索关键字[" + key + "]，注释正则匹配失败");
            return null;
        }
        commentTrim = commentTrim.replaceAll("/", "")
                .replaceAll("\\*", "")
                .replaceAll("@Description:", "")
                .trim();

        return commentTrim;
    }

    /**
     * sss
     *
     * @param urlList
     * @param paths
     * @return
     */
    private static Map<String, String> cacheFile(List<Map<String, String>> urlList, String[] paths) {
        List<String> classNameList = urlList.stream().map(stringStringMap -> stringStringMap.get("className")).distinct().collect(Collectors.toList());
        Map<String, String> classNameMap = new HashMap();

        for (String className : classNameList) {
            String[] split = className.split("\\.");
            String classSimpleName = split[split.length - 1] + ".java";

            List<File> result = new ArrayList<>();
            for (String path : paths) {
                List<File> result0 = FileUtils.searchFileByKeyWord(path, classSimpleName);
                if (result0.size() != 0) {
                    result.addAll(result0);
                }
            }

            if (result.size() == 0) {
                throw new RuntimeException("文件路径[" + Arrays.toString(paths) + "]下，找不到文件[" + classSimpleName + "]");
            } else if (result.size() != 1) {
                throw new RuntimeException("文件路径[" + Arrays.toString(paths) + "]下，文件[" + classSimpleName + "]存在多个");
            }

            classNameMap.put(className, FileUtils.readFileContent(result.get(0)));
        }
        return classNameMap;
    }

    private static void output2File(List<Map<String, String>> list, String outputFilePath) {
        StringBuilder sb = new StringBuilder();
        for (Map map : list) {
            sb.append(map.get("parent")).append("\t")
                    .append(map.get("method")).append("\t")
                    .append(map.get("className")).append("\t")
                    .append(map.get("type")).append("\t")
                    .append(map.get("url")).append("\t")
                    .append(map.get("sys")).append("\t")
                    .append(map.get("comment")).append("\t")
                    .append(map.get("classSimpleName")).append("\t")
                    .append("\r\n");
        }

        FileUtils.writeFile(new File(outputFilePath), sb.toString());
    }

}
























