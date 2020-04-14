package database.repositories;

import database.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface GameRepository extends JpaRepository<Game,Long> {
public interface GameRepository extends CrudRepository<Game,Long> {

    Game findGameById(Long id);
    List<Game> findByIdIn(List<Long> ids);
}
