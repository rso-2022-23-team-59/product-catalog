package si.fri.rso.productcatalog.services.messaging;
import com.kumuluz.ee.amqp.common.annotations.AMQPChannel;
import com.kumuluz.ee.amqp.common.annotations.AMQPProducer;
import com.kumuluz.ee.amqp.rabbitmq.utils.producer.Message;


import javax.enterprise.context.ApplicationScoped;
import com.kumuluz.ee.amqp.common.annotations.AMQPProducer;

@ApplicationScoped
public class ProductMessageProducer {
    // TODO
    @AMQPProducer(host = "PSProduct", exchange = "productInfo", key = "newProduct")
    public String sendRed() {
        return "I'm Red!";
    }

}

