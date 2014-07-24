angular.module('boardello').controller('CardShowController', 
    ['$scope', '$http', '$log',  
    function ($scope, $http, $log) {
        $scope.card = {
                id: 1002,
                closed: false,
                dateModified: "2014-07-21T18:27:58.528Z",
                desc: "",
                descData: null,
                attachmentCoverId: null,
                listId: 200,
                boardId: 1234,
                boardSlug: "welcome-board",
                name: "Drag people onto a card to indicate that they're responsible for it.",
                postiion: 2,
                badges: {},
                due: null,
                members: [],
                labels: [
                    {
                        color: "green",
                        name: ""
                    },
                    {
                        color: "red",
                        name: ""
                    }                    
                ],
                slug: "drag-people-onto-a-card-to-indicate-that-they-re-responsible-for-it",
                stickers: [],
                attachments: []
            };
        
    }]
);


