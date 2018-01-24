package de.wenzlaff.plotter;

import org.junit.jupiter.api.Test;

class StartTest {

	@Test
	void testMainMitNichtVorhandenerDatei() throws Exception {

		String argv[] = { "nicht/vorhanden" };
		Start.main(argv);
	}

	@Test
	void testMainMitTestdatei() throws Exception {

		String argv[] = { "src/test/resources/de/wenzlaff/plotter/airband-1090-test.csv" };
		Start.main(argv);
	}

}
