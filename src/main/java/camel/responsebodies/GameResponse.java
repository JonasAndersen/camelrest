package camel.responsebodies;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameResponse {
    int id;
    String gameName;
}
