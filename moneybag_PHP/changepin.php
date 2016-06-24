<?php

/*		
		
	if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$email = $_GET['Email'];
	
		
		$Pin = $_POST['newPin'];
		
		
		
		require_once('dbConnect.php');
		$sql="update user set pin='$Pin' where email='$email'";
		
	
		

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


$Pin = $_POST['newPin'];
		
		
		//require_once('dbConnect.php');
		$sql="update user set pin= '".$Pin." ' where email='".$Value." '";
		
	
		

		if(mysqli_query($con,$sql)){
			 echo "Pin updated successfully";
} else {
   print mysql_error();
		

	}
//mysqli_close($con);

}else {

require_once('dbConnect.php');

$Pin = $_POST['newPin'];
		
		
		//require_once('dbConnect.php');
		$sql="update user set pin= '".$Pin." ' where phone='".$Value." '";
		
	
		

		if(mysqli_query($con,$sql)){
			 echo "Pin updated successfully";
} else {
   print mysql_error();
		

	}

//mysqli_close($con);

}

}
	