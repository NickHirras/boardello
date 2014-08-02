angular.module('boardello').controller('BoardShowController', 

    ['$scope', '$http', '$log', 'BoardApi',  '$routeParams',
    function ($scope, $http, $log, BoardApi, $routeParams) {
        $scope.board = BoardApi.get({boardId: $routeParams.boardId});

    }]
);


