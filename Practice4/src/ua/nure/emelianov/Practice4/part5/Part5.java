package ua.nure.emelianov.Practice4.part5;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

	private static final String FILE_NAME = "resources";

	public void output() {
		Scanner sc = new Scanner(System.in,"cp1251");
		while (sc.hasNext()) {
			try {
				String[] arr = sc.nextLine().split(" ");
				if (arr[0].equalsIgnoreCase("stop")) {
					System.out.println("Finished");
					break;
				}
				if (arr.length != 2) {
					throw new ArrayIndexOutOfBoundsException();
				}
				Locale locale = new Locale(arr[1].toLowerCase());
				ResourceBundle rb = ResourceBundle.getBundle(FILE_NAME, locale);
				System.out.println(rb.getString(arr[0]));
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Output required: [key] [value]");
				continue;
			} catch (MissingResourceException e) {
				System.out.println("Invalid key");
				continue;
			}
		}
		sc.close();
	}

	public static void main(String[] args) {
		new Part5().output();
	}
}
