/**
 * 
 *//**
 * 
 */

//	Controller
app.controller("InvoiceController", function($scope, $http, $window){
	
//	$scope.invoiceNo = "";
//	$scope.customerCode = "";
//	$scope.amountOfMoney = 0;
//	$scope.vat = 0;
//	$scope.createdDate = new Date();
//	$scope.service = {};
	$scope.services = [];
	$scope.getInvoice = function(){
		$http.get("/invoice/get")
		    .then(function (response) {
		    	console.log(response);
		    	$scope.services = response.data;
		    },
		    function(errResponse){
		    	console.log(errResponse);
		    }
	    );
	};
});