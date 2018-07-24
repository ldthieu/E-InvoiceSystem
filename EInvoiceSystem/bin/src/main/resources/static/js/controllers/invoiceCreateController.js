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
	$scope.service = "";
	$scope.services = [];
	$scope.init = function(){
		$http.get("/services")
		    .then(function (response) {
		    	//$window.location.href = '/login?register';
		    	$scope.services = response.data;
		    	$scope.service = $scope.services[0].serviceName;
		    	console.log(response);
		    },
		    function(errResponse){
		    	console.log("load services failed!");
		    	console.log(errResponse);
		    }
	    );
	}
//	$http.get("/services")
//	    .then(function (response) {
//	    	//$window.location.href = '/login?register';
//	    	$scope.services = response.data;
//	    	console.log(response);
//	    },
//	    function(errResponse){
//	    	console.log("load services failed!");
//	    	console.log(errResponse);
//	    }
//    );
});