/**
 * 
 */

// Controller
app.controller("InvoiceUpdateController", function($scope, $http, $window) {

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
	$scope.init = function() {
		$http.get("/service/get").then(function(response) {
			$scope.services = response.data;
			$scope.service = $scope.services[0];
		}, function(errResponse) {
			console.log("load services failed!");
			console.log(errResponse);
		});
	};

	$scope.create = function() {

		if (!checkInputValid()) {
			$scope.error = "400";
			return;
		}

		var data = {
			invoiceNo : $scope.invoiceNo,
			customerCode : $scope.customerCode,
			amountOfMoney : $scope.amountOfMoney,
			invoiceNo : $scope.invoiceNo,
			vat : $scope.vat,
			createdDate : $scope.createdDate,
			service : $scope.service
		}
		
		console.log(data);

		$http.post("/invoice/create", data).then(function(response) {
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