<div class="row">
    <div class="col-xs-12">
        <h3 style="color: black;">
            {{board.board.name}} 
            <span class="glyphicon glyphicon-star-empty" style="margin-left: 2em; font-size: 10pt;"></span>
            <span style="margin-left: 1.5em; font-size: 10pt;"><span class="glyphicon glyphicon-lock"></span> Private</span>            
        </h3>
        
    </div>        
</div>
    
<div class="row">

    <!-- Deck of Cards -->
    <div class="col-sm-6 col-md-3" ng-repeat="deck in board.decks | orderBy:'position'">
        <div class="panel panel-default">
            <div class="panel-heading">
              <div class="panel-title">                    
                  {{deck.name}}
                  <span class="pull-right glyphicon glyphicon-chevron-down"></span>
              </div>            
            </div>
            <div class="deck panel-body">                

                <!-- card -->                

                <div class="panel panel-default clickable" ng-repeat="card in board.cards | filter:{deckId: deck.id} | orderBy: 'position'">                              
                    <!-- colored labels -->
                    <span ng-repeat="labelId in card.labelIds">
                        <span ng-repeat="label in board.labels | filter:{id:labelId}" style="color: {{label.color}}; float: left; margin-top: -2px;" class="glyphicon glyphicon-bookmark"></span>
                    </span>
                    <!-- card content -->
                    <a href="c/{{card.id}}/{{card.slug}}" class="unlink">
                        <div class="panel-body">
                            {{card.name}}
                        </div>
                    </a>
                </div>
                <!-- /card -->
                
            </div>            
            <a>
                <div class="panel-footer">
                    Add a card...
                </div>
            </a>
        </div>
    </div>
    <div class="col-sm-6 col-md-3">
        <div class="btn-group btn-group-justified">
            <a class="btn btn-default">Add a list...</a>
        </div>
    </div>
</div>
