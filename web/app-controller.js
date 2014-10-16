angular.module('boardello').controller('AppController', ['$scope', '$http', '$log', 'BoardApi', '$location',
  function ($scope, $http, $log, BoardApi, $location) {
    $scope.createBoard = function (boardName) {
      newBoard = {name: boardName};
      BoardApi.save(newBoard,
        function (board) {
          $location.path('/b/' + board.id + '/' + board.slug);
        },
        function (error) {
          alert("Unable to save board.");
        }
      );
    }
  }]);


