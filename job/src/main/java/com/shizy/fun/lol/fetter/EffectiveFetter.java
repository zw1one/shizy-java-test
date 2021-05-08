package com.shizy.fun.lol.fetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EffectiveFetter {

    private String fetterName;

    private int heroNum;

    private int fetterCrtRank;

    private int[] fetterRank;//羁绊共几级


}
