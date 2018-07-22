/**
 * 
 */
//var app = angular.module("myApp", []);
//	Controller
app.controller("RegisterController", function($scope, $http){
	
	$scope.fullname = "";
	$scope.email = "";
	$scope.password = "";
	$scope.repass = "";
	
	$scope.register = function (){
		
	};
	
	function checkValid(){
		
		if(!$scope.fullname || !$scope.email || !$scope.password || !$scope.repass){
			return false;
		}
		
		if($scope.password !== $scope.repass){
			return false;
		}
		
		return true;
	}
});