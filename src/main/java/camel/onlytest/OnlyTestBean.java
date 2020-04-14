package camel.onlytest;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnlyTestBean {

    @Autowired
    OnlyTestSingleton onlyTestSingleton;

    @Handler
    public void onlyTestHandler(Exchange exchange){
        System.out.println(onlyTestSingleton.hej());
    }
}
