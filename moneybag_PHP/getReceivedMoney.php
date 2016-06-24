<?php
if($_SERVER['REQUEST_METHOD']=='GET'  ){

$uID  = $_GET['userID'];


			
require_once('dbConnect.php');


$sql = "SELECT * FROM transaction WHERE receiver_id = $uID";

$result = mysqli_query($con,$sql);



$User = array();
while($check = mysqli_fetch_array($result))
{array_push($User,array(
"tId"=>$check['transaction_id'],
"sId"=>$check['sender_id'],
"amount"=>$check['amount'],
"time"=>$check['time']
)
);
}
echo json_encode($User);


mysqli_close($con);

}

?>