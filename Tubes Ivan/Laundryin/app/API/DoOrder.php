<?php
	include 'koneksi.php';

	
	class usr{}

	$username = $_POST["username"];
	$idPaket = $_POST["id_paket"];

	if ((empty($idPaket))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "id paket tidak boleh kosong";
		die(json_encode($response));
	} else if ((empty($username))) {
		$response = new usr();
		$response->success = 0;
		$response->message = "Username tidak boleh kosong";
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
			$response = new usr();
			$response->success = 1;
			$response->message = "Berhasil menambahkan order";
			die(json_encode($response));

		} else {
			$response = new usr();
			$response->success = 0;
			$response->message = "Gagal menambahkan order";
			die(json_encode($response));
		}
	}

	mysqli_close($con);
?>