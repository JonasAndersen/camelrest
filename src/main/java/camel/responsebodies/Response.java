package camel.responsebodies;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    Status status;
    Action action;
    PlayerResponse playerResponse;

}
