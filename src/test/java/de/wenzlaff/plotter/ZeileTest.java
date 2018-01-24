package de.wenzlaff.plotter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.wenzlaff.plotter.Zeile;

public class ZeileTest {

	@Test
	public void testZeile() throws Exception {
		// date, time, Hz low, Hz high, Hz step, samples, dB, dB, dB, ...
		String inhalt = "2018-01-20, 18:02:14, 1089000000, 1091000000, 976.56 , 4988, -43.38, -43.93";
		Zeile z = new Zeile(inhalt);
		z.parse();
		assertEquals(2018, z.getDatum().getYear());
		assertEquals(1, z.getDatum().getMonthValue());
		assertEquals(20, z.getDatum().getDayOfMonth());

		assertEquals(18, z.getZeit().getHour());
		assertEquals(2, z.getZeit().getMinute());
		assertEquals(14, z.getZeit().getSecond());

		assertEquals(1089000000, z.getHzLow().intValue());

		assertEquals(1091000000, z.getHzHigh().intValue());

		assertEquals(Double.valueOf(976.56d), z.getHzStep());

		assertEquals(4988, z.getSamples().intValue());

		assertEquals(2, z.getDb().size());

		assertEquals(Double.valueOf(-43.38), z.getDb().get(0));
		assertEquals(Double.valueOf(-43.93), z.getDb().get(1));

		assertEquals("2018-01-20T18:02:14", z.getDatumZeit().toString());

		System.out.println(z);
	}

}
