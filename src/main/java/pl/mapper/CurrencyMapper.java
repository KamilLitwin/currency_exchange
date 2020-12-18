package pl.mapper;

import com.google.gson.internal.LinkedTreeMap;
import pl.gson.CurrencyDto;
import pl.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyMapper {

    public static Currency currencyDtoToCurrency(CurrencyDto CDto){
        Currency currency = new Currency();

        currency.setOrderDate(CDto.getDate());
        currency.setBaseCurrency(CDto.getBase());
        currency.setValue(CDto.getValue());
        currency.setCurrency(CDto.getCurrency());
        currency.setRates(CDto.getRates());
        return currency;
    }


    public static List<Currency> mapCurrencyDtoToEntity(CurrencyDto currencyDto){

        List<Currency> result = new ArrayList<>();
        for (String key : currencyDto.getRates().keySet()) {
            Currency currency = new Currency();
            currency.setOrderDate(currencyDto.getDate());
            currency.setBaseCurrency(currencyDto.getBase());
            currency.setValue(currencyDto.getRates().get(key));
            currency.setCurrency(key);
            currency.setRates(currencyDto.getRates());

            result.add(currency);
        }

        return result;
    }

    public static CurrencyDto mapCurrencyToCurrencyDto(Currency currency) {

        CurrencyDto result = new CurrencyDto();
        result.setBase(currency.getBaseCurrency());
        result.setDate(currency.getOrderDate());
        result.setCurrency(currency.getCurrency());

        LinkedTreeMap<String, Double> rates = new LinkedTreeMap();
        rates.put(currency.getCurrency(), currency.getValue());

        result.setRates(rates);

        return result;
    }
}
