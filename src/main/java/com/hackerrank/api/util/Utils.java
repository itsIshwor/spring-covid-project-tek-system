package com.hackerrank.api.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
	public static final List<String> validBy = Arrays.asList("active", "recovered", "death");

	public static boolean isValidPreCheckBy(String by) {
		return validBy.contains(by);
	}
}