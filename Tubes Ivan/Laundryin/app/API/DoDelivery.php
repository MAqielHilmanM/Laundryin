<?php
	include 'koneksi.php';

	
	class usr{}

	$kodeTransaksi = $_POST["kode_transaksi"];
	$idKurir = $_POST["id_kurir"];

	if ((empty($kodeTransaksi))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "kode Transaksi tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else if ((empty($idKurir))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "id kurir tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else {

		$query = mysqli_query($con, "INSERT INTO `tb_delivery` (`kode_transaksi`, `id_kurir`) VALUES ('$kodeTransaksi', '$idKurir')");

		if ($query){
			$query2 = mysqli_query($con, "SELECT * FROM tb_delivery WHERE kode_transaksi = '$kodeTransaksi' AND id_kurir='$idKurir' ORDER BY id_delivery ASC");
			if($query2){
				$response = new usr();
				$response->status = 1;
				$response->message = "Menambahkan delivery Berhasil";
				$datas = array();
		
				while($row = mysqli_fetch_assoc($query2)){
					$datas = array(
						"id_delivery" => $row['id_delivery'],
						"kode_transaksi" => $row['kode_transaksi'],
						"id_kurir" => $row['id_kurir'],
						"tgl_delivery" => $row['tgl_delivery']
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