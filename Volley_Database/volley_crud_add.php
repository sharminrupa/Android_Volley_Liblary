<?php
$Host_Name="localhost:3307";
$Host_User="root";
$Host_Pass="";
$Host_DbName="volleycrudfinal";
$conn = mysqli_connect($Host_Name, $Host_User, $Host_Pass, $Host_DbName);
$Insert_name= $_POST['name'];
$Insert_email= $_POST['email'];
$Insert_contact= $_POST['contact'];
$Insert_address= $_POST['address'];
$sql="INSERT INTO `volley_crud_table`(`name`, `email`, `contact`, `address`) VALUES ('$Insert_name','$Insert_email','$Insert_contact','$Insert_address')";
$run = mysqli_query($conn,$sql);
if($run){
	echo "Data add successfully";
}else {
	echo "Data add failed";
}
mysqli_close($conn);
?>