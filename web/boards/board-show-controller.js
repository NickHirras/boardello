angular.module('boardello').controller('BoardShowController', 
    ['$scope', '$http', '$log', '$routeParams', 'BoardApi', 'DeckApi', 'CardApi',
    function ($scope, $http, $log, $routeParams, BoardApi, DeckApi, CardApi) {
        
        $scope.board = BoardApi.get({boardId: $routeParams.boardId});
        
        $scope.newCard = {}; 
        
        $scope.showNewCardForm = function(deckId) {
            $scope.newCard.deckId = deckId;
        }
        
        $scope.hideNewCardForm = function() {
            $scope.newCard = {};
        }
        
        $scope.createCard = function() {
            CardApi.save($scope.newCard, 
                function(card) {
                    $scope.newCard = {};
                    $scope.board.cards.push(card);
                },
                function(error) {
                    alert("Unable to create card, please try again later.");
                }
            );
        }
        
        $scope.createDeck = function(deckName) {
            deck = {};
            deck.boardId = $scope.board.board.id;
            deck.name = deckName;            
            DeckApi.save(deck, 
                function(deck) {
                    $scope.board.decks.push(deck);
                }, 
                function(error) {
                    alert("Unable to create list, please try again later.");
                }
            );
        }
        
    }]
);


