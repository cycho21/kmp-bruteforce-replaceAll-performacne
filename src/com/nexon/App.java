package com.nexon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

import com.nexon.bruteforce.BruteForce;
import com.nexon.kmp.Kmp;

public class App {

	public static void main(String[] args) {
		App test = new App();
		test.initialize();

		HashSet<String> bannedWords = new HashSet<String>();
		String path = "./test.txt";
		bannedWords.add("±∏±€");
		bannedWords.add("±§∞Ì¡÷");
		bannedWords.add("∑π≥Îπˆ");
		bannedWords.add("Ω√Ω∫≈€");

		test.findWords(path, bannedWords);
		test.findWordsByJava(path, bannedWords);
		test.findWordsByKMP(path, bannedWords);
	}

	private void findWordsByKMP(String path, HashSet<String> bannedWords) {
		
		try {
			byte[] encodedFile = Files.readAllBytes(Paths.get(path));
			
			String inputString = new String(encodedFile);
			
			Kmp kmp = new Kmp();
			
			long startTime = System.currentTimeMillis();
			
			kmp.removeBannedWords(inputString, bannedWords);
			
			long endTime = System.currentTimeMillis();

			System.out.println("KMP Time : " + (double) (endTime - startTime) / 1000 + " seconds");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void findWordsByJava(String path, HashSet<String> bannedWords) {
		try {
			byte[] encodedFile = Files.readAllBytes(Paths.get(path));

			String inputString = new String(encodedFile);
			
			long startTime = System.currentTimeMillis();

			for (String bannedWord : bannedWords) {
				inputString = inputString.replaceAll(bannedWord, "ª–ª–");
			}
			
			long endTime = System.currentTimeMillis();

			System.out.println("String replaceAll Time : " + (double) (endTime - startTime) / 1000 + " seconds");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void findWords(String path, HashSet<String> bannedWords) {

		try {
			byte[] encodedFile = Files.readAllBytes(Paths.get(path));

			String inputString = new String(encodedFile);
			
			long startTime = System.currentTimeMillis();

			BruteForce bruteForce = new BruteForce();

			bruteForce.removeBannedWords(inputString, bannedWords);

			long endTime = System.currentTimeMillis();

			System.out.println("Brute Force Time : " + (double) (endTime - startTime) / 1000 + " seconds");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initialize() {
	}

}
