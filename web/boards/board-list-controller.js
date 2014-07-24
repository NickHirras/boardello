angular.module('boardello').controller('BoardListController', 
    ['$scope', '$http', '$log',  '$location',
    function ($scope, $http, $log, $location) {
       
        $scope.toggleStar = function() {
            alert('I love it!');            
        }
        
    }]
);


