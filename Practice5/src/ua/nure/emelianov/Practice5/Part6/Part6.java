package ua.nure.emelianov.Practice5.Part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Part6 extends Thread {

	Part6(String filename, String mode) {
		try {
			out = new RandomAccessFile(fileName, mode);
		} catch (FileNotFoundException e) {
			System.err.println(filename + " is not found");
		}
	}

	private static final String EXC = "Thread:"
			+ Thread.currentThread().getName() + " is interrupted";

	private static String encoding = "cp1251";

	public static String getEncoding() {
		return encoding;
	}

	public static void setEncoding(String encoding) {
		Part6.encoding = encoding;
	}

	private int counter = -1;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	private static final int THREADS_NUMBER = 10;

	private static final int COLUMNS = 20;

	private static String fileName = "test.txt";

	private RandomAccessFile out;

	@Override
	public void run() {
		try {
			for (int i = 0; i < COLUMNS; i++) {
				out.write(48 + Integer.parseInt(currentThread().getName()
						.substring(currentThread().getName().length() - 1)));
			}
			setCounter(getCounter() + 1);

			if (getCounter() != 9
					&& !currentThread().getName().equals(
							"Thread-" + (THREADS_NUMBER - 1))) {
				out.write(System.lineSeparator().getBytes(getEncoding()));
			}

		} catch (IOException e1) {
			System.err.println(EXC);
		}
	}

	public static void writeToFile() {
		for (int i = 0; i < THREADS_NUMBER; i++) {
			Part6 myThread = new Part6(fileName, "rw");
			try {
				myThread.out.seek(i * 22);
			} catch (IOException e) {
				System.err.println(EXC);
			}
			myThread.start();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				System.err.println(EXC);
			}
		}

	}

	public static String getOutput() {
		StringBuilder sb = new StringBuilder();
		Scanner sc;
		try {
			sc = new Scanner(new File("test.txt"), getEncoding());
			while (sc.hasNext()) {
				sb.append(sc.next());
				sb.append(System.lineSeparator());
			}
			sc.close();
		} catch (IOException e) {
			System.err.println(EXC);
		}
		return sb.substring(0, sb.length() - System.lineSeparator().length())
				.toString();
	}

	public static void main(String[] args) {
		writeToFile();
		System.out.println(getOutput());

	}
}
