package camel.responsebodies;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerResponse {
    int id;
    String name;
    //double points;
}
