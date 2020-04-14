package camel.onlytest;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OnlyTestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:onlytest")
                .routeId("OnlyTest")
                .bean(OnlyTestBean.class)
                .to("mock:onlytestEnd");
    }
}
