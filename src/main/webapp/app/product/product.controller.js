(function() {
	'use strict';
	angular
		.module('app')
		.controller('productController', productController);
	
	productController.$inject = ['$http', '$routeParams'];	
	function productController($http, $routeParams) {
		var vm = this;
		vm.product = angular.copy(vm.product) || {};
		vm.menuCreate = vm.menuCreate || [];
		vm.title = "Product";
		vm.saved = false;
		vm.menuSaved = vm.menuSaved || {};
		vm.showButtonSave = true;
		vm.showButtonRemove = false;
		
		vm.load = function() {
			switch($routeParams.param) {
				case 'create':
					vm.title = "Create Product";
					vm.getMenuCreate();				
					vm.showButtonRemove = false;
					break;
				case 'details':
					vm.title = "Details Product";
					vm.showButtonSave = false;
					vm.showButtonRemove = false;
					vm.product = vm.getProductByID($routeParams.id);
					break;				
				case 'edit':
					vm.title = "Edit Product";
					vm.showButtonSave = true;
					vm.showButtonRemove = false;
					vm.product = vm.getProductByID($routeParams.id);
					break;
				case 'remove':
					vm.title = "Remove Product";
					vm.showButtonSave = false;
					vm.showButtonRemove = true;
					vm.product = vm.getProductByID($routeParams.id);
					break;			
			}
		};
		
		vm.list = function() {
			vm.getAllProduct();
			vm.getMenuCreate();
		}
		
		vm.save = function() {
			if(!(new RegExp('^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$').test(vm.product.id))) {			
				$http
					.post('/api/products', vm.product)
					.then(function(response) {
						vm.saved = true;				
						vm.menu = response.data.data[0];
					});
			} else {
				$http
					.put('/api/products/' + vm.product.id, vm.product)
					.then(function(response) {
						vm.saved = false;				
						vm.menu = response.data.data[0];
					});				
			}
		}
		
		vm.remove = function() {
			if(new RegExp('^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$').test(vm.product.id)) {
				$http
					.delete('/api/products/' + vm.product.id)
					.then(function(response) {
						vm.product = {};				
						vm.menu = response.data.data[0];
					});							
			}
		}
		
		vm.getProductByID = function(id) {
			$http
				.get('/api/products/' + id)
				.then(function(response) {
					vm.product = response.data.entity;
					vm.menu = response.data.data[0];
				});			
		}
		
		vm.getAllProduct = function() {
			$http
				.get('/api/products')
				.then(function(response) {				
					vm.products = response.data;
				});
		}
		
		vm.getMenuCreate = function() {
			$http
			.get('/api/menu-products')
			.then(function(response) {
				vm.menu = response.data.data[0];
			});			
		}
	};	
})();