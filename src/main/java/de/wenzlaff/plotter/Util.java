package de.wenzlaff.plotter;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

/**
 * Util Klasse.
 * 
 * @author Thomas Wenzlaff
 *
 */
public class Util {

	private static final int MAX_RGB = 255;
	private static final float MAX_VALUE = 74;

	private Util() {
		// nur Util
	}

	public static Double getMinDb(List<Double> dbs) {
		return -Collections.max(dbs);
	}

	public static Double getMaxDb(List<Double> dbs) {
		return -Collections.min(dbs);

	}

	public static Color berechneColor(float aktuellerWert) {

		float maxColor = MAX_RGB + MAX_RGB + MAX_RGB;

		float skalierterMaxColor = (maxColor / MAX_VALUE) * aktuellerWert;

		int r = 0;
		int g = 0;
		int b = 0;

		if (skalierterMaxColor < MAX_RGB) {
			b = (int) skalierterMaxColor;
		} else if (skalierterMaxColor > MAX_RGB && skalierterMaxColor < MAX_RGB * 2) {
			g = (int) skalierterMaxColor - MAX_RGB;
		} else if (skalierterMaxColor > MAX_RGB * 2) {
			r = (int) skalierterMaxColor - MAX_RGB * 2;
			r = Math.min(r, MAX_RGB);
		}

		return new Color(r, g, b);
	}

}
