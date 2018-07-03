package com.ListToMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {
	public static void main(String[] args) {

		List<Student> list = new ArrayList<>();
		list.add(new Student(1, "linh", 80));
		list.add(new Student(2, "tai", 900));
		list.add(new Student(3, "cuong", 12));
		list.add(new Student(4, "lan", 200));
		list.add(new Student(5, "hoang", 1));

		// key = id, value - total
		Map<Integer, String> result1 = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));

		System.out.println("Result 1 : " + result1);

		// key = name, value - total
		Map<String, Long> result2 = list.stream().collect(Collectors.toMap(Student::getName, Student::gettotal));

		System.out.println("Result 2 : " + result2);

		// Same with result1, just different syntax
		// key = id, value = name
		Map<Integer, String> result3 = list.stream().collect(Collectors.toMap(x -> x.getId(), x -> x.getName()));

		System.out.println("Result 3 : " + result3);
	}
}
