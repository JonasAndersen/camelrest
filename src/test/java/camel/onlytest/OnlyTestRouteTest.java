package camel.onlytest;


import application.Application;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.Before;



//@SpringBootTest
@SpringBootTest(classes = {Application.class, OnlyTestRoute.class})
@RunWith(SpringRunner.class)
public class OnlyTestRouteTest  extends CamelTestSupport {


    @EndpointInject(uri="direct:onlytest")
    ProducerTemplate producer;

    /*@Mock
    private OnlyTestSingleton onlyTestSingleton;*/

    @Test
    public void test() throws Exception{
        context.addRoutes(new OnlyTestRoute());
        context.start();
        producer.sendBody("");
        //template.sendBody("direct:camel.onlytest", "");
    }

}
