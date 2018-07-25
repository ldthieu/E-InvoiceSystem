//import myApp from '@angular/app';
// Controller Part
app.controller("ServiceController", function($scope, $http) {

	$scope.services = [];
	$scope.serviceForm = {
		monthly : false,
		serName : ""
	};
	// Now load the data from server
	_refreshServiceSData();

	// $scope.serviceForm.update to check create or edit
	$scope.submitService = function() {
		var data = [];
		if ($scope.serviceForm.update == -1) {
			data = {
				'serviceName' : $scope.serviceForm.serName,
				'monthly' : $scope.serviceForm.monthly,
				'id' : $scope.serviceForm.id
			};
			$http.put('/service/update', data).then(_success, _error);
		} else {
			data = {
				'serviceName' : $scope.serviceForm.serName,
				'monthly' : $scope.serviceForm.monthly
			};
			$http.post('service/create', data).then(_success, _error);
		}
	}

	// 
	$scope.createService = function() {
		_clearFormData();
	}

	// HTTP DELETE- delete employee by Id
	// Call: http://localhost:8080/employee/{empId}
			  
	$scope.deleteService = function(service) {
		$http({
			method : 'DELETE',
			url : '/service/' + service.id
		}).then(_success, _error);
	};
	// In case of edit
	$scope.editService = function(service) {
		$scope.serviceForm.serName = service.serviceName;
		$scope.serviceForm.monthly = service.monthly;
		$scope.serviceForm.id = service.id;
		$scope.serviceForm.update = -1;
	};

	// Private Method
	// HTTP GET- get all employees collection
	// Call: http://localhost:8080/employees
	function _refreshServiceSData() {
		$http.get('/service/get').then(function(res) { // success
			$scope.services = res.data;
			console.log(res);
		}, function(res) { // error
			console.log(res);
		});
	}

	function _success(res) {
		_refreshServiceSData();
		_clearFormData();
		$scope.serviceForm.update = 1;
	}

	function _error(res) {
		var data = res.data;
		var status = res.status;
		var header = res.header;
		var config = res.config;
		alert("Error: " + status + ":" + data);
	}

	// Clear the form
	function _clearFormData() {
		$scope.serviceForm.monthly = false;
		$scope.serviceForm.serName = ""
	}
	;
});