package pl.model;

import com.google.gson.internal.LinkedTreeMap;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUR_ID")
    private int id;
    @Column(name = "CUR_DATE")
    private String orderDate;
    @Column(name = "CUR_BASE_CURRENCY")
    private String baseCurrency;
    @Column(name = "CUR_CURRENCY")
    private String currency;
    @Column(name = "CUR_VALUE")
    private Double value;
    @Column(name = "CUR_RATE")
    private LinkedTreeMap<String, Double> rates;

    public Currency(String orderDate, String baseCurrency, String currency, Double value, LinkedTreeMap<String,Double> rate) {
        this.orderDate = orderDate;
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.value = value;
        this.rates = rate;
    }

}
