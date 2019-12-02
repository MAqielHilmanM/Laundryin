<?php
	include 'koneksi.php';

	
	class usr{}

	$id_order = $_POST["id_order"];
	$status = $_POST["status"];

	if ((empty($status))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "id paket tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else {
		$query = mysqli_query($con, "UPDATE `tb_order` SET `status` = '$status' WHERE `tb_order`.`id_order` = '$id_order'");
		if ($query){
			$query2 = mysqli_query($con, "SELECT * FROM tb_order WHERE `id_order` = '$id_order'");
					
			if ($query2){
				$response = new usr();
				$response->status = 1;
				$response->message = "Mengubah status Order Berhasil";
				$datas = array();
		
				while($row = mysqli_fetch_assoc($query2)){
					$datas = array(
						"id_order" => $row['id_order'],
						"tgl_order" => $row['tgl_order'],
						"tgl_selesai" => $row['tgl_selesai'],
						"username" => $row['username'],
						"id_paket" => $row['id_paket'],
						"catatan" => $row['catatan'],
						"address" => $row['address'],
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