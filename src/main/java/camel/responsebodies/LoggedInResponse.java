package camel.responsebodies;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInResponse {

    String isKeycloakLoggedIn;
    //Status status;
}
