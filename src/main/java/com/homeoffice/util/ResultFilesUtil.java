package com.homeoffice.util;

import java.io.File;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class ResultFilesUtil {

	public void createDirs() {
		File passFolder = new File("Pass_Screenshots");
		File failFolder = new File("Fail_Screenshots");
		if (!passFolder.exists()) {
			passFolder.mkdir();
		} else {
			deleteFiles(passFolder.listFiles());
		}
		if (!failFolder.exists()) {
			failFolder.mkdir();
		} else {
			deleteFiles(failFolder.listFiles());
		}
	}

	public void deleteFiles(File[] files) {
		for (File file : files) {
			file.delete();
		}
	}
}
