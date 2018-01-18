package com.homeoffice.actions;

import com.homeoffice.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by giridharvasudevan on 18/01/2018.
 */
public class PrintResults extends BaseActions {
	private static final Logger logger = LoggerFactory.getLogger(PrintResults.class.getName());

	public void printResultToFile() {
		String content = null;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("results.txt"))) {
			for (Result result : results) {
				content = "\n**********************************************************************\n" +
						"\n----------Vehicle File Details: ----------\n"
						+ result.getVehicle().getRegNumber() +
						"\nMake: " + result.getVehicle().getMake() + "::" + result.getVehicle().getColour() + "\n" +
						"\n" +
						"\n---Result from DVLA : ----------------------------\n" + result.getErrorMessage() +
						"\n**********************************************************************\n";
				bw.write(content);
			}

		} catch (IOException e) {


		}

	}
}
