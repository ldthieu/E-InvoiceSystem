/**
 * 
 *//**
 * 
 */

//	Controller
app.controller("ReportController", function($scope, $http, $window, $timeout){
	
	$scope.services = [];
	$scope.invoices = [];
	$scope.service = {};
	$scope.sum = 0;
	$scope.start = new Date();
	$scope.end = new Date();
	
	$scope.init = function() {
		$http.get("/service/get").then(function(response) {
			$scope.services = response.data;
			$scope.service = $scope.services[0];
		}, function(errResponse) {
			console.log("load services failed!");
			console.log(errResponse);
		});
	};
	
	$scope.getInvoice = function(){
		let startTime = $scope.start.getTime();
		let endTime = $scope.end.getTime();
		console.log($scope.service.id);
		console.log(startTime);
		console.log(endTime);
		$http.get("/invoice/report/"+$scope.service.id+"/"+startTime+"/"+endTime)
		    .then(function (response) {
		    	console.log(response);
		    	$scope.invoices = response.data;
		    	$scope.sum = getSum();	
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
	
	function getSum(){
		let s = 0;
		for(i of $scope.invoices){
			s += i.totalMoney;
		}
		return s;
	}
});