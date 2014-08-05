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
                    <div class="dropdown pull-right">
                      <a class="dropdown-toggle unlink" type="button" id="dropdownMenu1" data-toggle="dropdown">
                        <span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Add Card&#8230;</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Copy List&#8230;</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Move List&#8230;</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Subscribe</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Move All Cards in This List&#8230;</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Archive All Cards in This List&#8230;</a></li>
                        <li role="presentation" class="divider"></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Archive This List</a></li>
                      </ul>
                    </div>                                    
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

                <!-- new card form -->
                <div class="panel panel-default clickable" ng-show="newCard.deckId === deck.id">                              
                    <div class="panel-body">
                        <form role="form">
                          <div class="form-group">
                              <textarea type="text" class="form-control" ng-model="newCard.description"></textarea>
                          </div>
                          <button type="submit" class="btn btn-success" ng-click="createCard()">Save</button>
                          <button type="reset" ng-click="hideNewCardForm()" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span></button>
                        </form>        
                    </div>
                </div>                
                <!-- /new card form -->
                
            </div>            
            
            <a ng-click="showNewCardForm(deck.id)" ng-hide="newCard.deckId === deck.id">
                <div class="panel-footer">
                    Add a card...
                </div>
            </a>
        </div>
    </div>
    <div class="col-sm-6 col-md-3">
        <button class="btn btn-default" style="width: 100%;" ng-hide="creatingList == true" ng-click="creatingList = true;">Add a list&hellip;</button>
        <div class="panel panel-default" ng-show="creatingList == true">
            <div class="panel-body">
                <form role="form">
                  <div class="form-group">
                      <input type="text" class="form-control" id="newListName" placeholder="Title&hellip;" required ng-model="listName">
                  </div>
                  <button type="submit" class="btn btn-success" ng-click="createDeck(listName); creatingList = false; listName = '';">Save</button>
                  <button type="reset" ng-click="creatingList = false" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span></button>
                </form>        
            </div>
        </div>
    </div>
</div>
