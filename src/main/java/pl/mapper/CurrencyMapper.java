package pl.mapper;

import pl.gson.CurrencyDto;
import pl.model.Currency;

import java.text.ParseException;
import java.time.LocalDate;

public class CurrencyMapper {

    public static Currency currencyDtoToCurrency(CurrencyDto CDto){
        Currency currency = new Currency();
        currency.setOrderDate(LocalDate.parse(CDto.getDate()));
        currency.setBaseCurrency(CDto.getBase());
        currency.setValue(CDto.getValue());
        currency.setCurrency(CDto.getCurrency());
        currency.setRates(CDto.getRates());
        return currency;
    }
}
