package si.fri.rso.productcatalog.services.messaging;

import com.kumuluz.ee.amqp.common.annotations.AMQPProducer;
import si.fri.rso.productcatalog.lib.ProductStore;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductMessageProducer {

    @AMQPProducer(host = "PSProduct", exchange = "productInfo", key = "newProduct")
    public ProductStore sendNewPrice(ProductStore product) {
        return product;
    }

}

