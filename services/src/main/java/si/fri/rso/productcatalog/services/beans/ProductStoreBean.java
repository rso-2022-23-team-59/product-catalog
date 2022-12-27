package si.fri.rso.productcatalog.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.productcatalog.lib.ProductStore;
import si.fri.rso.productcatalog.lib.ProductStoreSimple;
import si.fri.rso.productcatalog.models.converters.ProductStoreConverter;
import si.fri.rso.productcatalog.models.entities.ProductEntity;
import si.fri.rso.productcatalog.models.entities.ProductStoreEntity;
import si.fri.rso.productcatalog.services.config.CurrencyExchangeProperties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;


@RequestScoped
public class ProductStoreBean {

    private Logger log = Logger.getLogger(si.fri.rso.productcatalog.services.beans.ProductStoreBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private CurrencyExchangeProperties currencyExchangeProperties;

    public List<ProductStore> getLatestPrices(UriInfo uriInfo, Integer productId) {
        // If the URI contains [currency] parameter, convert prices from EUR to this currency.
        // Otherwise, return found products with prices in EUR.
        String defaultCurrency = currencyExchangeProperties.getDefaultCurrency();
        String currency = uriInfo.getQueryParameters().getFirst("currency");

        // Perform a named native query to get the latest product price from each store.
        TypedQuery<ProductStoreEntity> query = em.createNamedQuery("ProductStoreEntity.getLatest", ProductStoreEntity.class);
        query.setParameter(1, productId);

        // Convert ProductStoreEntity objects to DTO.
        List<ProductStore> products = query.getResultStream()
                .map(entity-> ProductStoreConverter.toDto(entity, defaultCurrency)).toList();

        if (currency != null) {
            convertPrices(products, defaultCurrency, currency);
        }

        return products;
    }

    private double roundCurrency(double value) {
        return Math.round(value * 1000.0) / 1000.0;
    }

    private double getExchangeRate(String from, String to) throws NullPointerException, NumberFormatException {
        URI uri = UriBuilder.fromUri("https://currency-exchange.p.rapidapi.com/exchange")
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("q", 1)
                .build();

        // Read host and api key from configuration.
        String host = currencyExchangeProperties.getCurrencyExchangeHost();
        String apiKey = currencyExchangeProperties.getCurrencyExchangeApiKey();

        Response response = ClientBuilder.newClient().target(uri).request()
                .header("X-RapidAPI-Host", host)
                .header("X-RapidAPI-Key", apiKey)
                .get();

        if (!response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            log.warning("Could not get conversion rate from '" + from + "' to '" + to + "'.");
            throw new RuntimeException("Currency exchange API could not convert from '" + from + "' to '" + to + "'.");
        }

        // Parse the result. The API returns a single double precision floating value.
        String responseBody = response.readEntity(String.class);
        return Double.parseDouble(responseBody);
    }

    void convertPrices(List<ProductStore> products, String fromCurrency, String toCurrency) {
        // Get conversion rate between default currency and selected currency.
        double conversionRate;
        try {
            conversionRate = getExchangeRate(fromCurrency, toCurrency);
        } catch (NullPointerException | NumberFormatException e) {
            log.warning("Invalid response from currency exchange server.");
            throw new RuntimeException("Invalid response from currency exchange server.");
        }

        // Convert prices of all objects to selected currency.
        for (ProductStore product : products) {
            Double originalPrice = product.getPrice();
            if (originalPrice != null) {
                double convertedPrice = roundCurrency(originalPrice * conversionRate);
                product.setPrice(convertedPrice);
                product.setCurrency(toCurrency);
            }
        }
    }

    public List<ProductStore> getProductFilter(UriInfo uriInfo) {
        // If the URI contains [currency] parameter, convert prices from EUR to this currency.
        // Otherwise, return found products with prices in EUR.
        String defaultCurrency = currencyExchangeProperties.getDefaultCurrency();
        String currency = uriInfo.getQueryParameters().getFirst("currency");

        // First, find products in database using URI parameters for filtering.
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();
        List<ProductStore> products = JPAUtils.queryEntities(em, ProductStoreEntity.class, queryParameters).stream()
                .map(entity-> ProductStoreConverter.toDto(entity, defaultCurrency)).toList();

        // If the [currency] query argument is provided in the URL, get conversion rate from
        // external API and convert all product prices.
        if (currency != null) {
            // The convertPrices will modify PDO objects in place.
            convertPrices(products, defaultCurrency, currency);
        }

        return products;
    }

    public ProductStore insertProductStore(ProductStoreSimple productStoreSimple) {

        ProductEntity product = em.find(ProductEntity.class, productStoreSimple.getProductId());

        ProductStoreEntity entity = new ProductStoreEntity();
        entity.setStoreId(productStoreSimple.getStoreId());
        entity.setPrice(productStoreSimple.getPrice());
        entity.setTimestamp(Instant.now());
        entity.setProduct(product);

        try {
            beginTx();
            em.persist(entity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        String defaultCurrency = currencyExchangeProperties.getDefaultCurrency();
        return ProductStoreConverter.toDto(entity, defaultCurrency);
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
