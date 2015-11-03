package ua.nure.emelianov.Practice3.part1;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	private static String input = Part1.getInput("part1.txt");

	public static String convert1(String input) {
		String[] text = input.split(System.lineSeparator());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < text.length; i++) {
			String[] a = text[i].split(";");
			sb.append(a[0] + " ==> " + a[2] + System.lineSeparator());
		}
		return sb.toString();
	}

	public static String convert2(String input) {
		String[] text = input.split(System.lineSeparator());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < text.length; i++) {
			String[] a = text[i].split("[\\s\\;]");
			sb.append(a[2] + " " + a[1] + " (email: " + a[3] + ")" + System.lineSeparator());

		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();

	}

	public static String convert3(String input) {
		Pattern p = Pattern.compile("^?(.+;)(.+;)(.+)(@.+)");
		Matcher m = p.matcher(input);
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		while (m.find()) {
			Pattern p2 = Pattern.compile(m.group(4).substring(1));
			Matcher m2 = p2.matcher(sb);
			if (!m2.find()) {
				sb.append(System.lineSeparator() + m.group(4).substring(1)
						+ " ==> "
						+ m.group(1).substring(0, m.group(1).length() - 1));
			} else {
				Pattern p3 = Pattern.compile(m.group(4).substring(1) + ".+");
				Matcher m3 = p3.matcher(sb);
				m3.find();
				sb.insert(m3.end(),
						", " + m.group(1).substring(0, m.group(1).length() - 1));
			}
		}
		return sb.toString().substring(2);

	}

	public static String convert4(String input) {
		Random rand = new Random();
		String[] text = input.split(System.lineSeparator());
		StringBuilder sb = new StringBuilder();
		sb.append(text[0] + ";Password" + System.lineSeparator());
		for (int i = 1; i < text.length; i++) {
			int random = rand.nextInt(9999);
			while (random < 1001) {
				random = rand.nextInt(9999);
			}
			sb.append(text[i] + ";" + random + System.lineSeparator());

		}
		return sb.toString();
	}

	public static String getInput(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new File(fileName), "Cp1251");
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
			return sb.toString().trim();
		} catch (IOException ex) {
			System.err.println("Part1 FileNotFound");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.print(Part1.convert1(input)+System.lineSeparator());
		System.out.println(Part1.convert2(input)+System.lineSeparator());
		System.out.println(Part1.convert3(input)+System.lineSeparator());
		System.out.print(Part1.convert4(input)+System.lineSeparator());
	}
}
