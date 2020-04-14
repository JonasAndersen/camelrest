package camel.beans;

import camel.responsebodies.PlayerResponse;
import database.entities.Player;
import database.services.PlayerService;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPlayerBean {

    @Autowired
    PlayerService playerService;

    @Handler
    public void getPlayer(Exchange exchange) throws Exception{

        String id = (String) exchange.getIn().getHeader("id");
        Player player = playerService.findById(Long.parseLong(id));
        PlayerResponse playerResponse = new PlayerResponse(player.getId().intValue(),player.getName()/*,player.getPoints()*/);
        exchange.getIn().setBody(playerResponse);
    }
}
