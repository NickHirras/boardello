angular.module('boardello').controller('BoardShowController', 
    ['$scope', '$http', '$log', '$routeParams', 'BoardApi',
    function ($scope, $http, $log, $routeParams, BoardApi) {
        
        $scope.board = BoardApi.get({boardId: $routeParams.boardId});
        
    }]
);


