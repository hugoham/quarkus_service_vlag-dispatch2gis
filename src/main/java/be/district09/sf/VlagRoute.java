package be.district09.sf;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.camel.builder.RouteBuilder;

import javax.sql.DataSource;

@ApplicationScoped
public class VlagRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        from("jms:queue:TestQueue::TestQueue")
            .setBody(simple("INSERT INTO vlag_dispatch (message) VALUES ('${body}')"))
            .to("jdbc:target_db");

    }
}

