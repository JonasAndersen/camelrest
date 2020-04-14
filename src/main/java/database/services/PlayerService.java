package database.services;

import database.entities.Player;
import database.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    public PlayerRepository playerRepository;

    public Player findById(Long id) throws Exception {
        try {
            return playerRepository.findById(id);
        }
        catch (Exception e){
            throw new Exception("Player with id: " + id + " does not exists");
        }
    }
    public List<Player> findAll(){
        List<Player> players= new ArrayList<>();
        playerRepository.findAll().iterator().forEachRemaining(players::add);
        return players;
    }
    public Player addPlayer(String name){
        Player player = new Player(name);
        playerRepository.save(player);
        return player;
    }
}
