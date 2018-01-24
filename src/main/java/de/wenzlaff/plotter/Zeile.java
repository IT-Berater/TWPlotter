package de.wenzlaff.plotter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Eine Zeile der Daten.
 * 
 * @author Thomas Wenzlaff
 *
 */
public class Zeile {

	private static final Logger LOG = LogManager.getLogger(Zeile.class.getName());

	/** Der Tag des erstellten Datensatzes. */
	private LocalDate datum;
	/** Die Uhrzeit des erstellen Datensatzes. */
	private LocalTime zeit;
	/** Niedrigste Frequenz in Herz. */
	private Integer hzLow;
	/** Höchste Frequenz in Herz. */
	private Integer hzHigh;
	/** Step in Hz. */
	private Double hzStep;
	/** Sampel in Hz. */
	private Integer samples;
	/** Die jeweiligen DB Werte. */
	private List<Double> db;

	/** Eine Datenzeile, Komma separiert, wie sie von rtl_power erstellt wird. */
	private String zeile;

	/**
	 * Konstrukor für eine Zeile der von dem rtl-power erzeugen Programm
	 * (http://kmkeen.com/rtl-power/)
	 * 
	 * Eine Zeile ist wie folgt aufbebaut:
	 * 
	 * date, time, Hz low, Hz high, Hz step, samples, dB, dB, dB, ...
	 * 
	 * Z.B.
	 * 
	 * 2018-01-20, 18:02:14, 1089000000, 1091000000, 976.56 , 4988, -43.38, -43.93,
	 * ...
	 * 
	 * @param zeile
	 *            ein Zeile
	 */
	public Zeile(String zeile) {
		this.zeile = zeile;
	}

	public void parse() throws ParseException {

		String zeichen[] = zeile.split(",");

		if (zeichen.length < 5) {
			LOG.error("Ungültige Zeile!");
			throw new ParseException("Zeile fehlerhaft, keine 5 Spalten", zeichen.length);
		}
		datum = LocalDate.parse(zeichen[0].trim());
		zeit = LocalTime.parse(zeichen[1].trim());

		hzLow = Integer.valueOf(zeichen[2].trim());
		hzHigh = Integer.valueOf(zeichen[3].trim());

		hzStep = Double.valueOf(zeichen[4].trim());

		samples = Integer.valueOf(zeichen[5].trim());

		db = new ArrayList<Double>();
		for (int i = 6; i < zeichen.length; i++) {
			db.add(Double.valueOf(zeichen[i].trim()));
		}
	}

	public LocalDateTime getDatumZeit() {
		return LocalDateTime.of(datum, zeit);
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public LocalTime getZeit() {
		return zeit;
	}

	public void setZeit(LocalTime zeit) {
		this.zeit = zeit;
	}

	public Integer getHzLow() {
		return hzLow;
	}

	public void setHzLow(Integer hzLow) {
		this.hzLow = hzLow;
	}

	public Integer getHzHigh() {
		return hzHigh;
	}

	public void setHzHigh(Integer hzHigh) {
		this.hzHigh = hzHigh;
	}

	public Double getHzStep() {
		return hzStep;
	}

	public void setHzStep(Double hzStep) {
		this.hzStep = hzStep;
	}

	public Integer getSamples() {
		return samples;
	}

	public void setSamples(Integer samples) {
		this.samples = samples;
	}

	public List<Double> getDb() {
		return db;
	}

	public void setDb(List<Double> db) {
		this.db = db;
	}

	@Override
	public String toString() {
		return "Zeile [datum=" + datum + ", zeit=" + zeit + ", hzLow=" + hzLow + ", hzHigh=" + hzHigh + ", hzStep="
				+ hzStep + ", samples=" + samples + ", db=" + db + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + ((db == null) ? 0 : db.hashCode());
		result = prime * result + ((hzHigh == null) ? 0 : hzHigh.hashCode());
		result = prime * result + ((hzLow == null) ? 0 : hzLow.hashCode());
		result = prime * result + ((hzStep == null) ? 0 : hzStep.hashCode());
		result = prime * result + ((samples == null) ? 0 : samples.hashCode());
		result = prime * result + ((zeit == null) ? 0 : zeit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zeile other = (Zeile) obj;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (db == null) {
			if (other.db != null)
				return false;
		} else if (!db.equals(other.db))
			return false;
		if (hzHigh == null) {
			if (other.hzHigh != null)
				return false;
		} else if (!hzHigh.equals(other.hzHigh))
			return false;
		if (hzLow == null) {
			if (other.hzLow != null)
				return false;
		} else if (!hzLow.equals(other.hzLow))
			return false;
		if (hzStep == null) {
			if (other.hzStep != null)
				return false;
		} else if (!hzStep.equals(other.hzStep))
			return false;
		if (samples == null) {
			if (other.samples != null)
				return false;
		} else if (!samples.equals(other.samples))
			return false;
		if (zeit == null) {
			if (other.zeit != null)
				return false;
		} else if (!zeit.equals(other.zeit))
			return false;
		return true;
	}

}
