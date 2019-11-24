<?php
	include 'koneksi.php';

	// $username = $_POST['username'];
	// $query = mysqli_query($con, "select * from tb_order where username = '".$username."'");
	$query = mysqli_query($con, "select * from tb_order");
	if(mysqli_num_rows($query)>0){
		$response = array();
		$response["order"] = array();

		while ($data = mysqli_fetch_array($query)) {
			$result['id_order'] = $data['id_order'];
			$result['tgl_order'] = $data['tgl_order'];
			$result['username'] = $data['username'];
			$result['id_paket'] = $data['id_paket'];
			array_push($response['order'],$result);
		}
		echo json_encode($response);
	}else{
		$response['message'] = "Tidak ada data";
		echo json_encode($response);
	}

?>