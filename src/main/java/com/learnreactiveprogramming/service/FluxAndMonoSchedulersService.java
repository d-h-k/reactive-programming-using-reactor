package com.learnreactiveprogramming.service;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoSchedulersService {

    static List<String> namesList = List.of("alex", "ben", "chloe");
    static List<String> namesList1 = List.of("adam", "jill", "jack");
    static List<String> myList = List.of("alex,ben,choi");

    private String upperCase(String name) {
        delay(1000);
        return name.toUpperCase();
    }

}
