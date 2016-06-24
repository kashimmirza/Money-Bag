<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 //Getting values 
 $Value = $_GET['Myval'];
 $password = $_GET['Password'];


if (strpos($Value, '@') !== false){
//Creating sql query
// $sql = "SELECT * FROM user WHERE phone='$phone' AND password='$password'";
//$sql = "SELECT * FROM user WHERE (email='$Email' OR phone='$phone') AND password='$password'";

$sql = "SELECT * FROM user WHERE email='$Value' AND password='$password'";
 
 //importing dbConnect.php script 
 require_once('dbConnect.php');
 
 //executing query
 $result = mysqli_query($con,$sql);
 
 //fetching result
 $check = mysqli_fetch_array($result);
 
 //if we got some result 
 if(isset($check)){
 //displaying success 
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 }

else {

//Creating sql query
// $sql = "SELECT * FROM user WHERE phone='$phone' AND password='$password'";
//$sql = "SELECT * FROM user WHERE (email='$Email' OR phone='$phone') AND password='$password'";

$sql = "SELECT * FROM user WHERE phone='$Value' AND password='$password'";
 
 //importing dbConnect.php script 
 require_once('dbConnect.php');
 
 //executing query
 $result = mysqli_query($con,$sql);
 
 //fetching result
 $check = mysqli_fetch_array($result);
 
 //if we got some result 
 if(isset($check)){
 //displaying success 
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 }




}
   // echo 'true';


/*

 //Creating sql query
// $sql = "SELECT * FROM user WHERE phone='$phone' AND password='$password'";
$sql = "SELECT * FROM user WHERE (email='$Email' OR phone='$phone') AND password='$password'";
 
 //importing dbConnect.php script 
 require_once('dbConnect.php');
 
 //executing query
 $result = mysqli_query($con,$sql);
 
 //fetching result
 $check = mysqli_fetch_array($result);
 
 //if we got some result 
 if(isset($check)){
 //displaying success 
 echo "success";
 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 }


*/
?>
