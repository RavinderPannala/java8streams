package com.java8.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.java8.streams.Main.getEmployee;

public class POCJava8 {
    public static void main(String[] args) {
        List<Employee> employees = getEmployee();
        Map<String, Employee> employeeMap = employees.stream().collect(Collectors.toMap(Employee::getName, e -> e));
        Map<String, String> employeeMap1 = employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getDepartment));

        //Sort the hashMap Keys in Desc Order?
        LinkedHashMap<String, Employee> collect = employeeMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
       // System.out.println(collect);
        //Sort the hashMap Keys in Asc Order?
        LinkedHashMap<String, Employee> collect1 = employeeMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        //System.out.println(collect1);
        //Sort the HashMap values in desc order?
        LinkedHashMap<String, String> collect2 = employeeMap1.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        //System.out.println(collect2);
        //Sort the HashMap values in ASC order?
        LinkedHashMap<String, String> collect3 = employeeMap1.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        //System.out.println(collect3);

        //Sort the Employee by sal in desc?
        List<Employee> collect4 = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
        System.out.println(collect4);

        //Get the Employee whose salary is maximum?
        Employee first = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(1).findFirst().get();
        System.out.println(first);

        //Get The Maximum salary of Employee in each department ?
        Map<String, Optional<Employee>> collect5 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

        Map<String, Employee> collect6 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), Optional::get)));
        System.out.println(collect6);

        //Duplicate Number From a List?
        List<Integer> list = Arrays.asList(1,3,4,5,3,4,2,1);
        Map<Integer, Long> collect7 = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect7);

        Set<Integer> collect8 = list.stream().filter(i -> Collections.frequency(list, i) > 1).collect(Collectors.toSet());
        System.out.println(collect8);

        //Segregation of employees list into map of their department as key and value as number of employee's  as a value of that department
        Map<String, Long> collect9 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(collect9);
    }
}