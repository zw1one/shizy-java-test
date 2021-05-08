package com.shizy.job.ly.docx.po;

import com.shizy.job.ly.docx.DocxGenerator;
import com.shizy.utils.properties.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableBase {
    private String content;
    private String tableName;
    private String comments;

    private static String base;

    static {
        base = FileUtils.readFileContent(new File(DocxGenerator.templatePath + "table_base.xml"));//填充${tr_base}
    }

    public String toString() {
        if (comments == null) {
            throw new RuntimeException("表[" + tableName + "]没有表注释");
        } else if (comments.equals(tableName)) {
            System.out.println("表[" + tableName + "]没有表注释");
        }

        return base.replace("${tr_base}", content)
                .replace("${tableName}", tableName)
                .replace("${comments}", comments);
    }
}
