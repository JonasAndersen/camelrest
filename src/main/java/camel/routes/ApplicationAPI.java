package camel.routes;


import camel.beans.*;
import camel.requestbodies.AddPlayerRequestBody;
import camel.responsebodies.GameResponse;
import camel.responsebodies.LoggedInResponse;
import camel.responsebodies.PlayerResponse;
import camel.responsebodies.Response;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationAPI extends RouteBuilder {

    JacksonDataFormat jacksonDataFormatGame = new JacksonDataFormat(GameResponse.class);
    JacksonDataFormat jacksonDataFormatPlayer = new JacksonDataFormat(PlayerResponse.class);
    JacksonDataFormat jacksonDataFormatResponse = new JacksonDataFormat(Response.class);

    @Override
    public void configure(){
        JaxbDataFormat jaxb = new JaxbDataFormat();
        restConfiguration()
                .component("servlet")
                .apiContextPath("/swagger")
                .apiContextRouteId("swagger")
                .contextPath("/api")
                ;
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.off)
                .enableCORS(true)
                .corsHeaderProperty("Access-Control-Allow-Origin","*")
                .corsAllowCredentials(true) // <-- Important
                .corsHeaderProperty("Access-Control-Allow-Methods","GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, CONNECT, PATCH")
                .corsHeaderProperty("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, Cache-Control")
                .port(8088)
                //.host("localhost")
                .apiProperty("api.title","Camel REST API")
                ;

        rest("/keycloak").description("API")

                .get("/hello").description("Hello")
                //.consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .id("Get hello")
                .outType(LoggedInResponse.class)
                .route()
                .bean(TestBean.class)
                .marshal().json(JsonLibrary.Jackson, LoggedInResponse.class)
                .endRest()

                .get("/games")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .id("Get Games")
                .outType(List.class)
                .route().setHeader("Access-Control-Allow-Origin",constant("*"))
                .bean(GetAllGamesBean.class)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("AHHHHH");
                    }
                })
                .marshal().json(JsonLibrary.Jackson, List.class)
                .endRest()

                .get("/game?id={id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .id("Get Game by id")
                .outType(GameResponse.class)
                .route()
                .bean(GetGameBean.class)
                .marshal().json(JsonLibrary.Jackson, GameResponse.class)
                .endRest()
        //;
                //;

        //rest("/")

    // Add Player related REST API calls
                .get("/players")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .id("Get Players")
                .outType(List.class)
                .route()
                .bean(GetAllPlayersBean.class)
                .marshal().json(JsonLibrary.Jackson,List.class)
                .setHeader("Origin",constant("http://localhost:4200"))
                .endRest()

                .get("/player?id={id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .id("Get Player by id")
                .outType(PlayerResponse.class)
                .route()
                .bean(GetPlayerBean.class)
                .marshal().json(JsonLibrary.Jackson,PlayerResponse.class)
                .endRest()

                .post("/player/add")
                //.consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .id("Add player")
                .type(AddPlayerRequestBody.class).outType(Response.class)
                .bindingMode(RestBindingMode.json)
                .route()
                .bean(AddPlayerBean.class)
                .marshal().json(JsonLibrary.Jackson,Response.class)
                .endRest()

                .post("/file")
                //.produces(MediaType.APPLICATION_JSON_VALUE)
                .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
                .id("File")
                .route()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String a = "";
                    }
                })
                .endRest();




        from("timer://simpleTimer?period=1000")
                .setBody(simple("Hello timer"))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        //System.out.println("" + exchange.getIn().getBody());
                    }
                })
                //.to()
                ;

    }

}
