<p>&nbsp;</p>
<div class="row">
  <div class="col-xs-10 col-xs-offset-1">
    <div class="panel panel-default card-stage">
      <!-- card -->
      <div class="panel-heading">
        <span class="h4"><span class="glyphicon glyphicon-tasks" style="margin-right: 1em;"></span>{{card.card.name}}</span>
        <a href="b/{{card.board.id}}/{{card.board.slug}}" class="unlink"><span class="glyphicon glyphicon-remove pull-right"></span></a>
      </div>
      <div class="panel-body">

        <div class="row">

          <div class="col-xs-9 card">
            <div class="row">
              <h1><span class="glyphicon glyphicon-bookmark"></span>Labels</h1>
              <span ng-repeat="labelId in card.card.labelIds">
                <button ng-repeat="label in card.labels| filter:{id:labelId}" class="btn btn-sm btn-default"><span style="color: {{label.color}}" class="glyphicon glyphicon-bookmark"></span></button>
              </span>
              <button class="btn btn-sm btn-default"><span class="glyphicon glyphicon-plus"></span></button>
            </div>

            <div class="row">
              <h1><span class="glyphicon glyphicon-calendar"></span>Due Date</h1>
              <div class="label label-default">{{card.card.dueDate|date}}</div>                               
            </div>

            <div class="row">
              <h1><span class="glyphicon glyphicon-list-alt"></span>Description <a style="margin-left: 1em;">Edit</a></h1>
              <p>{{card.card.description}}</p>
            </div>

            <div class="row">
              <h1><span class="glyphicon glyphicon-paperclip"></span>Attachments</h1>
            </div>

            <div class="row" ng-repeat="checklist in card.checklists">
              <h1><span class="glyphicon glyphicon-list"></span>{{checklist.name}}</h1>      
              <div class="row" ng-repeat="item in card.checklistItems|filter:{checklistId: checklist.id}|orderBy:'position'">
                <input type="checkbox" id="{{item.id}}" ng-model="item.completed">
                <label for="{{item.id}}" style="{{item.completed ? 'text-decoration:line-through;' : ''}}">{{item.content}}</label>                            
              </div>
            </div>

          </div>

          <div class="col-xs-3">
            <fieldset>
              <legend>Add</legend>
              <p class="btn-group btn-group-justified">                            
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-user" style="margin-right: 0.5em;"></span> 
                  Members
                </a>
              </p>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-bookmark" style="margin-right: 0.5em;"></span> 
                  Labels
                </a>
              </p>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-list" style="margin-right: 0.5em;"></span> 
                  Checklist
                </a>
              </p>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-time" style="margin-right: 0.5em;"></span> 
                  Due date
                </a>
              </p>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-paperclip" style="margin-right: 0.5em;"></span> 
                  Attachment
                </a>
              </p>
            </fieldset>
            <fieldset>
              <legend>Actions</legend>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-move" style="margin-right: 0.5em;"></span> 
                  Move
                </a>
              </p>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-floppy-disk" style="margin-right: 0.5em;"></span> 
                  Copy
                </a>
              </p>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-asterisk" style="margin-right: 0.5em;"></span> 
                  Subscribe
                </a>
              </p>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-save" style="margin-right: 0.5em;"></span> 
                  Archive
                </a>
              </p>
              <p class="btn-group btn-group-justified">
                <a class="btn btn-default">
                  <span class="glyphicon glyphicon-cloud-download" style="margin-right: 0.5em;"></span> 
                  Export
                </a>
              </p>
            </fieldset>


          </div>                        
        </div>

      </div>


    </div>
  </div>        
</div>        
</div>

