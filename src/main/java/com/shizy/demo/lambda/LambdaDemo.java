package com.shizy.demo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaDemo {

    public static void main(String[] args) {
//
        List<?> data = Arrays.asList("a", "b", "c", "d", "e");

        List list1 = data.stream().map(e -> {
            return e + "" + e;
        }).collect(Collectors.toList());



        System.out.println(list1);
    }


}








