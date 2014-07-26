angular.module('boardello').controller('BoardListController', 
    ['$scope', '$http', '$log',  '$location', 'BoardApi',
    function ($scope, $http, $log, $location, BoardApi) {
       
        $scope.boards = BoardApi.query();
        
    }]
);


