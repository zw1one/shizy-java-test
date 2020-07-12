package com.shizy.happy.lol.fetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fetter {

    private String name;

    private int[] rank;

    /**
     * @return {4, 2, 2} 指4个英雄，羁绊等级为2，羁绊共2级
     */
    public Object[] getFetterCnt(int cnt) {
        for (int i = 0; i < rank.length; i++) {
            if (i == rank.length - 1) {
                if (rank[i] <= cnt) {
                    return new Object[]{rank[i], i + 1, rank};
                }
            }

            if (rank[i] <= cnt && cnt <= rank[i + 1]) {
                return new Object[]{rank[i], i + 1, rank};
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Fetter{" +
                "name='" + name + '\'' +
                '}';
    }
}
