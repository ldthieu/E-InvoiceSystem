app.controller('serviceController', function($scope, $http) {
	$scope.name = "";
	$scope.senData=function(){
		$http({
			method: 'POST',
			url: 'service/create',
			data: {'serviceName': $scope.name}
			})
		
	     .then(
	    function(response) {
	            // success
	           console.log(response.status);
	    }, 
	    function(response) { // optional
	            // failed
	    	console.log(response.status) 
	    }
	    ); 
	}
}
);