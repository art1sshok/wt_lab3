package by.mc.task.client.io;

import java.util.Scanner;

public class Input {
	public static String[] scanCommand() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		return in.nextLine().trim().split(" +");
	}
	
	public static String scanLine() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}
}
