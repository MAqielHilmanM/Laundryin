<?php
	include 'koneksi.php';

	
	class usr{}

	$username = $_POST["username"];
	$idPaket = $_POST["id_paket"];
	$ulasan = $_POST["ulasan"];

	if ((empty($idPaket))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "id paket tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else if ((empty($username))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "Username tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else {
		$query = mysqli_query($con, "INSERT INTO `tb_ulasan` (`id_paket`, `username`, `ulasan`) VALUES ('$idPaket', '$username', '$ulasan')");

		if ($query){
			$query2 = mysqli_query($con, "SELECT LAST_INSERT_ID() as lastId");
					
			if ($query2){
				$response = new usr();
				$response->status = 1;
				$response->message = "Menambahkan Order Berhasil";
				$datas = array();
		
				while($row = mysqli_fetch_assoc($query2)){
					$datas = array(
						"id_ulasan" => $row['lastId'],
						"id_paket" => $idPaket,
						"username" => $username,
						"ulasan" => $ulasan
					);		
				}
				
				$response->data = $datas;
		
				die(json_encode($response));
				
			}
		} else {
			$response = new usr();
			$response->status = 0;
			$response->message = "Gagal menambahkan order";
			$response->data = null;
			die(json_encode($response));
		}
	}

	mysqli_close($con);
?>