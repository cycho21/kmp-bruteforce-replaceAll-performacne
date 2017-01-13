package com.nexon.kmp;

import java.util.HashSet;

public class Kmp {

	public Kmp() {
	}

	private int[] getPI(String bannedWord) {

		int length = bannedWord.length();
		int[] retArr = new int[length];
		int j = 0;

		for (int i = 1; i < length; ++i) {
			while (j > 0 && bannedWord.charAt(i) != bannedWord.charAt(j))
				j = retArr[j - 1];

			if (bannedWord.charAt(i) == bannedWord.charAt(j))
				retArr[i] = ++j;
		}

		return retArr;
	}

	public String removeBannedWords(String inputString, HashSet<String> bannedWords) {

		StringBuilder sb = new StringBuilder();

		for (String bannedWord : bannedWords) {

			int[] pi = getPI(bannedWord);
			int bannedWordLength = bannedWord.length();
			int inputStringLength = inputString.length();

			for (int i = 0, j = 0; i < inputStringLength; ++i) {
				
				while (j > 0 && inputString.charAt(i) != bannedWord.charAt(j)) {
					j = pi[j - 1];
				}

				if (inputString.charAt(i) == bannedWord.charAt(j)) {
					if (j == bannedWordLength - 1) {
						for (int q = 0; q < bannedWordLength; ++q) 
							sb.append("»Ð");
						j = pi[j];
					} else {
						j++;
					}
				} else {
					
					for (int q = 0; q < j; ++q) {
						sb.append(inputString.charAt(i + q));
					}
					sb.append(inputString.charAt(i));
				}
			}
			
			inputString = sb.toString();
			sb.setLength(0);
		}
		return inputString;
	}
}
