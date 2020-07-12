package com.shizy.happy.lol.score;

import com.shizy.happy.lol.fetter.EffectiveFetter;
import com.shizy.happy.lol.hero.Hero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreResult {

    private List heros;
    private List effectiveFetters;
    private float score;

    public static void sortSR(List heros, List effectiveFetters) {
        heros.sort((o1, o2) -> {
            Hero h1 = (Hero) o1;
            Hero h2 = (Hero) o2;
            if (h2.getCost() != h1.getCost()) {
                return Integer.compare(h2.getCost(), h1.getCost());
            }
            return h1.getName().compareTo(h2.getName());
        });

        effectiveFetters.sort((o1, o2) -> Integer.compare(((EffectiveFetter) o2).getHeroNum(), ((EffectiveFetter) o1).getHeroNum()));
    }

    public void show() {
        showHeros(heros);
        showEffective(effectiveFetters);
        System.out.println(score);
    }

    private void showHeros(List<Hero> heros) {
        for (Hero hero : heros) {
            System.out.println(hero);
        }
    }

    private void showEffective(List<EffectiveFetter> effectiveFetters) {
        for (EffectiveFetter effective : effectiveFetters) {
            System.out.print(effective.getHeroNum() + effective.getFetterName() + "  ");
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreResult that = (ScoreResult) o;
        return Float.compare(that.score, score) == 0 &&
                listEquals(heros, that.heros) &&
                listEquals(effectiveFetters, that.effectiveFetters);
    }

    private boolean listEquals(List l1, List l2) {
        for (Object o : l2) {
            if (!l1.contains(o)) {
                return false;
            }
        }
        return true;
    }

}

















