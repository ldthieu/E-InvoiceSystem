//import myApp from '@angular/app';
// Controller Part
app.controller("ServiceController", function($scope, $http) {

	$scope.services = [];
	$scope.serviceForm = {
		monthly : true,
		//        empNo: "",
		serName : ""
	};
	// 
	// Now load the data from server
	_refreshServiceSData();
	// 
	//    // HTTP POST/PUT methods for add/edit employee  
	//    // Call: http://localhost:8080/employee
	$scope.submitService = function() {

		//    	$scope.name = "";
		var url = "";
		if ($scope.serviceForm.update == -1) {
			url = '/service/update';
			method = 'PUT';
			$http({
				method : 'PUT',
				url : url,
				data : {
					'serviceName' : $scope.serviceForm.serName,
					'monthly' : $scope.serviceForm.monthly,
					'id' : $scope.serviceForm.id
				}
			})

			.then(_success, _error);
		} else {
			url = 'service/create';
			$http({
				method : 'POST',
				url : url,
				data : {
					'serviceName' : $scope.serviceForm.serName,
					'monthly' : $scope.serviceForm.monthly
				}
			})

			.then(_success, _error);
		}

		//    	    function(response) {
		//    	            // success
		//    	           console.log(_success);
		//    	    }, 
		//    	    function(response) { // optional
		//    	            // failed
		//    	    	console.log(_success) 
		//    	    }

	}
	// 
	$scope.createService = function() {
		_clearFormData();
	}

	// HTTP DELETE- delete employee by Id
	// Call: http://localhost:8080/employee/{empId}
	$scope.deleteService = function(employee) {
		$http({
			method : 'DELETE',
			url : '/employee/' + employee.empId
		}).then(_success, _error);
	};
	// 
	//    // In case of edit
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
		$http({
			method : 'GET',
			url : '/service/get'
		}).then(function(res) { // success
			$scope.services = res.data;
			console.log(res.data);
		}, function(res) { // error
			console.log("Error: " + res.status + " : " + res.data);
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
		$scope.serviceForm.monthly = true;
		$scope.serviceForm.serName = ""
	}
	;
});