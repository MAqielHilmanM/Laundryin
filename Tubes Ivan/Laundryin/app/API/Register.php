<?php
	include 'koneksi.php';

	
	class usr{}

	$username = $_POST["username"];
	$password = $_POST["password"];
	$nama = $_POST["nama"];
	$phone = $_POST["phone"];
	$alamat = $_POST["alamat"];

	if ((empty($nama))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "Kolom nama tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else if ((empty($password))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "Kolom password tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	} else if ((empty($password))) {
		$response = new usr();
		$response->status = 0;
		$response->message = "Kolom password tidak boleh kosong";
		$response->data = null;
		die(json_encode($response));
	}else {
		if (!empty($username) && !empty($password) && !empty($nama) && !empty($phone) && !empty($alamat)){
			$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM tb_pelanggan WHERE username='".$username."'"));

			if ($num_rows == 0){
				$query = mysqli_query($con, "INSERT INTO tb_pelanggan ( username, password, nama, alamat, phone) VALUES('".$username."','".$password."','".$nama."','".$alamat."','".$phone."')");

				if ($query){
					$response = new usr();
					$response->status = 1;
					$response->message = "Register berhasil, silahkan login.";
					$response->data = null;
					die(json_encode($response));

				} else {
					$response = new usr();
					$response->status = 0;
					$response->message = "Username sudah ada";
					$response->data = null;
					die(json_encode($response));
				}
			} else {
				$response = new usr();
				$response->status = 0;
				$response->message = "Username sudah ada";
				$response->data = null;
				die(json_encode($response));
			}
		}
	}

	mysqli_close($con);
?>