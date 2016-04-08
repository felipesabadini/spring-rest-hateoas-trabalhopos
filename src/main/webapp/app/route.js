(function() {
	'use strict';
	
	angular
		.module('app')
		.config(route);
	
	route.$inject = ['$routeProvider'];
	function route($routeProvider) {
		$routeProvider
			.when('/', {
				templateUrl: './app/menu/menu.html',
				controller: 'menuController',
				controllerAs: 'vm'				
			})
			.when('/api/customers/list', {
				templateUrl: './app/customer/list.html',
				controller: 'customerController',
				controllerAs: 'vm'				
			})			
			.when('/api/customers/:param', {
				templateUrl: './app/customer/form.html',
				controller: 'customerController',
				controllerAs: 'vm'				
			})
			.when('/api/customers/:param/:id', {
				templateUrl: './app/customer/form.html',
				controller: 'customerController',
				controllerAs: 'vm'				
			})
			.when('/api/products/list', {
				templateUrl: './app/product/list.html',
				controller: 'productController',
				controllerAs: 'vm'				
			})			
			.when('/api/products/:param', {
				templateUrl: './app/product/form.html',
				controller: 'productController',
				controllerAs: 'vm'				
			})
			.when('/api/products/:param/:id', {
				templateUrl: './app/product/form.html',
				controller: 'productController',
				controllerAs: 'vm'				
			})
			.when('/api/orders/list', {
				templateUrl: './app/order/list.html',
				controller: 'orderController',
				controllerAs: 'vm'				
			})			
			.when('/api/orders/:param', {
				templateUrl: './app/order/form.html',
				controller: 'orderController',
				controllerAs: 'vm'				
			})
			.when('/api/orders/:param/:id', {
				templateUrl: './app/order/form.html',
				controller: 'orderController',
				controllerAs: 'vm'				
			})
		    .otherwise({
		        redirectTo: '/'
		      });
	};	
})();