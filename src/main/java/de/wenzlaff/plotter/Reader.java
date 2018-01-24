package de.wenzlaff.plotter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reader {

	private static final Logger LOG = LogManager.getLogger(Reader.class.getName());

	public static List<Zeile> leseDatensaetze(String excelDateiName) throws FileNotFoundException {

		Scanner scanner = new Scanner(new FileInputStream(new File(excelDateiName)));

		LOG.info("Lese alle Zeilen aus der CSV Datei " + excelDateiName + " ein");

		List<Zeile> alleDatensaetze = new ArrayList<Zeile>();

		while (scanner.hasNextLine()) {
			Zeile z = new Zeile(scanner.nextLine());
			try {
				z.parse();
				alleDatensaetze.add(z);
			} catch (Exception e) {
				// bei Parser Fehlern Zeile ignorieren
				LOG.error("Fehler in Zeile: " + z);
			}
		}
		scanner.close();
		LOG.info("Gelesene Zeilen: " + alleDatensaetze.size());
		return alleDatensaetze;
	}

}
