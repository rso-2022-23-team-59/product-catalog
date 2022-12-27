package si.fri.rso.productcatalog.api.v1.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.fri.rso.productcatalog.lib.Product;
import si.fri.rso.productcatalog.lib.ProductStore;
import si.fri.rso.productcatalog.services.beans.ProductStoreBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/product-stores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductStoreResource {

    private Logger log = Logger.getLogger(ProductResource.class.getName());

    @Inject
    private ProductStoreBean productBean;

    @Context
    protected UriInfo uriInfo;

    @Operation(description = "Get all products.", summary = "Get all products")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "List of products",
                    content = @Content(schema = @Schema(implementation = Product.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of products in list")}
            )
    })
    @GET
    public Response getAllProducts() {
        List<ProductStore> products = productBean.getProductFilter(uriInfo);
        return Response.ok(products).header("X-Total-Count", products.size()).build();
    }

    @GET
    @Path("/{productId}/prices")
    public Response getLatestPrices(@PathParam("productId") Integer productId) {
        List<ProductStore> products = productBean.getLatestPrices(uriInfo, productId);
        return Response.ok(products).header("X-Total-Count", products.size()).build();
    }

}
