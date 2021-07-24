<?php
$Host_Name="localhost:3307";
$Host_User="root";
$Host_Pass="";
$Host_DbName="volleycrudfinal";
$conn = mysqli_connect($Host_Name, $Host_User, $Host_Pass, $Host_DbName);
$Insert_name = $_POST['name'];
$Insert_email= $_POST['email'];
$Insert_password= $_POST['password'];
$sql="INSERT INTO `volley_registation_login`(`Name`, `Email`, `Password`) VALUES ('$Insert_name','$Insert_email','$Insert_password')";
$run = mysqli_query($conn,$sql);
if($run){
	echo "Data Insert Successfully";
}else {
	echo "Data Insert Failed";
}
mysqli_close($conn);
?>