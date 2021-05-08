package com.shizy.job.json2map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shizy.utils.properties.FileContentUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Json2Map sit菜单权限接口json数据，转数据库格式
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2020/12/1 16:31
 */
public class Json2Map {


    public static void main(String[] args) {

        String content = FileContentUtils.readFileContent(new File("src/main/java/com/com.shizy/job/json2map/input.txt"));

        JSONObject input = JSON.parseObject(content);
        List data = (JSONArray) input.get("data");

        List filterData = (List) data.parallelStream().filter(o -> {
            Map record = (Map) o;
            if ("229dbcb425164cbdbc81eb87b6701945".equals(record.get("id"))) {
                return true;
            }
            if ("4e7473faae6b4079a603426f048e87f6".equals(record.get("id"))) {
                return true;
            }
            if ("51c9d142d2c64f77837ec3b2ff84dd2e".equals(record.get("id"))) {
                return true;
            }
            if ("bb53243c2f424d998edf464cffa3158b".equals(record.get("id"))) {
                return true;
            }
            return false;
        }).distinct().collect(Collectors.toList());


        filterData.forEach(o -> {
            Map record = (Map) o;
            System.out.print(record.get("id") + "\t");
            System.out.print(record.get("name") + "\t");
            System.out.print(record.get("enName") + "\t");
            System.out.print(record.get("aliasName") + "\t");
            System.out.print(record.get("langKey") + "\t");
            System.out.print("1" + "\t");
            System.out.print(record.get("uri") + "\t");
            System.out.print(record.get("parentId") + "\t");
            System.out.print("1" + "\t");
            System.out.print("1" + "\t");
            System.out.print(record.get("icoinUrl") + "\t");
            System.out.print("1" + "\t");
            System.out.print("monitor4a" + "\t");
            System.out.print("2018-04-03 17:06:40" + "\t");
            System.out.print("monitor4a" + "\t");
            System.out.print("2018-04-03 17:06:40" + "\t");
            System.out.print("code" + "\t");
            System.out.print("2" + "\t");
            System.out.print(record.get("seqNum") + "\t");

            System.out.println();
        });


        System.out.println();


    }


}
