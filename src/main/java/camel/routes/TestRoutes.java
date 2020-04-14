package camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TestRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:test")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody("Hej");
                    }
                })
                .to("file:output");
    }
}
