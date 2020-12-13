package pl.api;

import com.google.gson.Gson;
import pl.exception.CustomException;
import pl.gson.CurrencyDto;
import pl.mapper.CurrencyMapper;
import pl.model.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentService {

    public String returnCurrencies() throws CustomException {
        String uri = "https://api.exchangeratesapi.io/latest";
        return get(uri);
    }

    public String allCurrencies(String currency) throws CustomException {
        String uri = "https://api.exchangeratesapi.io/latest?base=" + currency;
        return get(uri);
    }

    public String currencyExchangeCountries(String baseCurrency, String exchangeCurrency) throws CustomException{

        String uri = "https://api.exchangeratesapi.io/latest?base=" + baseCurrency + "&symbols=" + exchangeCurrency;
        return get(uri);
    }

    public String ratesHistorical(String currency, String dateFrom, String dateTo) throws CustomException{
        String uri = "https://api.exchangeratesapi.io/history?base=" + currency + "&start_at=" + dateFrom + "&end_at=" + dateTo;
        return get(uri);
    }

    public CurrencyDto parseDto() throws CustomException {
        String uri = "https://api.exchangeratesapi.io/latest";
        String json = get(uri);

        Gson gson = new Gson();
        CurrencyDto currency = gson.fromJson(json, CurrencyDto.class);
        Currency entity = CurrencyMapper.currencyDtoToCurrency(currency);
        return currency;
    }

    public CurrencyDto parseDto2(String baseCurrency, String exchangeCurrency) throws CustomException {
        String uri = "https://api.exchangeratesapi.io/latest?base=" + baseCurrency + "&symbols=" + exchangeCurrency;
        String json = get(uri);

        Gson gson = new Gson();
        CurrencyDto currency = gson.fromJson(json, CurrencyDto.class);
        Currency entity = CurrencyMapper.currencyDtoToCurrency(currency);
        return currency;
    }

    private String get(String uri) throws CustomException {
        try {
            URL url = new URL(uri);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
            in.close();

            if (stringBuilder.toString() == "") {
                throw new CustomException("Jakiś inny komunikat obłędzie");
            }

            return stringBuilder.toString();

        } catch (MalformedURLException e) {
            System.out.println("jakiś błąd");
            throw new CustomException("jakiś błąd");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
