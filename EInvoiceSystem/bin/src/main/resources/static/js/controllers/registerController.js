/**
 * 
 */

//	Controller
app.controller("RegisterController", function($scope, $http, $window){
	
	$scope.fullname = "";
	$scope.email = "";
	$scope.password = "";
	$scope.repass = "";
	$scope.error = "";
	
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