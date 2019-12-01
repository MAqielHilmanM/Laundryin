<?php
 include_once "koneksi.php";

	class usr{}
	
	$username = $_GET['username'];
	
	$query = mysqli_query($con, "SELECT * FROM tb_order WHERE username='$username'");
			
	if ($query){
		$response = new usr();
		$response->success = 1;
		$response->message = "Ambil Order Berhasil";
		$datas = array();

		while($row = mysqli_fetch_assoc($query)){
			array_push($datas,array(
				"id_order" => $row['id_order'],
				"tgl_order" => $row['tgl_order'],
				"tgl_selesai" => $row['tgl_selesai'],
				"username" => $row['username'],
				"status" => $row['status']
			));		
		}
		
		$response->data = $datas;

		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->success = 0;
		$response->message = $query->error;
		die(json_encode($response));
	}
	
	mysqli_close($con);
?>