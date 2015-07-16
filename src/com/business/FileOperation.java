package com.business;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {
	/*
	 * 操作类，不具有对象。 所有函数为静态函数。
	 */
	public static int saveAsFileWriter(String path, String data) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(path,true);
			fw.append(data);
			return 1;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fw.flush();
				fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return 0;
	}

	public static String ReadFileToString(String path) {
		int ch = 0;
		String data = "";
		try {
			FileReader fr = new FileReader(path);
			while ((ch = fr.read()) != -1) {
				data = data + (char) ch;
			}
			fr.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
		}
		return data;
	}

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FileOperation.saveAsFileWriter("D:\\projectMy\\user\\1234.txt", "123\r\n");
		FileOperation.saveAsFileWriter("D:\\projectMy\\user\\1234.txt", "456\r\n");
	}*/

}
