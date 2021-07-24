<?php
$Host_Name="localhost:3309";
$Host_User="root";
$Host_Pass="";
$Host_DbName="volleycrudfinal";
$conn = mysqli_connect($Host_Name, $Host_User, $Host_Pass, $Host_DbName);
$image = $_POST['image']; // Image insert
$imageStore = "IMG".rand().".jpeg"; // all type image save jpeg formet
file_put_contents("images/".$imageStore,base64_decode($image)); //file_put_contents its string encoded file converted base64_decode and save
$sql = "INSERT INTO `volley_image_upload`(`id`, `image`) VALUES (NULL,'$imageStore')";
$result=mysqli_query($conn,$sql);
 if($result == true){
         echo "Image upload success";  		
     }
     else{
         echo "Image upload failed";
     }
?>