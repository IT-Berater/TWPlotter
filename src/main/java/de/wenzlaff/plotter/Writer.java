package de.wenzlaff.plotter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Writer {

	private static final Logger LOG = LogManager.getLogger(Writer.class.getName());

	public static void schreibeBild(BufferedImage img) {

		try {
			String bildDateiName = "bild-" + getTimestamp() + ".png";
			File f = new File(bildDateiName);
			ImageIO.write(img, "png", f);
			LOG.info("Bilddatei " + bildDateiName + " geschrieben.");
		} catch (IOException e) {
			LOG.error("Error: " + e);
		}
	}

	private static String getTimestamp() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
	}
}
