

<?php
// Deklariere das Interface 'iTemplate'
interface Wahl_Interface
{
   //Funktion, die eine Datenbank f�r eine konkrete Wahl erstellt
	function datenbank_erstellen();
	
	//Definiert die Anzeige einer Mitgliederliste f�r eine bestimmte Partei
	function mitgliederliste_anzeigen($partei_id);
	
	//Funktion, mit der die Parteienliste geladen und angezeigt wird
	function parteienliste_laden();
	
	//Bereitet die Abgabe der Wahl vor
	function wahl_abgeben();
	
	/*Wird aufgerufen, wenn ein Button bet�tigt wurde (Listenstimme angekreuzt, Stimme an Kandidaten vergeben oder Kandidat gestrichen).
	Die �nderung wird in dieser Methode vorgenommen.*/
	function stimme_bearbeiten();
	
	//Erstellt den Wahlcode mit dem der QR-Code und das PDF erstellt wird
	function wahlcode_erstellen();
	
	//Definition der Regeln f�r den Header
	function check_rules();
	
	//Wenn eine Suchfunktion f�r die Wahl erstellt werden soll, werden die folgenden Methoden ben�tigt, um die Suche zu erstellen
	function get_search_value(); //erstellt die Suchliste f�r die Suche
	function suche($suche); //liest den Suchtext aus und zeigt die gew�nschte Partei bzw. den gew�nschten Kandidaten an oder gibt eine Fehlermeldung aus
	
	//Erstellt ein PDF aus dem �bergebenen Wahlcode
	function makePDF($wahlcode);
	
	//Zeigt die Wahl des Users als Vorschau an
	function show_wahl($wahlcode);
}