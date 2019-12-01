<?php
 include_once "koneksi.php";

	class usr{}
	
	$query = mysqli_query($con, "SELECT * FROM tb_paketlaundry");
			
	if ($query){
		$response = new usr();
		$response->success = 1;
		$response->message = "Ambil Paket Berhasil";
		$datas = array();

		while($row = mysqli_fetch_assoc($query)){
			$reviews = array();
			
			$query2 = mysqli_query($con, "SELECT * FROM tb_ulasan WHERE id_paket = '".$row['id_paket']."'");
		
			while($row2 = mysqli_fetch_assoc($query2)){
				array_push($reviews,array(
					"id" => $row2['id'],
					"id_paket" => $row2['id_paket'],
					"username" => $row2['username'],
					"ulasan" => $row2['ulasan'],
					"entry_date" => $row2['entry_date']
				));		
			}

			array_push($datas,array(
				"id_paket" => $row['id_paket'],
				"nama_paket" => $row['nama_paket'],
				"harga_paket" => $row['harga_paket'],
				"estimasi_paket" => $row['estimasi_paket'],
				"keterangan" => $row['keterangan'],
				"url_picture" => $row['url_picture'],
				"ulasan" => $reviews 
			));		
		}
		
		$response->data = $datas;

		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = $query->error;
		die(json_encode($response));
	}
	
	mysqli_close($con);
?>