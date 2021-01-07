package pl.gson;

import com.google.gson.internal.LinkedTreeMap;

import java.math.BigDecimal;

public class CurrencyHistoryDto {
    private String base;
    private BigDecimal value;
    private String currency;
    private String date;
    private LinkedTreeMap<String, LinkedTreeMap<String, BigDecimal>> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LinkedTreeMap<String, LinkedTreeMap<String, BigDecimal>> getRates() {
        return rates;
    }

    public void setRates(LinkedTreeMap<String, LinkedTreeMap<String, BigDecimal>> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyHistoryDto{" +
                "base='" + base + '\'' +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
