package com.example.easyvoteqrreader;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class Landtagswahl extends Wahl{
	
	/**
	 * Methode die einen Wahlcode �bergeben bekommt und aus diesem die Wahl des Anwenders rekonstruiert
	 */
	@Override
	public String showElection(String contents) {
		/*
		 * Die folgenden Werte werden aus de, Wahlcode ausgelesen
		 * 
		 * wahlart: Die ausgelesene Wahlart (hier Kommunalwahl)
		 * gueltig: Ob die Wahl g�tlig oder ung�rlig ist
		 * candidate_id: Die ID des gew�hlten Kandidaten
		 * partei_id: DIe ID der gew�hlten Partei
		 */
		String wahlart = (String) contents.subSequence(0, 5);

		String gueltig = (String) contents.subSequence(5, 6);
		if (gueltig.equals("1")) {
			gueltig = "G�ltig";
		} else {
			gueltig = "Ung�ltig";
		}

		int wks_partei_id = Integer.parseInt((String) contents
				.subSequence(6, 8));
		String ls_partei_id = (((Integer) Integer.parseInt((String) contents
				.subSequence(9, 11))).toString());
		
		String candidate_id = ((Integer) ((wks_partei_id * 100) + 1)).toString();

		/*
		 * Im nachfolgenden Block wird das zur Wahl geh�rige XML-Dokument ge�ffnet und die Namen, die zu den berechneten IDs geh�ren, ausgelesen
		 */
		String tmp = "";
		String partei_name = "-";
		String wahljahr = "";
		String candidate_name = "-";
		XmlPullParserFactory factory;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();

			xpp.setInput(this.getAssets().open(wahlart + ".xml"), null);
			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				String name = xpp.getName();
				switch (eventType) {
				case XmlPullParser.START_TAG:
					if (name.equals("party")) {
						if (ls_partei_id.equals(xpp
								.getAttributeValue(null, "id"))) {
							partei_name = xpp.getAttributeValue(null, "partei");
						}
					}
					if (name.equals("candidate")) {
						if (candidate_id.equals(xpp
								.getAttributeValue(null, "id"))) {
							candidate_name = xpp.getAttributeValue(null, "prename")
									+ " "
									+ xpp.getAttributeValue(null, "name")
									+ " ("
									+ xpp.getAttributeValue(null, "partei")
									+ ")";
						} 
					}
					break;
				case XmlPullParser.TEXT:
					tmp = xpp.getText();
					break;

				case XmlPullParser.END_TAG:
					if (name.equals("election_name")) {
						wahlart = tmp;
					}
					if (name.equals("election_year")) {
						wahljahr = tmp;
					}
					break;
				}
				eventType = xpp.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return wahlart + " (" + wahljahr +")\n\n" + "Status: " + gueltig + "\n\nGew�hlter Kandidat (Wahlkreisstimme):\n" + candidate_name + "\n\nGew�hlte Partei (Landesstimme):\n" + partei_name;
	}

}
