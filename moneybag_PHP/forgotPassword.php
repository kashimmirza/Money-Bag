<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 $Name = $_GET['Name'];
 $Email = $_GET['Email'];
 //$DOB = $_GET['DOB'];
$Phone = $_GET['Phone'];

 $to      = 'nameyour174@gmail.com';
 $subject = 'Fake sendmail test';
 $message = 'If we can read this, it means that our fake Sendmail setup works!';
 $headers = 'From: webmaster@sunkingbdltd.com';

 
 //Creating sql query
 //$sql = "SELECT * FROM user WHERE Name='$Name' AND Email='$Email' AND DOB='$DOB'";
//$sql = "SELECT * FROM user WHERE Email='$Email' AND Name='$Name'";
$sql = "SELECT * FROM user WHERE Name='$Name' AND Email='$Email' AND Phone='$Phone'";
 
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

if(mail($Email, $subject, $message, $headers)) {
    echo 'Email sent successfully!';
} else {
    die('Failure: Email was not sent!');
}

 }else{
 //displaying failure
 echo "failure";
 }
 mysqli_close($con);
 }
 
?>
