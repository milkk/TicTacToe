package me.namreg.tictactoe.helpers;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

abstract public class FileHelper {

	private FileHelper() {
	}

	public static String getTextFromFile(String fileName) throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException(file.getName());
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String s;
				while ((s = br.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				br.close();
			}
		} catch (Exception e) {

		}

		return sb.toString();
	}

}
