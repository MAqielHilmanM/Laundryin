<?php
 include_once "koneksi.php";

	class usr{}
	
	$query = "";
	if(isset($_GET['username'])){
		$username = $_GET['username'];
		$query = mysqli_query($con, "SELECT * FROM tb_order WHERE username='$username'");
	}else {
		$query = mysqli_query($con, "SELECT * FROM tb_order");
	}
			
	if ($query){
		$response = new usr();
		$response->status = 1;
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
		$response->status = 0;
		$response->message = "Gagal mengambil order";
		$response->data = null;
		die(json_encode($response));
	}
	
	mysqli_close($con);
?>