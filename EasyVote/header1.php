<?php	
	
	//Wenn die Wahl ge�ndert wird, wird �berpr�ft, ob ein neuer Header geladen werden muss (durch die Methode create_rules)
	if(isset($_POST['vote_changed'])){
		$wahl = new WahlNavi();
		$wahl->create_rules();
	}
	
	//Wenn der Reset-Button bet�tigt wurde, wird auch der Header der Seite zur�ckgesetzt
	if(isset($_POST['reset'])){
		unset($_SESSION['alert']);
	}
	
	//Ruft die PHP-Seite auf, die die Regeln anzeigen
	require_once("lang/".$_SESSION['lang'].$_SESSION['aktive_wahl']."/regel_anzeige.php");
?>

 
