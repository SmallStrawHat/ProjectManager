package com.business;

import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {
	/*
	 * 操作类，不具有对象。 所有函数为静态函数。
	 */
	public static int saveAsFileWriter(String path, String data) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
