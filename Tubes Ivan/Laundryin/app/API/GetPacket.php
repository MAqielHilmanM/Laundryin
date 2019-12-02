<?php
 include_once "koneksi.php";

	class usr{}
	
	$id_paket = $_GET['id_paket'];
	
	$query = mysqli_query($con, "SELECT * FROM tb_paketlaundry WHERE id_paket = '$id_paket'");
			
	if ($query){
		$response = new usr();
		$response->status = 1;
		$response->message = "Ambil Paket Berhasil";
		$datas = array();

		while($row = mysqli_fetch_assoc($query)){
			$reviews = array();
			
			$query2 = mysqli_query($con, "SELECT * FROM tb_ulasan u JOIN tb_pelanggan p ON u.username = p.username WHERE u.id_paket = '".$id_paket."'");
		
			while($row2 = mysqli_fetch_assoc($query2)){
				array_push($reviews,array(
					"id" => $row2['id'],
					"id_paket" => $row2['id_paket'],
					"username" => $row2['username'],
					"ulasan" => $row2['ulasan'],
					"entry_date" => $row2['entry_date'],
					"name" => $row2['nama'],
					"url_picture" => "https://lh5.googleusercontent.com/-bIqOWtgzJNc/Unzk7VGz3QI/AAAAAAAAByY/E6VftXSSwno/s1600/IMG_20131108_192941.jpg"
				));		
			}

			$datas = array(
				"id_paket" => $row['id_paket'],
				"nama_paket" => $row['nama_paket'],
				"harga_paket" => $row['harga_paket'],
				"estimasi_paket" => $row['estimasi_paket'],
				"keterangan" => $row['keterangan'],
				"url_picture" => $row['url_picture'],
				"ulasan" => $reviews 
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