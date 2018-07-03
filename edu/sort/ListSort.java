package com.edu.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSort {
	public static void main(String[] args) {
		List<Integer> number = new ArrayList<Integer>();
		number.add(5);
		number.add(8);
		number.add(2);
		number.add(3);
		number.add(1);
		Collections.sort(number);
		number.forEach(a -> System.out.println(a));
	}
}