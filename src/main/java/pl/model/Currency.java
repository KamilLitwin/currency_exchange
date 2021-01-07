package pl.model;

import com.google.gson.internal.LinkedTreeMap;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private BigDecimal value;
    //@Column(name = "CUR_VALUE2")
    private LinkedTreeMap<String, BigDecimal> value2;
    /*@Column(name = "CUR_RATE")
    private LinkedTreeMap<String, Double> rates;*/

    public Currency(String orderDate, String baseCurrency, String currency, BigDecimal value) {
        this.orderDate = orderDate;
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.value = value;
        //this.rates = rates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LinkedTreeMap<String, BigDecimal> getValue2() {
        return value2;
    }

   public void setValue2(LinkedTreeMap<String, BigDecimal> value2){
            this.value2 = value2;
        }
}
