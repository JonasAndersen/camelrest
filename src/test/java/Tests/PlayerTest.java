package Tests;


import application.Application;
import camel.responsebodies.LoggedInResponse;
import camel.routes.ApplicationAPI;
import camel.routes.TestRoutes;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;

import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = {Application.class, TestRoutes.class})
@MockEndpoints("file:output")

public class PlayerTest  {

    @EndpointInject(uri= "mock:file:output")
    MockEndpoint mockEndpoint;

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    public void test() throws Exception{

        mockEndpoint.expectedBodiesReceived("Hej");
        producerTemplate.sendBody("direct:test",null);

        mockEndpoint.assertIsSatisfied();


    }


}
