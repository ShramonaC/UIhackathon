<?php
define('HOST','localhost');
define('USER','id2452095_shramo');
define('PASS','shramo1');
define('DB','id2452095_company');
$con = mysqli_connect(HOST,USER,PASS,DB) ;
 if($_SERVER['REQUEST_METHOD']=='POST'){
 $Empid = $_POST['Empid'];
 $Name = $_POST['Name'];
 $Desig = $_POST['Desig'];
              $sql = "INSERT INTO Company (Empid,Name,Desig) VALUES ('$Empid','$Name','$Desig')";
             if(mysqli_query($con,$sql))
                {
                echo "Added new employee";
               }
         
 		}else{
			echo 'error';
		      }
 
?>