angular.module("boardello").factory('BoardApi', ['$resource', 
    function($resource) {
        return $resource('../api/boards/:boardId', {}, {
          
        });
    }
]);
        
 