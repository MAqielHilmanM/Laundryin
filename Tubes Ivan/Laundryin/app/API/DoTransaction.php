<?php
	include 'koneksi.php';

	
	class usr{}

	$idOrder = $_POST["id_order"];
	$totalBayar = $_POST["total_bayar"];

	if ((empty($idOrder))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "id order tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($totalBayar))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Total Bayar tidak boleh kosong";
		die(json_encode($response));
	} else {
		$query = mysqli_query($con, "INSERT INTO `tb_transaksi` (`id_order`, `total_bayar`) VALUES ('$idOrder', '$totalBayar')");

		if ($query){
			$response = new usr();
			$response->success = 1;
			$response->message = "Berhasil menambahkan transaksi";
			die(json_encode($response));

		} else {
			$response = new usr();
			$response->success = 0;
			$response->message = "Gagal menambahkan transaksi";
			die(json_encode($response));
		}
	}

	mysqli_close($con);
?>