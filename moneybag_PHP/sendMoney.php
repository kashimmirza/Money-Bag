<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	
	//$Email  = $_GET['Email'];
$Value = $_GET['Myval'];
//$phone = $_POST['Phone'];
$password = $_GET['Password'];
	//$email = $_GET['Email'];
	//$password = $_GET['Password'];
	
if (strpos($Value, '@') !== false){
require_once('dbConnect.php');

		//$email = $_POST['Email'];// This 'Email' is from KEY_EMAIL = "Email"
		$SendAmount = $_POST['SendAmount']; // This 'SendAmount' is from KEY_SAMOUNT = "SendAmount"
		$rID = $_POST['ReceiverID']; // This 'ReceiverID' is from KEY_RECEIVERID = "ReceiverID"
		$sID = $_POST['User_ID']; // This 'User_ID' is from KEY_USERID = "User_ID"
		
	
		
		
		require_once('dbConnect.php');
		

		$tID = rand(10,99999999999);
		$sql = "SELECT * FROM user WHERE user_id='$rID'";

$check = mysqli_fetch_array(mysqli_query($con,$sql));

if(!isset($check)){
echo 'This ID does not exist';
}

else{
//require_once('dbConnect.php');

		$sql =  mysqli_query($con,"update user set balance = ( balance - '$SendAmount') where user_id ='$sID'");
		$sql2 = mysqli_query($con,"INSERT INTO transaction (transaction_id,sender_id,receiver_id,amount) VALUES ('$tID','$sID','$rID','$SendAmount')");
		$sql3 = mysqli_query($con,"update user set balance = (balance + '$SendAmount') where user_id ='$rID'") ;
		echo "Balance sent to receiver ".$rID ." successfully";
		

}
		
		
		
		

}

else{
require_once('dbConnect.php');

		//$email = $_POST['Email']// This 'Email' is from KEY_EMAIL = "Email"
		$SendAmount = $_POST['SendAmount']; // This 'SendAmount' is from KEY_SAMOUNT = "SendAmount"
		$rID = $_POST['ReceiverID']; // This 'ReceiverID' is from KEY_RECEIVERID = "ReceiverID"
		$sID = $_POST['User_ID']; // This 'User_ID' is from KEY_USERID = "User_ID"
		
	
		
		
		require_once('dbConnect.php');
		

		$tID = rand(10,99999999999);
		$sql = "SELECT * FROM user WHERE user_id='$rID'";

$check = mysqli_fetch_array(mysqli_query($con,$sql));

if(!isset($check)){
echo 'This ID does not exist';
}

else{
//require_once('dbConnect.php');

		$sql =  mysqli_query($con,"update user set balance = ( balance - '$SendAmount') where user_id ='$sID'");
		$sql2 = mysqli_query($con,"INSERT INTO transaction (transaction_id,sender_id,receiver_id,amount) VALUES ('$tID','$sID','$rID','$SendAmount')");
		$sql3 = mysqli_query($con,"update user set balance = (balance + '$SendAmount') where user_id ='$rID'") ;
		echo "Balance sent to receiver ".$rID ." successfully";
		

}


}
	
	
}	
	
	
	
	/*
if($_SERVER['REQUEST_METHOD']=='GET'  ){

//$Email  = $_GET['Email'];
$Value = $_GET['Myval'];
//$phone = $_POST['Phone'];
$password = $_GET['Password'];

if (strpos($Value, '@') !== false){
require_once('dbConnect.php');

$sql = "SELECT * FROM user WHERE email='".$Value." ' AND password='".$password." '" ;

//$sql = "SELECT * FROM user WHERE email='".$Email." ' AND phone='".$phone." ' " ;

//$sql = "SELECT * FROM user WHERE phone='".$phone." ' " ;

$result = mysqli_query($con,$sql);

$check = mysqli_fetch_array($result);

$User = array();

array_push($User,array(
"name"=>$check['name'],
"email"=>$check['email'],
"userId"=>$check['user_id'],
"phone"=>$check['phone'],
"dob"=>$check['date_of_birth'],
"profession"=>$check['profession'],
"present_add"=>$check['pre_address'],
"pin"=>$check['pin'],
"password"=>$check['password'],
"balance" =>$check['balance']
)
);

echo json_encode($User);

mysqli_close($con);

}else {

require_once('dbConnect.php');

$sql = "SELECT * FROM user WHERE phone='".$Value." ' AND password='".$password." '" ;

//$sql = "SELECT * FROM user WHERE email='".$Email." ' " ;

//$sql = "SELECT * FROM user WHERE email='".$Email." ' AND phone='".$phone." ' " ;

//$sql = "SELECT * FROM user WHERE phone='".$phone." ' " ;

$result = mysqli_query($con,$sql);

$check = mysqli_fetch_array($result);

$User = array();

array_push($User,array(
"name"=>$check['name'],
"email"=>$check['email'],
"userId"=>$check['user_id'],
"phone"=>$check['phone'],
"dob"=>$check['date_of_birth'],
"profession"=>$check['profession'],
"present_add"=>$check['pre_address'],
"pin"=>$check['pin'],
"password"=>$check['password'],
"balance" =>$check['balance']
)
);

echo json_encode($User);

mysqli_close($con);

}

}

*/
	