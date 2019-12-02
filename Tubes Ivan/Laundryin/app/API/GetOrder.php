<?php
 include_once "koneksi.php";

	class usr{}
	
	$id = $_GET['id_order'];

	$query = mysqli_query($con, "SELECT * FROM tb_paketlaundry p JOIN tb_order o ON p.id_paket = o.id_paket LEFT JOIN tb_transaksi t ON o.id_order = t.id_order WHERE o.id_order='$id'");
			
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
				"id_paket" => $row['id_paket'],
				"catatan" => $row['catatan'],
				"address" => $row['address'],
				"status" => $row['status'],
				"nama_paket" => $row['nama_paket'],
				"harga_paket" => $row['harga_paket'],
				"estimasi_paket" => $row['estimasi_paket'],
				"keterangan" => $row['keterangan'],
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