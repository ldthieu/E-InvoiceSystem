/**
 * 
 */

//	Controller
app.controller("InvoiceCreateController", function($scope, $http, $window){
	
	$scope.invoiceNo = "";
	$scope.customerCode = "";
	$scope.amountOfMoney = 0;
	$scope.vat = 0;
	$scope.error = "";
	$scope.createdDate = new Date();
	$scope.service = [];
	
	$http.get("/services")
	    .then(function (response) {
	    	$window.location.href = '/login?register';
	    },
	    function(errResponse){
	    	//$scope.error = "Invalid input!"
	    	if(errResponse.status == 409){
	    		$scope.error = "Email already is used!"
	    	}
	    	if(errResponse.status == 400){
	    		$scope.error = "Invalid input!"
	    	}
	    }
    );
	
	$scope.register = function (){
		
		if(!isInputValid()){
			$scope.error = "Invalid input!"
			return;
		}
		
		console.log("data");
		
		var data = {
			fullname: $scope.fullname,
			email: $scope.email,
			password: $scope.password
		};
		
		$http.post("/user/register", data)
	        .then(function (response) {
	        	$window.location.href = '/login?register';
	        },
	        function(errResponse){
	        	//$scope.error = "Invalid input!"
	        	if(errResponse.status == 409){
	        		$scope.error = "Email already is used!"
	        	}
	        	if(errResponse.status == 400){
	        		$scope.error = "Invalid input!"
	        	}
	        }
        );
	};
	
	function isInputValid(){
		
		if(!$scope.fullname || !$scope.email || !$scope.password || !$scope.repass){
			return false;
		}
		
		if($scope.password !== $scope.repass){
			return false;
		}
		
		return true;
	}
});