package camel.beans;

import camel.requestbodies.AddPlayerRequestBody;
import camel.responsebodies.Action;
import camel.responsebodies.PlayerResponse;
import camel.responsebodies.Response;
import camel.responsebodies.Status;
import database.entities.Player;
import database.services.PlayerService;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddPlayerBean {

    @Autowired
    PlayerService playerService;

    @Handler
    public void addPlayer(Exchange exchange){
        AddPlayerRequestBody addPlayerRequestBody = (AddPlayerRequestBody)exchange.getIn().getBody();
        Player player = playerService.addPlayer(addPlayerRequestBody.getName());
        PlayerResponse playerResponse = new PlayerResponse(player.getId().intValue(),player.getName());

        Response response = new Response(Status.OK,Action.ADD_PLAYER,playerResponse);
        exchange.getIn().setBody(response);
    }
}
