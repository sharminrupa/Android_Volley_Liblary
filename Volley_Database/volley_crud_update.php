<?php
$Host_Name="localhost:3307";
$Host_User="root";
$Host_Pass="";
$Host_DbName="volleycrudfinal";
$conn = mysqli_connect($Host_Name, $Host_User, $Host_Pass, $Host_DbName);
$id = $_POST["id"];
$name = $_POST["name"];
$email = $_POST["email"];
$contact = $_POST["contact"];
$address = $_POST["address"];
$sql = "UPDATE `volley_crud_table` SET `name`='$name',`email`='$email',`contact`='$contact',`address`='$address' WHERE id = '$id' ";
$result=mysqli_query($conn,$sql);
 if($result){
         echo "Data update success";        
     }
     else{
         echo "Data update Failed";
     }
mysqli_close($conn);
?>