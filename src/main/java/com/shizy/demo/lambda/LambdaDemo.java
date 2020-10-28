package com.shizy.demo.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LambdaDemo {

    @Test
    public void lambdaDemo1() {

        /**
         * findFirst
         * 用一个条件，去list中获取匹配项，得到其po
         */

        List<Archer> list = new ArrayList<>();
        list.add(new Archer("ashe", "ice archer", 67));
        list.add(new Archer("ezreal", "exlporer", 68));
        list.add(new Archer("vayne", "night hunter", 66));

        String findKey = "vayne";
        Archer archer = list.stream().filter(po -> findKey.equals(po.getName()))
                .findFirst().orElse(null);
        String archerTitle = list.stream().filter(po -> findKey.equals(po.getName()))
                .findFirst().map(Archer::getTitle).orElse(null);
    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Archer {
    private String name;
    private String title;
    private int attack;
}