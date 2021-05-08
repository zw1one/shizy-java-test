package com.shizy.fun.lol.hero;

import com.shizy.fun.lol.fetter.Fetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {

    private String name;

//    private String title;

    private int cost;

    private List<Fetter> fetters;


}
