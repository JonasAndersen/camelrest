package camel.beans;

import camel.responsebodies.GameResponse;
import camel.responsebodies.LoggedInResponse;
import camel.responsebodies.Status;
import database.entities.Game;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import database.services.GameService;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestBean {

    @Autowired
    private GameService gameService;

    @Handler
    public void test(Exchange exchange){

        LoggedInResponse loggedInResponse = new LoggedInResponse("Ok"/*, Status.OK*/);
        exchange.getIn().setBody(loggedInResponse);

    }
}
