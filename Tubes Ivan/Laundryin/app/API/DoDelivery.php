<?php
	include 'koneksi.php';

	
	class usr{}

	$kodeTransaksi = $_POST["kode_transaksi"];
	$idKurir = $_POST["id_kurir"];

	if ((empty($kodeTransaksi))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "kode Transaksi tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($idKurir))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "id kurir tidak boleh kosong";
		die(json_encode($response));
	} else {

		$query = mysqli_query($con, "INSERT INTO `tb_delivery` (`kode_transaksi`, `id_kurir`) VALUES ('$kodeTransaksi', '$idKurir')");

		if ($query){
			$response = new usr();
			$response->success = 1;
			$response->message = "Berhasil menambahkan delivery";
			die(json_encode($response));

		} else {
			$response = new usr();
			$response->success = 0;
			$response->message = "Gagal menambahkan delivery";
			die(json_encode($response));
		}
	}

	mysqli_close($con);
?>