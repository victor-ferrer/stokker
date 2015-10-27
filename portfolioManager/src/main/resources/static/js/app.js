angular.module('portfolio_manager', [ 'ngRoute' ])
.config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'navigation'
	}).when('/stocks', {
		templateUrl : 'stocks.html',
		controller : 'stocksController'
	}).when('/portfolio_list', {
		templateUrl : 'portfolio_list.html',
		controller : 'portfolioController'
	}).when('/portfolio_detail', {
		templateUrl : 'portfolio_detail.html',
		controller : 'portfolioDetailController'
	}).when('/stock_detail', {
		templateUrl : 'stock_detail.html',
		controller : 'stocksController'
	})
	.otherwise('/');

  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

})
.controller('navigation',

  function($rootScope, $scope, $http, $location, $window) {

	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};
	if (!$scope.user) {
		$http.get('/portfoliomanager/user').success(function(data) {
			$scope.user = data;
			$rootScope.authenticated = true;
		}).error(function() {
			$rootScope.authenticated = false;
		});
	}
	
	$scope.login = function() {
		$window.location.href= "/portfoliomanager/login";
		
	};
	
	$scope.logout = function() {
		$http.post('/portfoliomanager/logout', {}).success(function() {
			delete $scope.user;
			$rootScope.authenticated = false;
			// Force reload of home page to reset all state after logout
			$window.location = '/';
		});
	};

})
.controller('home', function() {})
.controller('portfolioController', function($scope, $http) {
	  
	  $http.get('/portfolios').success(function(data) {
		    $scope.portfolios = data._embedded.portfolios;
	  })
})
.controller('portfolioDetailController', function($scope, $http) {
	  
	  $http.get('positions').success(function(data) {
		    $scope.positions = data._embedded.positions;
		    
		    angular.forEach($scope.positions, function(position) {
		    	  $http.get(position._links.stock.href).success(function(stockdata){
		    		  position.stock = stockdata;
		    		  position.currentPrice = 0;
		    	  })
		    });
		    
		  })
})
.controller('stocksController', function($scope, $http, $sce, $document, $routeParams){
	  $http.get('stocks').success(function(data) {
		    $scope.stocks = data._embedded.stocks;
		  });

	  
	  $scope.init = function () {
		  	$scope.query = '*';
		  	$scope.tab = [{ "refresh" : false}];
		  	
		  	$http.get($routeParams.stockInfo).success(function(data){
		  		$scope.targetStock = data;  
		  		$scope.query = $scope.targetStock.ticker + "." + $scope.targetStock.market;
		  		
		  		// WARNING This should be customized with the URL created by Kibana when exporting the dashboard
		  		$scope.dahsboardURL = $sce.trustAsResourceUrl("http://localhost:5601/#/dashboard/Full_dashboard?embed&_g=(refreshInterval:(display:Off,pause:!f,section:0,value:0),time:(from:now-1y,mode:quick,to:now))&_a=(filters:!(),panels:!((col:1,id:Stock-Values,row:1,size_x:12,size_y:4,type:visualization),(col:1,id:Stock-volume,row:7,size_x:12,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'stock%3D!'"+ $scope.query +  "!'')),title:Full_dashboard)");		  
		  	});
		  	
		  	$scope.timeframes = [{ "label" : "Last 2 years", "code" : "now-2y"},
			                       { "label" : "Last year", "code" : "now-1y"},
			                       { "label" : "Last 6 months", "code" : "now-6M"},
			                       { "label" : "Last month", "code" : "now-1M"},
			                       { "label" : "Last 2 weeks", "code" : "now-2w"},
			                       { "label" : "Last week", "code" : "now-1w"} ];
			  
			  $scope.targetTimeFrame = [{ "label" : "Last year", "code" : "now-1y"}];
		}
	  $scope.init();
	  
	  
	  $scope.updateIframe = function() {
		  
		  if ($scope.targetStock.needMarketSuffix){
			  $scope.query = $scope.targetStock.ticker + "." + $scope.targetStock.market;
		  }
		  else {
			  $scope.query = $scope.targetStock.ticker;
		  }
		  
		  $scope.dahsboardURL = $sce.trustAsResourceUrl("http://localhost:5601/#/dashboard/Full_dashboard?embed&_g=(refreshInterval:(display:Off,pause:!f,section:0,value:0),time:(from:" + $scope.targetTimeFrame.code +  ",mode:quick,to:now))&_a=(filters:!(),panels:!((col:1,id:Stock-Values,row:1,size_x:12,size_y:4,type:visualization),(col:1,id:Stock-volume,row:7,size_x:12,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'stock%3D!'"+ $scope.query +  "!'')),title:Full_dashboard)");
		  $scope.tab.refresh=true;
		}
	  
	  
		  
})
.directive('refreshable', [function () {
    return {
        restrict: 'A',
        scope: {
            refresh: "=refreshable"
        },
        link: function (scope, element, attr) {
            var refreshMe = function () {
                element.attr('src', element.attr('src'));
            };

            scope.$watch('refresh', function (newVal, oldVal) {
                if (scope.refresh) {
                    scope.refresh = false;
                    refreshMe();
                }
            });
        }
    };
}]);