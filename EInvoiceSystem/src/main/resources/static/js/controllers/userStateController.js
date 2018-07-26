/**
 * 
 */

// Controller
app.controller("UserStateController", function($scope, $http, $timeout) {

	$scope.user = {};
	$scope.users = [];
	$scope.status = 0;
	
	$scope.init = function() {
		$http.get("/user/get").then(function(response) {
			$scope.users = response.data;
			$scope.user = $scope.users[0];
		}, function(errResponse) {
			console.log("load users failed!");
			console.log(errResponse);
		});	
	};

	$scope.update = function() {
		var data = $scope.user;

		$http.put("/user/state", data).then(function(response) {
			$scope.status = 1;
			$timeout(function(){
				$scope.status = 0;
			}, 2000);
		}, function(errResponse) {
			console.log(errResponse);
		});
	};
});