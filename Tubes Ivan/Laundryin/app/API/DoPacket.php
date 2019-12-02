<?php
	include 'koneksi.php';

	
	class usr{}

	$nama_paket = $_POST["nama_paket"];
	$harga_paket = $_POST["harga_paket"];
	$estimasi_paket = $_POST["estimasi_paket"];
	$keterangan = $_POST["keterangan"];
	$url_picture = $_POST["url_picture"];

	if ((empty($nama_paket))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "Nama Paket tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else if ((empty($harga_paket))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "Harga Paket tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else {

		$query = mysqli_query($con, "INSERT INTO `tb_paketlaundry` VALUES (null,'$nama_paket','$harga_paket','$estimasi_paket','$keterangan', '$url_picture')");

		if ($query){po
			$query2 = mysqli_query($con, "SELECT LAST_INSERT_ID() as lastId");
			if($query2){
				$response = new usr();
				$response->status = 1;
				$response->message = "Menambahkan delivery Berhasil";
				$datas = array();
		
				while($row = mysqli_fetch_assoc($query2)){
					$datas = array(
						"id_paket" => $row['lastId'],
						"nama_paket" => $nama_paket,
						"harga_paket" => $harga_paket,
						"estimasi_paket" => $estimasi_paket,
						"url_picture" => $url_picture
					);		
				}
				
				$response->data = $datas;
		
				die(json_encode($response));

			}
		} else {
			$response = new usr();
			$response->status = 0;
			$response->message = "Gagal menambahkan delivery";
			$response->data = null;
			die(json_encode($response));
		}
	}

	mysqli_close($con);
?>