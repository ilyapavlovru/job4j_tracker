package ru.job4j.collection;

import java.util.*;

public class Departments {

    public static List<String> fillGaps(List<String> deps) {
        HashSet<String> tmp = new HashSet<>();
        for (String value : deps) {
            StringBuilder start = new StringBuilder();
            for (String el : value.split("/")) {
                tmp.add(start + el);
                start.append(el).append("/");
            }
        }
        List<String> rst = new ArrayList<>(tmp);
        Collections.sort(rst);
        return rst;
    }

    public static List<String> sortAsc(List<String> orgs) {
        Collections.sort(orgs);
        return orgs;
    }

    public static void sortDesc(List<String> orgs) {
        Collections.sort(orgs, new DepDescComp());
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("K1/SK1");
        List<String> result = Departments.fillGaps(input);
        List<String> resultSrt = Departments.sortAsc(result);

        for (String s : resultSrt) {
            System.out.println(s);
        }

        System.out.println("---");

        List<String> input2 = Arrays.asList(
                "K1/SK1",
                "K1/SK2",
                "K1/SK1/SSK1",
                "K1/SK1/SSK2",
                "K2",
                "K2/SK1/SSK1",
                "K2/SK1/SSK2");
        List<String> result2 = Departments.fillGaps(input2);

        List<String> resultSrt2 = Departments.sortAsc(result2);

        for (String s : resultSrt2) {
            System.out.println(s);
        }

        System.out.println("---");

        Departments.sortDesc(result2);
        for (String s : result2) {
            System.out.println(s);
        }
    }
}