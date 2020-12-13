package pl.gson;

import com.google.gson.internal.LinkedTreeMap;

import java.time.LocalDate;
import java.util.Date;

public class CurrencyDto {
    private String base;
    private String amount;
    private LinkedTreeMap<String, Double> rates;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LinkedTreeMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(LinkedTreeMap<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "base='" + base + '\'' +
                ", amount=" + amount +
                ", rates=" + rates +
                ", date=" + date +
                '}';
    }
}
