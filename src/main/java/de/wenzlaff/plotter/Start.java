package de.wenzlaff.plotter;

import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

public class Start {

	static {
		System.setProperty(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY, "./log4j2.xml");
	}
	/**
	 * Log4j2 siehe
	 * http://logging.apache.org/log4j/2.x/manual/configuration.html#AutomaticConfiguration
	 */
	private static final Logger LOG = LogManager.getLogger(Start.class.getName());

	/**
	 * Start des Plotters
	 * 
	 * @param args
	 *            Dateiname der CSV Datei
	 * @throws Exception
	 *             bei Fehler
	 */
	public static void main(String args[]) throws Exception {
		String csvDateiName = args[0];

		LocalTime startZeit = LocalTime.now();
		LOG.info("Startzeit: " + startZeit + " mit CSV Datei: " + csvDateiName);

		List<Zeile> alleDatensaetze = Reader.leseDatensaetze(csvDateiName);

		int breite = alleDatensaetze.get(0).getDb().size(); // vom ersten Datensatz, da alle gleich breit sind
		int hoehe = alleDatensaetze.size(); // ein Pixel pro Zeile

		LOG.info("Breite: " + breite + " Höhe: " + hoehe + " Anzahl Pixel: " + breite * hoehe);
		BufferedImage img = new BufferedImage(breite, hoehe, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < alleDatensaetze.size(); y++) {
			List<Double> dbs = alleDatensaetze.get(y).getDb();
			LOG.debug("Max: " + Util.getMaxDb(dbs) + " Min: " + Util.getMinDb(dbs) + " Differenz: "
					+ (Util.getMaxDb(dbs) - Util.getMinDb(dbs)));
			for (int x = 0; x < dbs.size(); x++) {
				Double dbWert = dbs.get(x);

				try {
					img.setRGB(x, y, Util.berechneColor(-dbWert.floatValue()).getRGB());
				} catch (Exception e) {
					LOG.error("Ungültiger Farbwert.");
				}
			}
		}

		Writer.schreibeBild(img);

		LocalTime endeZeitpunkt = LocalTime.now();
		LOG.info("Ende Zeitpunkt: " + endeZeitpunkt);
		LOG.info("Programmlauf Dauer: " + startZeit.until(endeZeitpunkt, ChronoUnit.SECONDS) + " Sekunden");
	}

}