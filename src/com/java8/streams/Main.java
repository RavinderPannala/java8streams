package com.java8.streams;

import com.sun.xml.internal.ws.util.QNameMap;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Employee> employeeList = getEmployee();

       /* Employee[] employees = employeeList.stream().toArray(Employee[]::new);
        Map<String, Long> collect = employeeList.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.counting()));
        employeeList.stream().count();
        employeeList.stream().forEach(System.out::println);
        employeeList.stream().sorted(Comparator.comparing(Employee::getName)).forEach(System.out::println);
        employeeList.stream().allMatch(e -> e.getName().matches("Ravi"));
        boolean ravi = employeeList.stream().noneMatch(e -> e.getName().equals("Ravi"));
        employeeList.stream().distinct().forEach(System.out::println);
        employeeList.stream().filter(e -> e.getName().equals("Ravi")).forEach(System.out::println);*/

        //How many male and female employees are there in the organization?
        Map<String, Long> collect = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        //System.out.println(collect);

        // Print the name of all departments in the organization?
        List<String> collect1 = employeeList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
        //System.out.println(collect1);

        //Average age of Male and Female in Organization
        Map<String, Double> collect2 = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        //System.out.println(collect2);

        //Highest Paid Salary Employee
        Employee employee = employeeList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).findFirst().get();
        Employee employee1 = employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get();
        //System.out.println(employee);

        //Second Highest Paid Salary Employee
        Employee secondHighestEmployee = employeeList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(2).skip(1).findFirst().get();
        //System.out.println(secondHighestEmployee);

        //get All the Employee Names Who Joined after 2015
        List<String> collect3 = employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName).collect(Collectors.toList());
        //System.out.println(collect3);

        //Get number of Employess in each Department
        Map<String, Long> collect4 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        //System.out.println(collect4);

        //What is the average salary in each Department
        Map<String, Double> collect5 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        //System.out.println(collect5);

        //Get details of Yougest Employee in Product Development Team
        Employee productDevelopment = employeeList.stream()
                .filter(e -> e.getDepartment().equals("Product Development") && e.getGender().equals("Male"))
                .max(Comparator.comparingInt(Employee::getAge).reversed()).get();
        //System.out.println(productDevelopment);

        //Who has the most Experience employee in the Organization?
        Employee employee2 = employeeList.stream().min(Comparator.comparingInt(Employee::getYearOfJoining)).get();
        //System.out.println(employee2);

        //How many Male and Female employees available in sales and Marketing deparment?
        Map<String, Long> salesAndMarketing = employeeList.stream().filter(x -> x.department.equals("Sales And Marketing"))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        //System.out.println(salesAndMarketing);

        //What is the average salary in mala and Female employee in organization?
        Map<String, Double> avargeSalary = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        // System.out.println(avargeSalary);

        //List down all employees in each department in organization?
        Map<String, List<Employee>> collect6 = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        //System.out.println(collect6);

        //What is the average salary and Max salary in whole organization?
        DoubleSummaryStatistics collect7 = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        //System.out.println(collect7);

        //Separate the Employee who are younger and equal to 25 Years from those employees who are older than 25 years?
        Map<Boolean, List<Employee>> collect8 = employeeList.stream().collect(Collectors.partitioningBy(e -> e.age >= 25));
        //System.out.println(collect8);

        //Who is the oldest employee in the organization? What is his age and which department he belongs to?
        Employee employee3 = employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).get();
        //System.out.println(employee3);

        //Sort Employee Map by Key
        Map<String, String> employeeMap = employeeList.stream().collect(Collectors.toMap(Employee::getName, Employee::getDepartment));
        LinkedHashMap<String, String> collect9 = employeeMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        LinkedHashMap<String, String> collect10 = employeeMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        //System.out.println(collect10);

        //CollectingAnd then
        List<String> collect11 = employeeList.stream().map(Employee::getName)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        System.out.println(collect11);
    }

    private static List<Employee> getEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        return employeeList;
    }
}