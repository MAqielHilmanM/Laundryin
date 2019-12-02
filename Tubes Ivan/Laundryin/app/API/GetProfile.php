<?php
 include_once "koneksi.php";

	class usr{}
	
	$username = $_GET['username'];
	
	$query = mysqli_query($con, "SELECT * FROM tb_pelanggan WHERE username = '$username'");
			
	if ($query){
		$response = new usr();
		$response->status = 1;
		$response->message = "Ambil Paket Berhasil";
		$datas = array();

		while($row = mysqli_fetch_assoc($query)){
			$datas = array(
				"username" => $row['username'],
				"password" => $row['password'],
				"nama" => $row['nama'],
				"alamat" => $row['alamat'],
				"email" => $row['email'],
				"pakaian" => $row['pakaian'],
				"url_picture" => "https://lh5.googleusercontent.com/-bIqOWtgzJNc/Unzk7VGz3QI/AAAAAAAAByY/E6VftXSSwno/s1600/IMG_20131108_192941.jpg"
			);		
		}
		
		$response->data = $datas;

		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->status = 0;
		$response->message = "gagal mendapatkan semua paket";
		$response->data = null;
		die(json_encode($response));
	}
	
	mysqli_close($con);
?>