<?php
	include 'koneksi.php';

	
	class usr{}

	$username = $_POST["username"];
	$idPaket = $_POST["id_paket"];

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
		$tgl_selesai=Date('Y-m-d H:i:s', strtotime("+4 days"));
		
		$queryPaket = mysqli_query($con, "SELECT estimasi_paket FROM tb_paketlaundry WHERE id_paket='$idPaket'");
		if ($queryPaket){
			$rowPaket = mysqli_fetch_array($queryPaket);

			$tgl_selesai=Date('Y-m-d H:i:s', strtotime("+".$rowPaket['estimasi_paket']." days"));
		}
	
		$query = mysqli_query($con, "INSERT INTO `tb_order` (`tgl_selesai`, `username`, `id_paket`) VALUES ('$tgl_selesai', '$username', '$idPaket')");

		if ($query){

			$query2 = mysqli_query($con, "SELECT * FROM tb_order WHERE username='$username' AND id_paket='$idPaket' ORDER BY id_order ASC");
					
			if ($query2){
				$response = new usr();
				$response->status = 1;
				$response->message = "Menambahkan Order Berhasil";
				$datas = array();
		
				while($row = mysqli_fetch_assoc($query2)){
					$datas = array(
						"id_order" => $row['id_order'],
						"tgl_order" => $row['tgl_order'],
						"tgl_selesai" => $row['tgl_selesai'],
						"username" => $row['username'],
						"status" => $row['status']
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