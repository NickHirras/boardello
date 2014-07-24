angular.module('boardello', [
    'ui.bootstrap', 
    'ui.utils',
    'ngRoute',
    'ngResource',
    'ngSanitize'
]);

angular.module('boardello').config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true).hashPrefix('!');
        $routeProvider.
            when('/', {
                templateUrl: '../boards/board-list.jsp',
                controller: 'BoardListController'
            }).
            when('/b/:boardId', {
                templateUrl: '../boards/board-show.jsp',
                controller: 'BoardShowController'
            }).
            when('/b/:boardId/:boardSlug', {
                templateUrl: '../boards/board-show.jsp',
                controller: 'BoardShowController'
            }).
            when('/c/:cardId', {
                templateUrl: '../cards/card-show.jsp',
                controller: 'CardShowController'
            }).
            when('/c/:cardId/:cardSlug', {
                templateUrl: '../cards/card-show.jsp',
                controller: 'CardShowController'
            }).
            otherwise({
               redirectTo: '/'
            });
    }
]);