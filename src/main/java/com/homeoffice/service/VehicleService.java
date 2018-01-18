package com.homeoffice.service;

import com.homeoffice.model.FileInfo;
import com.homeoffice.model.Vehicle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class VehicleService {
	private static final Logger logger = LoggerFactory.getLogger(VehicleService.class.getName());

	// Test to see if we can read the list of vehicles from the files.
	public static void main(String[] args) {
		List <Vehicle> ff = new ArrayList <>();
		VehicleService vh = new VehicleService();
		List <String> extns = new ArrayList <>();
		extns.add("csv");
		extns.add("xlx");
		ff = vh.readFile(extns, new FileInfoService().getFileMapWithExtns());
		ff.forEach(vehicle -> {
			System.out.println(vehicle.getRegNumber());
		});


	}

	public List <Vehicle> readFile(List <String> fileExtns,
	                               Map <String, ArrayList <FileInfo>> fileDetailsMap) {
		List <Vehicle> vehiclesFromAllFiles = new ArrayList <>();

		for (String fileExtn : fileExtns) {
			List <FileInfo> fileInfoList = fileDetailsMap.get(fileExtn);
			switch (fileExtn.toLowerCase()) {
				case "csv":
					fileInfoList.stream()
							.filter(fileInfo -> fileInfo.getFileName() != null).
							forEach(file -> vehiclesFromAllFiles.addAll(readCSVFile(file.getFileName())));
					break;
				case "xlsx":
					fileInfoList.stream()
							.filter(fileInfo -> fileInfo.getFileName() != null).
							forEach(file -> vehiclesFromAllFiles.addAll(readExcelFile(file.getFileName())));
					break;
				default:
					logger.error("Unsupported file format " + fileExtn + " NOT supported...");
			}
		}

		return vehiclesFromAllFiles;
	}

	//    This is reading CSV files
	private List <Vehicle> readCSVFile(String fileName) {
		String line = "";
		String cstSeparator = ",";
		List <Vehicle> vehicleList = new ArrayList <>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] vehicleData = line.split(cstSeparator);
				Vehicle vehicle = new Vehicle();
				vehicle.setRegNumber(vehicleData[0]);
				vehicle.setMake(vehicleData[1]);
				vehicle.setColour(vehicleData[2]);
				vehicleList.add(vehicle);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vehicleList;
	}

	//    this is reading an excel file and I've sued POI API
	private List <Vehicle> readExcelFile(String fileName) {
		List <Vehicle> vehicleList = new ArrayList <>();
		try {
			FileInputStream excelFile = new FileInputStream(fileName);
			try {
				Workbook workbook = new XSSFWorkbook(excelFile);
				Sheet datatypeSheet = workbook.getSheetAt(0);
				Iterator <Row> iterator = datatypeSheet.iterator();
				iterator.next();
				while (iterator.hasNext()) {
					Vehicle vehicle = new Vehicle();
					Row currentRow = iterator.next();
					vehicle.setRegNumber(currentRow.getCell(0).getStringCellValue());
					vehicle.setMake(currentRow.getCell(1).getStringCellValue());
					vehicle.setColour(currentRow.getCell(2).getStringCellValue());
					vehicleList.add(vehicle);
				}
			} finally {
				excelFile.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vehicleList;
	}


}
