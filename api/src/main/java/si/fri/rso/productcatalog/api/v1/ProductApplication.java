package si.fri.rso.productcatalog.api.v1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
@CrossOrigin(allowOrigin = "*")
@OpenAPIDefinition(
    info = @Info(title = "Product catalog API", version = "v1",
    contact = @Contact(email = "nb2020@student.uni-lj.si"),
    license = @License(name = "dev"), description = "API for managing products."),
    servers = @Server(url = "http://localhost:8080/"))
@ApplicationPath("/v1")
public class ProductApplication extends Application {

}
