package com.shizy.job.txt2bean;

import com.shizy.job.txt2bean.dto.Txt2BeanDto;
import com.shizy.utils.properties.FileContentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Txt2Bean
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2020/11/17 9:18
 */
public class Txt2BeanMain {

    private static final Logger logger = LoggerFactory.getLogger(Txt2BeanMain.class);

    //工程中运行才读的到，打包后不能这么读
    private static String templatePath = "src/main/java/com/shizy/job/txt2bean/input/";
    private static String outputPath = "src/main/java/com/shizy/job/txt2bean/output/";

    public void start() {
        //读取文件内容
        String content = FileContentUtils.readFileContent(new File(templatePath + "input.txt"));

        //解析文件获得列对象
        List<Txt2BeanDto> txt2BeanDtoList = this.txt2BeanDto(content);

        //成select
        this.genSelect(txt2BeanDtoList, outputPath + "select.sql");

        //解析成bean
        this.genBean(txt2BeanDtoList, outputPath + "DemoBean.java");
    }

    private void genBean(List<Txt2BeanDto> txt2BeanDtoList, String outputPath) {
        //todo 使用Velocity模板而不是String

        StringBuilder sb = new StringBuilder();
        sb.append("package com.shizy.job.txt2bean.output;")
                .append("\r\n\r\n")
                .append("public class DemoBean {")
                .append("\r\n\r\n");
        for (Txt2BeanDto txt2BeanDto : txt2BeanDtoList) {
            sb.append("    /**").append("\r\n")
                    .append("    * ").append(txt2BeanDto.getColumnName()).append("\r\n")
                    .append("    */").append("\r\n")
                    .append("    private String ").append(txt2BeanDto.getColumnHump()).append(";").append("\r\n")
                    .append("\r\n");
        }
        sb.append("}");

        String content = sb.toString();
        FileContentUtils.writeFile(new File(outputPath), content);
    }

    private void genSelect(List<Txt2BeanDto> txt2BeanDtoList, String outputPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        for (Txt2BeanDto txt2BeanDto : txt2BeanDtoList) {
            sb.append(txt2BeanDto.getColumn()).append(" ").append(txt2BeanDto.getColumnHump()).append(", \r\n\t");
        }

        String select = sb.substring(0, sb.lastIndexOf(", ")) + " \r\nfrom dual;";
        FileContentUtils.writeFile(new File(outputPath), select);
    }

    private List<Txt2BeanDto> txt2BeanDto(String content) {
        content = content.toLowerCase();

        List<Txt2BeanDto> txt2BeanDtos = new ArrayList<>();
        for (String row : content.split("\r\n")) {
            String[] rowDetail = row.trim().split("\\s+");
            String column = rowDetail[0].trim();
            String columnUnderline = this.getcolumnUnderline(column);
            String columnName = rowDetail[1].trim();
            String columnHump = this.underline2Hump(columnUnderline);
            txt2BeanDtos.add(new Txt2BeanDto(column, columnUnderline, columnName, columnHump));
        }

        return txt2BeanDtos;
    }

    private String getcolumnUnderline(String column) {
        String[] strs = column.split("\\.");
        return strs[strs.length - 1];
    }

    private String underline2Hump(String columnUnderline) {
        StringBuilder hump = new StringBuilder();
        for (String str : columnUnderline.split("_")) {
            hump.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
        }
        return hump.substring(0, 1).toLowerCase() + hump.substring(1);
    }


    public static void main(String[] args) {
        new Txt2BeanMain().start();
    }


}



























