(function() {
	'use strict';
	angular
		.module('app')
		.controller('customerController', customerController);
	
	customerController.$inject = ['$http', '$routeParams'];	
	function customerController($http, $routeParams) {
		var vm = this;
		vm.customer = angular.copy(vm.customer) || {};
		vm.menuCreate = vm.menuCreate || [];
		vm.title = "Customer";
		vm.saved = false;
		vm.menuSaved = vm.menuSaved || {};
		vm.showButtonSave = true;
		vm.showButtonRemove = false;
		
		vm.load = function() {
			switch($routeParams.param) {
				case 'create':
					vm.title = "Create Customer";
					vm.getMenuCreate();				
					vm.showButtonRemove = false;
					break;
				case 'details':
					vm.title = "Details Customer";
					vm.showButtonSave = false;
					vm.showButtonRemove = false;
					vm.customer = vm.getCustomerByID($routeParams.id);
					break;				
				case 'edit':
					vm.title = "Edit Customer";
					vm.showButtonSave = true;
					vm.showButtonRemove = false;
					vm.customer = vm.getCustomerByID($routeParams.id);
					break;
				case 'remove':
					vm.title = "Remove Customer";
					vm.showButtonSave = false;
					vm.showButtonRemove = true;
					vm.customer = vm.getCustomerByID($routeParams.id);
					break;			
			}
		};
		
		vm.list = function() {
			vm.getAllCustomer();
			vm.getMenuCreate();
		}
		
		vm.save = function() {
			if(!(new RegExp('^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$').test(vm.customer.id))) {			
				$http
					.post('/api/customers', vm.customer)
					.then(function(response) {
						vm.saved = true;				
						vm.menu = response.data.data[0];
					});
			} else {
				$http
					.put('/api/customers/' + vm.customer.id, vm.customer)
					.then(function(response) {
						vm.saved = false;				
						vm.menu = response.data.data[0];
					});				
			}
		}
		
		vm.remove = function() {
			if(new RegExp('^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$').test(vm.customer.id)) {
				$http
					.delete('/api/customers/' + vm.customer.id)
					.then(function(response) {
						vm.customer = {};				
						vm.menu = response.data.data[0];
					});							
			}
		}
		
		vm.getCustomerByID = function(id) {
			$http
				.get('/api/customers/' + id)
				.then(function(response) {
					vm.customer = response.data.entity;
					vm.menu = response.data.data[0];
				});			
		}
		
		vm.getAllCustomer = function() {
			$http
				.get('/api/customers')
				.then(function(response) {				
					console.log(response.data);
					vm.customers = response.data;
				});
		}
		
		vm.getMenuCreate = function() {
			$http
			.get('/api/menu-customers')
			.then(function(response) {
				vm.menu = response.data.data[0];
			});			
		}
	};	
})();