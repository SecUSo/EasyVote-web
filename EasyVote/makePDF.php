<?php
require('fpdf/fpdf.php');
require('wahl_navigation.php');

	// �ffnet die Methode, die das PDF erstellt
	$wahlNavi = new WahlNavi();
	$wahlNavi->createPDF($_POST['pdf']);
	
?>


