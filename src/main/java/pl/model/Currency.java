package pl.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUR_ID")
    private int id;
    @Column(name = "CUR_DATE")
    private LocalDate orderDate;
    @Column(name = "CUR_BASE_CURRENCY")
    private String baseCurrency;
    @Column(name = "CUR_CURRENCY")
    private String currency;
    @Column(name = "CUR_VALUE")
    private String value;

    public Currency(LocalDate orderDate, String baseCurrency, String currency, String value) {
        this.orderDate = orderDate;
        this.baseCurrency = baseCurrency;
        this.currency = currency;
        this.value = value;
    }


}
