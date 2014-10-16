<h3 style="color: black;"><span class="glyphicon glyphicon-user"></span> My Boards</h3>

<div class="row">
  <div class="col-sm-6 col-md-3" ng-repeat="board in boards">
    <a href="b/{{board.id}}/{{board.slug}}" class="unlink">
      <div class="panel panel-primary clickable">
        <div class="panel-heading">
          <h3 class="panel-title">
            {{board.name}}
            <span class="pull-right glyphicon glyphicon-star-empty" ng-click="toggleStar();
                $event.stopPropagation()"></span>
          </h3>            
        </div>
        <div class="panel-body">

        </div>
      </div>
    </a>
  </div>
</div>