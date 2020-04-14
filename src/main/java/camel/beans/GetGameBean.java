package camel.beans;

import camel.responsebodies.GameResponse;
import database.entities.Game;
import database.services.GameService;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetGameBean {

    @Autowired
    GameService gameService;

    @Handler
    public void getGame(Exchange exchange){
        String id = (String) exchange.getIn().getHeader("id");
        Game game = gameService.findGameById(Long.parseLong(id));
        GameResponse gameResponse = new GameResponse(game.getId().intValue(), game.getGameName());
        exchange.getIn().setBody(gameResponse);
    }
}
