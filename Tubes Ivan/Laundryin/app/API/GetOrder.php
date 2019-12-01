<?php
 include_once "koneksi.php";

	class usr{}
	
	$id = $_GET['id_order'];

	$query = mysqli_query($con, "SELECT * FROM tb_order WHERE id_order='$id'");
			
	if ($query){
		$response = new usr();
		$response->success = 1;
		$response->message = "Ambil Order Berhasil";
		$datas = array();

		while($row = mysqli_fetch_assoc($query)){
			$datas = array(
				"id_order" => $row['id_order'],
				"tgl_order" => $row['tgl_order'],
				"tgl_selesai" => $row['tgl_selesai'],
				"username" => $row['username'],
				"status" => $row['status']
			);		
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