package com.java8.streams;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeMap {
    public static void main(String[] args) {

        //StreamConcat();
        streamPipiline();
    }

    private static void mergeMap() {
        //Map-1
        HashMap<String, Integer> subjectToStudentCountMap1 = new HashMap<>();
        subjectToStudentCountMap1.put("Maths", 45);
        subjectToStudentCountMap1.put("Physics", 57);
        subjectToStudentCountMap1.put("Chemistry", 52);
        subjectToStudentCountMap1.put("History", 41);
        //Map-2
        HashMap<String, Integer> subjectToStudentCountMap2 = new HashMap<>();
        subjectToStudentCountMap2.put("Economics", 49);
        subjectToStudentCountMap2.put("Maths", 42);
        subjectToStudentCountMap2.put("Biology", 41);
        subjectToStudentCountMap2.put("History", 55);

        HashMap<String, Integer> subjectToStudentCountMap3 = new HashMap<>(subjectToStudentCountMap1);
        subjectToStudentCountMap2.forEach((key, value) -> subjectToStudentCountMap3.merge(key, value, (v1, v2) -> v1 + v2));
        System.out.println(subjectToStudentCountMap3);
    }

    public static void StreamConcat() {
        //Map-1
        HashMap<String, Integer> subjectToStudentCountMap1 = new HashMap<>();
        subjectToStudentCountMap1.put("Maths", 45);
        subjectToStudentCountMap1.put("Physics", 57);
        subjectToStudentCountMap1.put("Chemistry", 52);
        subjectToStudentCountMap1.put("History", 41);
        //Map-2
        HashMap<String, Integer> subjectToStudentCountMap2 = new HashMap<>();
        subjectToStudentCountMap2.put("Economics", 49);
        subjectToStudentCountMap2.put("Maths", 42);
        subjectToStudentCountMap2.put("Biology", 41);
        subjectToStudentCountMap2.put("History", 55);

        HashMap<String, Integer> collect = Stream.concat(subjectToStudentCountMap1.entrySet().stream(), subjectToStudentCountMap2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1 > v2 ? v1 : v2, HashMap::new));
        System.out.println(collect);
    }

    public static void streamPipiline() {
        //Map-1
        HashMap<String, Integer> subjectToStudentCountMap1 = new HashMap<>();
        subjectToStudentCountMap1.put("Maths", 45);
        subjectToStudentCountMap1.put("Physics", 57);
        subjectToStudentCountMap1.put("Chemistry", 52);
        subjectToStudentCountMap1.put("History", 41);
        //Map-2
        HashMap<String, Integer> subjectToStudentCountMap2 = new HashMap<>();
        subjectToStudentCountMap2.put("Economics", 49);
        subjectToStudentCountMap2.put("Maths", 42);
        subjectToStudentCountMap2.put("Biology", 41);
        subjectToStudentCountMap2.put("History", 55);

        HashMap<String, Integer> collect = subjectToStudentCountMap2.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1 < v2 ? v1 : v2, () -> new HashMap<>(subjectToStudentCountMap1)));
        System.out.println(collect);
    }
}
