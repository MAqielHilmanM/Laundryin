<?php
	$con = mysqli_connect("localhost","root","","db_laundryin") or die("Koneksi Gagal");
	if (mysqli_connect_errno()) {
		echo "Gagal terhubung MySQL: " . mysqli_connect_error();
	}

?>