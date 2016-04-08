(function() {
	'use strict';
	
	angular
		.module('app')
		.controller('menuController', menuController);
	
	menuController.$inject = ['$http'];	
	function menuController($http) {
		var vm = this;
		vm.menu = angular.copy(vm.menu) || [];
		
		vm.getMenu = function() {
			$http
				.get('/api/menus')
				.then(function(response) {
					vm.menu = response.data;
				});
		};
	};			
})();