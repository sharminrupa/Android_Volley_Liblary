<?php
$Host_Name="localhost:3307";
$Host_User="root";
$Host_Pass="";
$Host_DbName="volleycrudfinal";
$conn = mysqli_connect($Host_Name, $Host_User, $Host_Pass, $Host_DbName);
$result = array();
$result['volley_crud_table'] = array();
$select = "SELECT * FROM `volley_crud_table`";
$response = mysqli_query($conn,$select);
while($row = mysqli_fetch_array($response)){
	$index['id'] = $row['0'];
	$index['name'] = $row['1'];
	$index['email'] = $row['2'];
	$index['contact'] = $row['3'];
	$index['address'] = $row['4'];	
	array_push($result['volley_crud_table'], $index);	
}
	$result["success"]="1";
	echo json_encode($result);
	mysqli_close($conn);
?>