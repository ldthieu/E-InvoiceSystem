/**
 * 
 *//**
 * 
 */

//	Controller
app.controller("InvoiceController", function($scope, $http, $window, $timeout){
	
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
	
	$scope.delete = function(id){
		console.log(id)
		var r = confirm("Press OK to delete this invoice!");
		if (r == true){
			$http.delete("/invoice/delete/"+id)
			    .then(function (response) {
			    	console.log(response);
			    	$window.location.href = '/invoice';
			    },
			    function(errResponse){
			    	console.log(errResponse);
			    }
		    );
		}
	}
});