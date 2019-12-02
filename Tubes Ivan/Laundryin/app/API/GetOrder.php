<?php
 include_once "koneksi.php";

	class usr{}
	
	$id = $_GET['id_order'];

	$query = mysqli_query($con, "SELECT * FROM tb_order o LEFT JOIN tb_transaksi t ON o.id_order = t.id_order WHERE o.id_order='$id'");
			
	if ($query){
		$response = new usr();
		$response->status = 1;
		$response->message = "Ambil Order Berhasil";
		$datas = array();

		while($row = mysqli_fetch_assoc($query)){
			$datas = array(
				"id_order" => $row['id_order'],
				"tgl_order" => $row['tgl_order'],
				"tgl_selesai" => $row['tgl_selesai'],
				"username" => $row['username'],
				"status" => $row['status'],
				"kode_transaksi" => $row['kode_transaksi'],
				"total_bayar" => $row['total_bayar'],
				"tgl_transaksi" => $row['tgl_transaksi']
			);		
		}
		
		$response->data = $datas;

		die(json_encode($response));
		
	} else { 
		$response = new usr();
		$response->status = 0;
		$response->message = "failed to get order";
		$response->data = null;
		die(json_encode($response));
	}
	
	mysqli_close($con);
?>