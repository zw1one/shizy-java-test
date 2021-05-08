package com.shizy.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

    public static void main(String[] args) {

        SimpleDateFormat sf  = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sf.format(new Date("Fri Nov 20 00:00:00 CST 2020")));
    }

    @Test
    public void zzz(){
        System.out.println("Hello world!");



    }
}
