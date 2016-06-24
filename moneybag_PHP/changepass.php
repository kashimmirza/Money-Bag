

<?php

	
	/*	
	if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$email = $_GET['Email'];
	
		
		$Password = $_POST['newPassword'];
		
		
		require_once('dbConnect.php');
		$sql="update user set password= '$Password' where email='$email'";
		
	
		

		if(mysqli_query($con,$sql)){
			 echo "Record updated successfully";
} else {
   print mysql_error();
		

	}
	}
	
*/	
	
if($_SERVER['REQUEST_METHOD']=='POST'  ){

//$Email  = $_GET['Email'];
$Value = $_GET['Myval'];
//$phone = $_POST['Phone'];
$password = $_GET['Password'];

if (strpos($Value, '@') !== false){
require_once('dbConnect.php');

$nPassword = $_POST['newPassword'];
		
		
		//require_once('dbConnect.php');
		$sql="update user set password= '".$nPassword." ' where email='".$Value." '";
		
	
		

		if(mysqli_query($con,$sql)){
			 echo "Password updated successfully";
} else {
   print mysql_error();
		

	}
//mysqli_close($con);

}else {

require_once('dbConnect.php');

$nPassword = $_POST['newPassword'];
		
		
		//require_once('dbConnect.php');
		$sql="update user set password= '".$nPassword." ' where phone='".$Value." '";
		
	
		

		if(mysqli_query($con,$sql)){
			 echo "Password updated successfully";
} else {
   print mysql_error();
		

	}

//mysqli_close($con);

}

}
	
	