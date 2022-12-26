package si.fri.rso.productcatalog.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("currency-exchange")
public class CurrencyExchangeProperties {

    @ConfigValue(watch = true)
    private String currencyExchangeHost;

    @ConfigValue(watch = true)
    private String currencyExchangeApiKey;

    @ConfigValue(watch = true)
    private String defaultCurrency;

    public String getCurrencyExchangeHost() {
        return currencyExchangeHost;
    }

    public void setCurrencyExchangeHost(String currencyExchangeHost) {
        this.currencyExchangeHost = currencyExchangeHost;
    }

    public String getCurrencyExchangeApiKey() {
        return currencyExchangeApiKey;
    }

    public void setCurrencyExchangeApiKey(String currencyExchangeApiKey) {
        this.currencyExchangeApiKey = currencyExchangeApiKey;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
}