<?php
define('HOST','localhost');
define('USER','id2452095_shra');
define('PASS','shramo1');
define('DB','id2452095_comp');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
  if($_SERVER['REQUEST_METHOD']=='POST'){
$Val = $_POST['Val']
if($Val == 'Work Environment'){
  $sqla = "select We from Feedback";
 $resulta =  mysqli_fetch_array(mysqli_query($con,$sqla));
  $result = array();
 
   while($row =$resulta){
     array_push($result,
     array('Val'=>$row[0]
    ));
   }
 echo json_encode(array("result"=>$resulta[0]));
      }
elseif($Val == 'Fooding'){
  $sqlb = "select Fo from Feedback";
$resultb =  mysqli_fetch_array(mysqli_query($con,$sqlb));
echo json_encode(array("result"=>$resultb[0]));
      }
elseif($Val == 'Work Culture'){
  $sqlc = "select Wc from Feedback";
$resultc =  mysqli_fetch_array(mysqli_query($con,$sqlc));
echo json_encode(array("result"=>$resultc[0]));
      }
elseif($Val == 'Flexibility'){
  $sqld = "select F from Feedback";
$resultd =  mysqli_fetch_array(mysqli_query($con,$sqld));
echo json_encode(array("result"=>$resultd[0]));
      }
else{
   echo "No checking occured";
    }
} else{
	echo 'error';
      }
 
?>