<?php
define('HOST','localhost');
define('USER','id2452095_shra');
define('PASS','shramo1');
define('DB','id2452095_comp');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$sql = "select Empid,Wt from Feedback";
 
$res = mysqli_query($con,$sql);
 if (!$res) {
    printf("Error: %s\n", mysqli_error($con));
    exit();
}

$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('Empid'=>$row[0],
'Wt'=>$row[1]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);
 
?>