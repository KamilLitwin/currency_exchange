package pl.gson;

import com.google.gson.internal.LinkedTreeMap;

public class CurrencyDto {
    private String base;
    private Double value;
    private String currency;
    private String date;
    private LinkedTreeMap<String, Double> rates;

    public LinkedTreeMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(LinkedTreeMap<String, Double> rates) {
        this.rates = rates;
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

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "base='" + base + '\'' +
                ", value='" + value + '\'' +
                ", currency='" + currency + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
