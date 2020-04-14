package camel.beans;

import camel.responsebodies.GameResponse;
import database.entities.Game;
import database.services.GameService;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllGamesBean {

    @Autowired
    GameService gameService;

    @Handler
    public void getAllPlayers(Exchange exchange){

        List<Game> gameList = gameService.findAllGames();
        List<GameResponse> gameResponseList = new ArrayList<>();
        for(Game game : gameList){
            GameResponse gameResponse = new GameResponse(game.getId().intValue(),game.getGameName());
            gameResponseList.add(gameResponse);
        }
        exchange.getIn().setBody(gameResponseList);
    }
}
