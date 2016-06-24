<?php
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




/*


require_once('dbConnect.php');

$sql = "SELECT * FROM user WHERE email='".$Email." ' " ;

//$sql = "SELECT * FROM user WHERE email='".$Email." ' AND phone='".$phone." ' " ;

//$sql = "SELECT * FROM user WHERE phone='".$phone." ' " ;

$result = mysqli_query($con,$sql);

$check = mysqli_fetch_array($result);

$User = array();

array_push($User,array(
"name"=>$check['name'],
"userId"=>$check['user_id'],
"phone"=>$check['phone'],
"dob"=>$check['date_of_birth'],
"profession"=>$check['profession'],
"present_add"=>$check['pre_address'],
"pin"=>$check['pin'],
"password"=>$check['password']
)
);

echo json_encode($User);

mysqli_close($con);

}

*/

?>
