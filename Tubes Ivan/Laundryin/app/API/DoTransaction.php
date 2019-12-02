<?php
	include 'koneksi.php';

	
	class usr{}

	$idOrder = $_POST["id_order"];
	$totalBayar = $_POST["total_bayar"];

	if ((empty($idOrder))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "id order tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else if ((empty($totalBayar))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "Total Bayar tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else {
		$query = mysqli_query($con, "INSERT INTO `tb_transaksi` (`id_order`, `total_bayar`) VALUES ('$idOrder', '$totalBayar')");

		if ($query){

			$query2 = mysqli_query($con, "SELECT * FROM tb_transaksi WHERE id_order='$idOrder' AND total_bayar='$totalBayar' ORDER BY kode_transaksi ASC");
					
			if ($query2){
				$response = new usr();
				$response->status = 1;
				$response->message = "Menambahkan Tramsaksi Berhasil";
				$datas = array();
		
				while($row = mysqli_fetch_assoc($query2)){
					$datas = array(
						"kode_transaksi" => $row['kode_transaksi'],
						"id_order" => $row['id_order'],
						"total_bayar" => $row['total_bayar'],
						"tgl_transaksi" => $row['tgl_transaksi']
					);		
				}
				
				$response->data = $datas;
		
				die(json_encode($response));
				
			}

		} else {
			$response = new usr();
			$response->status = 0;
			$response->message = "Gagal menambahkan transaksi";
			$response->data = null;
			die(json_encode($response));
		}
	}

	mysqli_close($con);
?>