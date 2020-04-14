package database.services;

import database.entities.Game;
import database.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GameService {

    @Autowired
    public GameRepository gameRepository;


    public Game findGameById(Long id){
        Game game = gameRepository.findGameById(id);
        return game;
    }
    public List<Game> findGamesByIdIn(List<Long> ids ){
        List<Game> games = gameRepository.findByIdIn(ids);
        return games;
    }
    public  List<Game>findAllGames(){
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().iterator().forEachRemaining(games::add);
        return games;
    }
}
