package com.shizy.job.midea.mysqldump;

import com.shizy.utils.file.FileUtils;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GenPlan
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2021/5/18 19:39
 */
public class GenMysqlDump {


    public static void main(String[] args) {
        new GenMysqlDump().genPlan();
    }

    @SneakyThrows
    public void genPlan() {
        String content = FileUtils.readFileContent(new File("job/src/main/java/com/shizy/job/midea/mysqldump/input.txt"));

        String[] rows = content.trim().split("\\r\\n");
        List<String> rowList = new ArrayList<>(Arrays.asList(rows));

        List<Table> tables = new ArrayList<>();
        rowList.forEach(row -> {
            tables.add(Table.builder().db(row.split("\\t")[0]).table(row.split("\\t")[1]).build());
        });

        Map<String, List<Table>> groupMap = tables.stream().collect(Collectors.groupingBy(Table::getDb));
        groupMap.forEach((s, tables1) -> {
            StringBuilder sb = new StringBuilder();
            sb.append("mysqldump -h10.18.45.100 -p3306 -u\"appuser\" -p\"Apps^Mjb123\" --single-transaction ")
                    .append(s);
            tables1.forEach(table -> {
                sb.append(" " + table.getTable());
            });
            sb.append(" | mysql -h10.74.156.36 -p3306 -uroot -p'123456' -f ").append(s);
            System.out.println(sb);
        });
    }
}

@Data
@Builder
class Table {
    private String db;
    private String table;
}