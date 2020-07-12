package com.shizy.happy.lol.fetter;

public class Ninja extends Fetter {

    public Ninja(String name, int[] rank) {
        super(name, rank);
    }

    public Object[] getFetterCnt(int cnt) {
        if (cnt == 1) {
            return new Object[]{1, 1, getRank()};
        } else if (cnt == 4) {
            return new Object[]{4, 2, getRank()};
        }
        return null;
    }
}
