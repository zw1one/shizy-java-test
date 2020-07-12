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
public class TcBase {

    private String content;

    private static String base;

    static {
        base = FileContentUtils.readFileContent(new File(DocxGenerator.templatePath + "tc_base.xml"));//填充${tc_value}
    }

    public String toString() {
        return base.replace("${tc_value}", content);
    }
}
