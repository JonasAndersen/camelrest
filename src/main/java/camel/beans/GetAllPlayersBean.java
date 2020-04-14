package camel.beans;

import camel.responsebodies.PlayerResponse;
import database.entities.Player;
import database.services.PlayerService;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllPlayersBean {
    @Autowired
    PlayerService playerService;

    @Handler
    public void getAllPlayersBean(Exchange exchange){
        List<Player> playerList = playerService.findAll();
        List<PlayerResponse> playerResponseList = new ArrayList<>();
        for(Player player : playerList){
            PlayerResponse playerResponse = new PlayerResponse(player.getId().intValue(),player.getName()/*,player.getPoints()*/);
            playerResponseList.add(playerResponse);
        }
        exchange.getIn().setBody(playerResponseList);
    }
}
