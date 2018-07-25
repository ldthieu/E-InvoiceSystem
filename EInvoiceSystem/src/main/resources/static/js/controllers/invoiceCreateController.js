/**
 * 
 */

//	Controller
app.controller("InvoiceCreateController", function($scope, $http, $window){
	
	$scope.invoiceNo = "";
	$scope.customerCode = "";
	$scope.amountOfMoney = 0;
	$scope.vat = 0;
	$scope.createdDate = new Date();
	$scope.service = {};
	$scope.services = [];
	$scope.init = function(){
		$http.get("/service/get")
		    .then(function (response) {
		    	//$window.location.href = '/login?register';
		    	$scope.services = response.data;
		    	$scope.service = $scope.services[0];
		    	console.log(response);
		    },
		    function(errResponse){
		    	console.log("load services failed!");
		    	console.log(errResponse);
		    }
	    );
	}
	
	$scope.create = function(){
		var data = {
				invoiceNo: $scope.invoiceNo,
				customerCode: $scope.customerCode,
				amountOfMoney: $scope.amountOfMoney,
				invoiceNo: $scope.invoiceNo,
				vat: $scope.vat,
				createdDate: $scope.createdDate,
				service: $scope.service
		}
		
		$http.post("/invoice/create", data)
	        .then(
	        function (response) {
	        	console.log(response);
	        	//$window.location.href = '/login?register';
	        },
	        function(errResponse){
	        	console.log(errResponse);
	        	if(errResponse.status == 409){
	        		$scope.error = "Email already is used!"
	        	}
	        	if(errResponse.status == 400){
	        		$scope.error = "Invalid input!"
	        	}
	        });
	}
});