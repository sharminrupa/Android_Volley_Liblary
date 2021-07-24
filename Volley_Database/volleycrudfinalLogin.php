<?php
$Host_Name="localhost:3307";
$Host_User="root";
$Host_Pass="";
$Host_DbName="volleycrudfinal";
$conn = mysqli_connect($Host_Name, $Host_User, $Host_Pass, $Host_DbName);
$Insert_email= $_POST['email'];
$Insert_password= $_POST['password'];
$sql="SELECT * FROM `volley_registation_login` WHERE email = '$Insert_email' AND password = '$Insert_password' ";
$run = mysqli_query($conn,$sql);
if($run->num_rows > 0){
	echo "Loged in successfully";
}else {
	echo "Loged in failed";
}
mysqli_close($conn);
?>