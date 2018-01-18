package com.homeoffice.service;


import com.homeoffice.model.FileInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class FileInfoService {

	public HashMap<String, ArrayList<FileInfo>> getFileMapWithExtns() {
		List<FileInfo> fileData = new ArrayList <>();
		// hardcoded at the moment but can take in values to point to the folder
		File dir = new File("src/main/resources/testData");
		File[] fileList = dir.listFiles();
		for (File file : fileList) {
			String absolutePath = file.getAbsolutePath();
			if (file.isFile()) {
					fileData.add(getFilesDetails(absolutePath));
			}
		}
		return getMapOfFilesOnExtension(fileData);
	}

	private HashMap<String, ArrayList<FileInfo>> getMapOfFilesOnExtension(List<FileInfo> fileInfoList){
		HashMap<String, ArrayList<FileInfo>> fileMapSortedExtn = new HashMap <>();
		ArrayList<FileInfo> fileInfoTemp;
			for(FileInfo fileInfo:fileInfoList) {
				fileInfoTemp = new ArrayList <>();
				if (fileMapSortedExtn.get(fileInfo.getFileExtension()) != null) {
					fileInfoTemp = fileMapSortedExtn.get(fileInfo.getFileExtension());
				}
				fileInfoTemp.add(fileInfo);
				fileMapSortedExtn.put(fileInfo.getFileExtension(),fileInfoTemp);
			}
			return fileMapSortedExtn;
	}

	private FileInfo getFilesDetails(String absolutePath) {
		FileInfo fileDetails = new FileInfo();
		String mimeType = getMimeType(absolutePath);
		fileDetails.setFileMimeType(mimeType);
		fileDetails.setFileExtension(FilenameUtils.getExtension(absolutePath));
		fileDetails.setFileSize(new File(absolutePath).length());
		fileDetails.setFileName(absolutePath);
		return fileDetails;
	}

	private String getMimeType(final String fileName) {
		Tika mimeTika = new Tika();
		String fileType;
		try {
			final File file = new File(fileName);
			fileType = mimeTika.detect(file);
		} catch (IOException exp) {
			fileType = "Unknown";
		}
		return fileType;
	}


}
