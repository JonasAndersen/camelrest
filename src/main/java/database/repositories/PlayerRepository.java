package database.repositories;

import database.entities.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findById(Long id);
    //List<Player> find
}
