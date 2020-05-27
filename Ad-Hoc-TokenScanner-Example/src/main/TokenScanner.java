package main;

import java.util.*;

public class TokenScanner {

	public static String[] keyword = { "if", "else", "for", "while", "return", "&&", "||" };
	public static char[] symbol = "+-*/=<>()[]{}.;,".toCharArray();
	public static char[] digit = "0123456789".toCharArray();
	public static char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public static char[] charsToSkip = { '\n', '\r', '\t', ' ' }; // An array of characters to skip while scanning.

	public static List<String> scanner(String input) {
		List<String> results = new ArrayList<>();

		// Trim leading characters that can be skipped.
		String trimmedInput = trimIgnorableCharsFromStart(input);
		int index = 0;
		char currentChar = trimmedInput.charAt(index);
		String charsSequence = "";
		
		// loop through each character
		while (index < trimmedInput.length()) {

			currentChar = trimmedInput.charAt(index);

			/**
			 * Common characters
			 **/
			// Check if the current character can be skipped.
			if (arrayContains(charsToSkip, currentChar)) {
				index++;
				continue;
			}

			// Check if the current character is a symbol.
			if (arrayContains(symbol, currentChar)) {
				results.add(String.valueOf(currentChar));
				// Read next character and repeat.
				index++;
				continue;
			}

			// Check for both operation && , ||
			if (currentChar == '&') {
				String andOP = trimmedInput.substring(index, index + 2);
				if (andOP.equals("&&")) {
					results.add(andOP);
					index += 2;
					continue;
				}
			}
			if (currentChar == '|') {
				String orOP = trimmedInput.substring(index, index + 2);
				if (orOP.equals("||")) {
					results.add(orOP);
					index += 2;
					continue;
				}
			}

			/**
			 * check for keywords, numbers and ids.
			 **/
			// Check if the current character is a digit and read the longest possible
			// number.
			while (arrayContains(digit, currentChar)) {
				charsSequence += currentChar;
				index++;
				currentChar = trimmedInput.charAt(index);
			}
			if (isValidNumber(charsSequence)) {
				results.add(charsSequence);
				charsSequence = "";
				continue;
			}

			// Check if the current character is a letter and read the longest possible
			// letter token.
			while (arrayContains(letter, currentChar)) {
				charsSequence += currentChar;
				index++;
				currentChar = trimmedInput.charAt(index);
			}
			if (isValidToken(charsSequence)) {
				results.add(charsSequence);
				charsSequence = "";
				continue;
			}

			// If non of the above applies then return invalid.
			return getInvalidList();
		}

		return results;
	}

	/**
	 * Helper methods
	 **/
	public static List<String> getInvalidList() {
		List<String> invalidList = new ArrayList<String>();
		invalidList.add("invalid");
		return invalidList;
	}

	public static boolean arrayContains(char[] array, char character) {
		for (char c : array) {
			if (character == c) {
				return true;
			}
		}
		return false;
	}

	public static boolean arrayContains(String[] array, String str) {
		for (String s : array) {
			if (str.equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static String trimIgnorableCharsFromStart(String str) {
		int index = 0;
		char currentChar = str.charAt(index);
		while (arrayContains(charsToSkip, currentChar)) {
			index++;
			currentChar = str.charAt(index);
		}
		return str.substring(index);
	}

	public static boolean isValidNumber(String str) {
		boolean result = false;
		for (char c : str.toCharArray()) {
			if (arrayContains(digit, c)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

	public static boolean isValidToken(String str) {
		boolean result = false;
		for (char c : str.toCharArray()) {
			if (arrayContains(letter, c)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result || isValidKeyword(str);
	}

	public static boolean isValidKeyword(String src) {
		boolean result = false;
		if (arrayContains(keyword, src)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

}
