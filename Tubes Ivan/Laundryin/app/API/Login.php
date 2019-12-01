<?php
 include_once "koneksi.php";

	class usr{}
	
	$username = $_POST["username"];
	$password = $_POST["password"];
	
	if ((empty($username)) || (empty($password))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom tidak boleh kosong"; 
		die(json_encode($response));
	}
	
	$query = mysqli_query($con, "SELECT * FROM tb_pelanggan WHERE username='$username' AND password='$password'");

	
	if ($query){
		$row = mysqli_fetch_array($query);

		$response = new usr();
		$response->success = 1;
		$response->message = "Login success";
		$response->data = array(
			"username" => $row['username'],
			"nama" => $row['nama'],
			"alamat" => $row['alamat'],
			"email" => $row['email'],
			"pakaian" => $row['pakaian']
		);

		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = $query->error;
		die(json_encode($response));
	}
	
	mysqli_close($con);
?>