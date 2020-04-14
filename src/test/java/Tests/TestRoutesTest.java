package Tests;

import application.Application;
import camel.beans.TestBean;
import camel.responsebodies.LoggedInResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.junit.Test;

@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
@RunWith(CamelSpringRunner.class)
public class TestRoutesTest {

    @EndpointInject(uri = "mock:result")
    private MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @Autowired
    TestBean testBean;

    @Autowired
    private CamelContext camelContext;

    @Test
    public void testTestBean() throws Exception{
        camelContext.addRoutes(createRoute());
        LoggedInResponse loggedInResponse = new LoggedInResponse("s");
        resultEndpoint.expectedBodiesReceived(loggedInResponse);
        template.sendBody(null);

        resultEndpoint.assertIsSatisfied();


    }

    public RouteBuilder createRoute(){
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("direct:start")
                        .bean(testBean)
                        .to("mock:result");
            }
        };
    }
}
