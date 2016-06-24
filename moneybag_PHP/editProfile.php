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

$Name = $_POST['newName'];
$PrAdd = $_POST['newPrAdd'];
$Prof = $_POST['newProf'];

		
		
		//require_once('dbConnect.php');
		$sql=mysqli_query($con,"update user set name= '".$Name." ' where email='".$Value." '");
		$sql1=mysqli_query($con,"update user set profession= '".$Prof." ' where email='".$Value." '");
		$sql2=mysqli_query($con,"update user set pre_address= '".$PrAdd." ' where email='".$Value." '");
	
	
		
		
		echo "Data updated successfully";
	
	

//mysqli_close($con);

}else {

require_once('dbConnect.php');

$Name = $_POST['newName'];
$PrAdd = $_POST['newPrAdd'];
$Prof = $_POST['newProf'];

		
		
		//require_once('dbConnect.php');
		$sql=mysqli_query($con,"update user set name= '".$Name." ' where phone='".$Value." '");
		$sql1=mysqli_query($con,"update user set profession= '".$Prof." ' where phone='".$Value." '");
		$sql2=mysqli_query($con,"update user set pre_address= '".$PrAdd." ' where phone='".$Value." '");
		
		
		
		echo "Data updated successfully";


//mysqli_close($con);

}

}