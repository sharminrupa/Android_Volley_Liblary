<?php
$Host_Name="localhost:3307";
$Host_User="root";
$Host_Pass="";
$Host_DbName="volleycrudfinal";
$conn = mysqli_connect($Host_Name, $Host_User, $Host_Pass, $Host_DbName);
$id = $_POST["id"];
$sql = "DELETE FROM `volley_crud_table` WHERE id='$id'";
$result=mysqli_query($conn,$sql);
 if($result){
         echo "Data Deleted success";        
     }
     else{
         echo "Data Delete Failed";
     }
mysqli_close($conn);
?>