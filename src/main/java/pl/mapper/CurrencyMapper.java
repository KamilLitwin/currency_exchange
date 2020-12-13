package pl.mapper;

import com.google.gson.internal.LinkedTreeMap;
import pl.gson.CurrencyDto;
import pl.model.Currency;

import java.time.LocalDate;
import java.util.Date;

public class CurrencyMapper {

    public static Currency currencyDtoToCurrency(CurrencyDto CDto){
        Currency currency = new Currency();
        currency.setOrderDate(CDto.getDate());
        currency.setBaseCurrency(CDto.getBase());
        currency.setValue(CDto.getAmount());
        return currency;
    }
}
