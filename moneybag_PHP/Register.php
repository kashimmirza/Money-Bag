<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		$Name = $_POST['Name'];
		$Email = $_POST['Email'];
		$Phone = $_POST['Phone'];
		$DOB = $_POST['DOB'];
		$Gender = $_POST['Gender'];		
		$Profession = $_POST['Profession'];
		$N_ID = $_POST['N_ID'];
		$Password = $_POST['Password'];
		//$Re_password = $_POST['Re_password'];
		$Pin = $_POST['Pin'];
		$PresentAddress = $_POST['Present_Address'];		
		$PresentDivision = $_POST['Present_Division'];
		$PresentDistrict = $_POST['Present_District'];
		$PresentThana = $_POST['Present_Thana'];
		$PermanentAddress = $_POST['Permanent_Address'];
		$PermanentDivision = $_POST['Permanent_Division'];
		$PermanentDistrict = $_POST['Permanent_District'];		
		$PermanentThana = $_POST['Permanent_Thana'];
		$UserID = $_POST['User_ID'];


require_once('dbConnect.php');
$sql = "SELECT * FROM user WHERE Email='$Email' OR Phone='$Phone'";

$check = mysqli_fetch_array(mysqli_query($con,$sql));

if(isset($check)){
echo 'Phone or email already exist. Please try again.';
}

else{
require_once('dbConnect.php');
		
		// $num= 1;
               
                // $newid = date("Y").$num.(rand(0,9999999));

		$sql = "INSERT INTO user (user_id,name,email,phone,date_of_birth,gender,profession,N_ID,password,pin,pre_address,pre_division,pre_district,
pre_thana,per_address,per_division,per_district,per_thana) VALUES ('$UserID','$Name','$Email','$Phone','$DOB','$Gender','$Profession','$N_ID','$Password','$Pin','$PresentAddress','$PresentDivision','$PresentDistrict','$PresentThana','$PermanentAddress','$PermanentDivision','$PermanentDistrict','$PermanentThana')";
		 //$num=$_SESSION['myValue'];
			     //  $result = mysql_query("insert into tbl_user (id) values($newid)");
				   
if(mysqli_query($con,$sql)){
echo 'Successfully registered';
}else{
echo 'oops! Please try again!';
}
}
mysqli_close($con);

} else{
echo 'error';
}

?>
