(function() {
	'use strict';
	angular
		.module('app')
		.controller('orderController', orderController);
	
	orderController.$inject = ['$http', '$routeParams'];	
	function orderController($http, $routeParams) {
		var vm = this;
		vm.order = angular.copy(vm.order) || {id: '', customer: {}, product: {}};
		vm.menuCreate = vm.menuCreate || [];
		vm.title = "Order";
		vm.saved = false;
		vm.menuSaved = vm.menuSaved || {};
		vm.showButtonSave = true;
		vm.showButtonRemove = false;
		
		vm.load = function() {
			switch($routeParams.param) {
				case 'create':
					vm.title = "Create Order";
					vm.getMenuCreate();				
					vm.showButtonRemove = false;
					break;
				case 'details':
					vm.title = "Details Order";
					vm.showButtonSave = false;
					vm.showButtonRemove = false;
					vm.order = vm.getOrderByID($routeParams.id);
					break;				
				case 'edit':
					vm.title = "Edit Order";
					vm.showButtonSave = true;
					vm.showButtonRemove = false;
					vm.order = vm.getOrderByID($routeParams.id);
					break;
				case 'remove':
					vm.title = "Remove Order";
					vm.showButtonSave = false;
					vm.showButtonRemove = true;
					vm.order = vm.getOrderByID($routeParams.id);
					break;			
			}
			vm.getAllCustomer();
			vm.getAllProduct();
		};
		
		vm.list = function() {
			vm.getAllOrder();
			vm.getMenuCreate();
		}
		
		vm.save = function() {
			if(!(new RegExp('^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$').test(vm.order.id))) {			
				$http
					.post('/api/orders', vm.order)
					.then(function(response) {
						vm.saved = true;				
						vm.menu = response.data.data[0];
					});
			} else {
				$http
					.put('/api/orders/' + vm.order.id, vm.order)
					.then(function(response) {
						vm.saved = false;				
						vm.menu = response.data.data[0];
					});				
			}
		}
		
		vm.remove = function() {
			if(new RegExp('^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$').test(vm.order.id)) {
				$http
					.delete('/api/orders/' + vm.order.id)
					.then(function(response) {
						vm.order = {};				
						vm.menu = response.data.data[0];
					});							
			}
		}
		
		vm.getOrderByID = function(id) {
			$http
				.get('/api/orders/' + id)
				.then(function(response) {
					vm.order = response.data.entity;
					vm.menu = response.data.data[0];
				});			
		}
		
		vm.getAllOrder = function() {
			$http
				.get('/api/orders')
				.then(function(response) {				
					vm.orders = response.data;
				});
		}
		
		vm.getAllCustomer = function() {
			$http
				.get('/api/customers')
				.then(function(response) {		
					vm.customers = [];
					response.data.forEach(function(customer) {
						vm.customers.push(customer.entity);
					});
				});
		}
		
		vm.getAllProduct = function() {
			$http
				.get('/api/products')
				.then(function(response) {				
					vm.products = [];
					response.data.forEach(function(product) {
						vm.products.push(product.entity);
					});
				});
		}		
		
		vm.getMenuCreate = function() {
			$http
			.get('/api/menu-orders')
			.then(function(response) {
				vm.menu = response.data.data[0];
			});			
		}
	};	
})();