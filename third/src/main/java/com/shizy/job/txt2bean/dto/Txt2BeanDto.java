package com.shizy.job.txt2bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Txt2BeanDto
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2020/11/17 9:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Txt2BeanDto {

    /**
     * 列
     * t2.account_no
     */
    private String column;

    /**
     * 列-下划线
     * account_no
     */
    private String columnUnderline;

    /**
     * 列-中文
     * 账户
     */
    private String columnName;

    /**
     * 列-驼峰
     * accountNo
     */
    private String columnHump;
}
