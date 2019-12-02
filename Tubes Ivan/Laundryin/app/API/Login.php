<?php
 include_once "koneksi.php";

	class usr{}
	
	$username = $_POST["username"];
	$password = $_POST["password"];
	
	if ((empty($username)) || (empty($password))) { 
		$response = new usr();
		$response->status = 0;
		$response->message = "Kolom tidak boleh kosong"; 
		$response->data = null;
		die(json_encode($response));
	}
	
	$query = mysqli_query($con, "SELECT * FROM tb_pelanggan WHERE username='$username' AND password='$password'");

	$num = mysqli_num_rows($query);
	if ($num > 0){
		$row = mysqli_fetch_array($query);

		$response = new usr();
		$response->status = 1;
		$response->message = "Login success";
		$response->data = array(
			"username" => $row['username'],
			"nama" => $row['nama'],
			"alamat" => $row['alamat'],
			"phone" => $row['phone'],
			"pakaian" => $row['pakaian']
		);

		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->status = 0;
		$response->message = "Login gagal";
		$response->data = null;
		die(json_encode($response));
	}
	
	mysqli_close($con);
?>