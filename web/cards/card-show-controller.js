angular.module('boardello').controller('CardShowController',
['$scope', '$http', '$log', 'CardApi', '$routeParams',
  function ($scope, $http, $log, CardApi, $routeParams) {
    $scope.card = CardApi.get({cardId: $routeParams.cardId});
  }]
);


