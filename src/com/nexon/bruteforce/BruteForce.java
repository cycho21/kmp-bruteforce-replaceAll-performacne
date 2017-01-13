package com.nexon.bruteforce;

import java.util.HashSet;

public class BruteForce {

	public static final int FAILURE = -1;
	public static final int SUCCESS = 1;

	private int length;
	private int bannedWordLength;

	private int recursiveFind(String inputString, int originIdx, String bannedWord, int bannedIdx) {

		if (originIdx == length) {
			return FAILURE;
		}

		if (bannedIdx == bannedWordLength) {
			return FAILURE;
		}

		if (inputString.charAt(originIdx) == bannedWord.charAt(bannedIdx)) {
			if (bannedIdx == bannedWord.length() - 1)
				return originIdx;
			else
				return recursiveFind(inputString, ++originIdx, bannedWord, ++bannedIdx);
		}

		return FAILURE;
	}

	public String removeBannedWords(String inputString, HashSet<String> bannedWords) {
		StringBuilder sb = new StringBuilder();
		length = inputString.length();

		for (String bannedWord : bannedWords) {

			for (int i = 0; i < length; ++i) {
				bannedWordLength = bannedWord.length();

				int result = recursiveFind(inputString, i, bannedWord, 0);

				if (result != FAILURE) {
					for (int k = 0; k < bannedWordLength; ++k) {
						sb.append("»Ð");
					}
					i = result;
				} else {
					sb.append(inputString.charAt(i));
				}
			}
			
			inputString = sb.toString();
			sb.setLength(0);
		}

		return inputString;
	}

}
