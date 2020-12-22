package pl.mapper;

import com.google.gson.internal.LinkedTreeMap;
import pl.gson.CurrencyDto;
import pl.gson.CurrencyHistoryDto;
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
        //currency.setRates(CDto.getRates());
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
            //currency.setRates(currencyDto.getRates());

            result.add(currency);
        }

        return result;
    }

    public static List<Currency> mapCurrencyDtoToEntity2(CurrencyHistoryDto currencyHistoryDto){

       List<Currency> result = new ArrayList<>();
        for (String key : currencyHistoryDto.getRates().keySet()) {
            Currency currency = new Currency();
            currency.setOrderDate(key);
            currency.setBaseCurrency(currencyHistoryDto.getBase());
            //currency.setValue();
            //currency.setValue2(currencyHistoryDto.getRates().get(key));
            //currency.setCurrency(key);
            //currency.setRates(currencyDto.getRates());

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

    public static CurrencyHistoryDto mapCurrencyToCurrencyDto2(Currency currency) {

        CurrencyHistoryDto result = new CurrencyHistoryDto();
        result.setBase(currency.getBaseCurrency());
        result.setDate(currency.getOrderDate());
        result.setCurrency(currency.getCurrency());
        result.setValue(currency.getValue());



        LinkedTreeMap<String, LinkedTreeMap<String, Double>> rates = new LinkedTreeMap();
        rates.put(currency.getOrderDate(),rates.put(currency.getBaseCurrency(),currency.getValue2()));
        //rates.put(currency.getCurrency(), currency.getValue2());

        result.setRates(rates);

        return result;
    }
}
