package com.shizy.job.gen.docx.po;

import com.shizy.job.gen.docx.DocxGenerator;
import com.shizy.job.gen.docx.util.FileContentUtils;
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
        base = FileContentUtils.readFileContent(new File(DocxGenerator.templatePath + "table_base.xml"));//填充${tr_base}
    }

    public String toString() {
        if (comments == null) {
            throw new RuntimeException("表[" + tableName + "]没有表注释");
        }
        return base.replace("${tr_base}", content)
                .replace("${tableName}", tableName)
                .replace("${comments}", comments);
    }
}
