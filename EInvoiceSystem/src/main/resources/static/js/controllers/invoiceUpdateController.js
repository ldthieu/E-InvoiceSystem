/**
 * 
 */

// Controller
app.controller("InvoiceUpdateController", function($scope, $http, $window, $location) {

	$scope.invoiceNo = "";
	$scope.customerCode = "";
	$scope.amountOfMoney = 0;
	$scope.vat = 0;
	$scope.createdDate = new Date();
	$scope.service = {};
	$scope.services = [];
	$scope.error = {
		status : 0,
		data : ""
	};
	$scope.id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
	$scope.init = function() {
		$http.get("/service/get").then(function(response) {
			$scope.services = response.data;
			$scope.service = $scope.services[0];
		}, function(errResponse) {
			console.log("load services failed!");
			console.log(errResponse);
		});
		
		let id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
		
		$http.get("/invoice/get/"+ $scope.id).then(function(response) {
			console.log(response.data);
			$scope.id = response.data.id;
			$scope.invoiceNo = response.data.invoiceNo;
			$scope.customerCode = response.data.customerCode;
			$scope.vat = response.data.vat;
			$scope.createdDate = new Date(response.data.createdDate);
			$scope.amountOfMoney = response.data.amountOfMoney;
			for(s of $scope.services){
				if(s.id == response.data.service.id){
					$scope.service = s;
				}
			}
		
		}, function(errResponse) {
			console.log("load invoice failed!");
			console.log(errResponse);
		});
	};

	$scope.update = function() {

		if (!checkInputValid()) {
			$scope.error = "400";
			return;
		}

		var data = {
			id: $scope.id,
			invoiceNo : $scope.invoiceNo,
			customerCode : $scope.customerCode,
			amountOfMoney : $scope.amountOfMoney,
			invoiceNo : $scope.invoiceNo,
			vat : $scope.vat,
			createdDate : $scope.createdDate,
			service : $scope.service
		}

		$http.put("/invoice/update/", data).then(function(response) {
			$window.location.href = '/invoice';
		}, function(errResponse) {
			console.log(errResponse);
			if (errResponse.status == 409) {
				$scope.error.status = 409;
				$scope.error.data = "Duplication of monthly invoice!";
			} else if (errResponse.status == 400) {
				$scope.error.status = 400;
				$scope.error.data = "Bad request!";
			} else {
				$scope.error.status = errResponse.status
				$scope.error.data = "request fail with status: " + errResponse.status;
			}
		});
	};

	function checkInputValid() {
		if (!$scope.invoiceNo || !$scope.customerCode
				|| $scope.amountOfMoney < 0 || $scope.vat < 0
				|| !$scope.service) {
			return false;
		}
		return true;
	}
});