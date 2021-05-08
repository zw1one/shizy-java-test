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
public class WordBase {
    private String content;

    private static String base;

    static {
        base = FileUtils.readFileContent(new File(DocxGenerator.templatePath + "word_base.xml"));//填充${word_content}
    }

    public String toString() {
        return base.replace("${word_content}", content);
    }
}
