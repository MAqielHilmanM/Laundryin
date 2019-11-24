<?php
	include 'koneksi.php';
	$query = mysqli_query($con, "select * from tb_transaksi");
	if(mysqli_num_rows($query)>0){
		$response = array();
		$response["transaksi"] = array();

		while ($data = mysqli_fetch_array($query)) {
			$result['kode_transaksi'] = $data['kode_transaksi'];
			$result['id_order'] = $data['id_order'];
			$result['total_bayar'] = $data['total_bayar'];
			$result['tgl_transaksi'] = $data['tgl_transaksi'];
			array_push($response['transaksi'],$result);
		}
		echo json_encode($response);
	}else{
		$response['message'] = "Tidak ada data";
		echo json_encode($response);
	}

?>