package com.shizy.job.midea.genplan;

import com.shizy.utils.date.CalculateWorkDaysUtil;
import com.shizy.utils.file.FileUtils;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * GenPlan
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2021/5/18 19:39
 */
public class GenPlan {

    private String workDay = "68";
    private String workEmployeeNum = "5";
    private String[] worker = {
            "莫梁美",
            "黄华冬",
            "严瑜龙",
            "陈炯圳",
            "石中玉",
//            "爱玛开发A",
    };

    public static void main(String[] args) {
        new GenPlan().genPlan();
    }

    @SneakyThrows
    public void genPlan() {

        String content = FileUtils.readFileContent(new File("job/src/main/java/com/shizy/job/midea/genplan/input.txt"));

        //获取日期相减的计划人天
        List<String> planDays = new ArrayList();
        String[] twoDates = content.split("\\r\\n");
        for (String twoDate : twoDates) {
            if (twoDate.trim().isEmpty()) {
                planDays.add(" ");
            } else {
                String[] dateStartAndEne = twoDate.split("\\t");
                int dateSub = CalculateWorkDaysUtil.getworkDays(dateStartAndEne[0], dateStartAndEne[1], "yyyy/MM/dd");
                dateSub = dateSub == 0 ? 1 : dateSub;//dateSub不能为0，最小为1
                planDays.add(String.valueOf(dateSub));
            }
        }

        //计划人天匹配上实际工作日
        planDays = processWorkDay(planDays);

        long planDaySum = 0L;
        for (String planDay : planDays) {
            if (planDay.trim().isEmpty()) {
                continue;
            }
            planDaySum += Long.parseLong(planDay);
        }
        System.out.println(planDaySum);
        System.out.println(Long.parseLong(workDay) * Long.parseLong(workEmployeeNum));

        //计划人天匹配开发人员
        List<String> planDayAndWorkers = processWorkEmployee(planDays);

        //输出
        System.out.println();
        System.out.println();
        System.out.println();
        for (String planDayAndWorker : planDayAndWorkers) {
            if (planDayAndWorker.trim().isEmpty()) {
                System.out.println();
                continue;
            }
            String str[] = planDayAndWorker.split("\\$");
            System.out.println(str[0] + "\t" + str[1]);
        }
    }

    private List<String> processWorkEmployee(List<String> planDays) {
        // 一个人排5天或5天以上，然后排下一个人。
        // 取下一个人逻辑：当前工作量最少的人，并且不能是前一个人

        List<String> planDayAndWorkers = new ArrayList<>();
        long[] workerDays = new long[worker.length];//按下标对应每个人的工作量
        int cache5 = 0;//当前人 这轮排5天或5天以上工作量 已经排了几天
        int currentWorker = 0;//当前人

        for (String planDay : planDays) {
            if (planDay.trim().isEmpty()) {
                planDayAndWorkers.add(planDay);
                continue;
            }
            Long planDayValue = Long.parseLong(planDay);

            if (cache5 >= 5) {
                cache5 = 0;
                currentWorker = getCurrentWorker(workerDays, currentWorker);
                //这里不continue，而是换人继续排
//                continue;
            }

            workerDays[currentWorker] += planDayValue;
            cache5 += planDayValue;
            planDayAndWorkers.add(planDay + "$" + worker[currentWorker]);
        }

        return planDayAndWorkers;
    }

    private int getCurrentWorker(long[] workerDays, int currentWorker) {
        //取最小值
        int minIndex = 0;
        for (int i = 0; i < workerDays.length; i++) {
            //是上一个人 跳过
            if (i == currentWorker) {
                continue;
            }
            if (workerDays[i] < workerDays[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private List<String> processWorkDay(List<String> planDays) {
        long planDaySum = 0L;
        for (String planDay : planDays) {
            if (planDay.trim().isEmpty()) {
                continue;
            }
            planDaySum += Long.parseLong(planDay);
        }
        Long workDayValue = Long.parseLong(workDay);
        Long workEmployeeValue = Long.parseLong(workEmployeeNum);

        long workDaySub = planDaySum - workDayValue * workEmployeeValue;//人天计划与每月实际工作日之差

        int workDaySubMinus = workDaySub > 0 ? 1 : -1;//workDaySub的正负号
        workDaySub = Math.abs(workDaySub);

        /**开始计算 人天计划与每月实际工作日之差*/

        //如果人天计划与每月实际工作日之差，可以每项工作添加/删除k天，则添加/删除
        int k = (int) (workDaySub / planDays.size());
        planDays = planDaysCalK(planDays, k, workDaySubMinus);

        //如果人天计划与每月实际工作日之差，可以在所有工作项中，一共添加/删除y天，则添加/删除
        int y = (int) (workDaySub % planDays.size());
        planDays = planDaysCalY(planDays, y, workDaySubMinus);

        return planDays;
    }

    private List<String> planDaysCalK(List<String> planDays, int k, int workDaySubMinus) {
        if (k == 0) {
            return planDays;
        }

        List<String> planDays2 = new ArrayList<>();
        for (String planDay : planDays) {
            if (planDay.trim().isEmpty()) {
                planDays2.add(planDay);
                continue;
            }
            planDays2.add(String.valueOf(Long.parseLong(planDay) + k * workDaySubMinus * -1));
        }
        return planDays2;
    }

    private List<String> planDaysCalY(List<String> planDays, int y, int workDaySubMinus) {
        if (y == 0) {
            return planDays;
        }

        List<String> planDays2 = new ArrayList<>();
        for (String planDay : planDays) {
            if (planDay.trim().isEmpty()) {
                planDays2.add(planDay);
                continue;
            }

            //y用完了，或者当前操作之后等于0，都跳过
            if (y == 0 || (Long.parseLong(planDay) + workDaySubMinus * -1) == 0) {
                planDays2.add(planDay);
                continue;
            }
            y--;
            planDays2.add(String.valueOf(Long.parseLong(planDay) + workDaySubMinus * -1));
        }
        return planDays2;
    }

//    private int dateSub(String date2Str, String date1Str) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//
//        Date date2 = format.parse(date2Str);
//        Date date1 = format.parse(date1Str);
//        long diffDays = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
//
//        return (int) diffDays;
//    }
}


;