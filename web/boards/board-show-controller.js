angular.module('boardello').controller('BoardShowController', 
    ['$scope', '$http', '$log',  
    function ($scope, $http, $log) {
        $scope.lists = [
            {
                id: 200,
                name: "Basics",
                closed: false,
                boardId: 1234,
                position: 1                
            },
            {
                id: 202,
                name: "Advanced",
                closed: false,
                boardId: 1234,
                position: 3                
            },
            {
                id: 201,
                name: "Intermediate",
                closed: false,
                boardId: 1234,
                position: 2                
            }
        ];
        
        $scope.cards = [
            {
                id: 1001,
                closed: false,
                dateModified: "2014-07-24T18:27:58.528Z",
                desc: "",
                descData: null,
                attachmentCoverId: null,
                listId: 200,
                boardId: 1234,
                name: "Invite your team to this board using the Add Members button",
                postiion: 1,
                badges: {},
                due: null,
                members: [],
                labels: [],
                slug: "invite-your-team-to-this-board-using-the-add-members-button",
                stickers: [],
                attachments: []
            },
            {
                id: 1002,
                closed: false,
                dateModified: "2014-07-21T18:27:58.528Z",
                desc: "",
                descData: null,
                attachmentCoverId: null,
                listId: 200,
                boardId: 1234,
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
            },
            {
                id: 1003,
                closed: false,
                dateModified: "2014-07-21T18:27:58.528Z",
                desc: "",
                descData: null,
                attachmentCoverId: null,
                listId: 202,
                boardId: 1234,
                name: "Use as many boards as you want!",
                postiion: 1,
                badges: {},
                due: null,
                members: [],
                labels: [],
                slug: "use-as-many-boards-as-you-want",
                stickers: [],
                attachments: []
            }
        ];
        
    }]
);


