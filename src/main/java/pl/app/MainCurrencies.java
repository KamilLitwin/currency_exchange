package pl.app;

import pl.api.CurrentService;
import pl.exception.CustomException;
import pl.gson.CurrencyDto;

import java.util.Scanner;

public class MainCurrencies {
    public static void main(String[] args) {
        CurrentService currentService = new CurrentService();
        try {

            System.out.println("Wybierz opcję");
            System.out.println("1) Zwróć kursy walut domyślnie euro");
            System.out.println("2) Wyceń kurs w innej walucie");
            System.out.println("3) Wyceń dokładny kurs jednej waluty");
            System.out.println("4) Uzyskaj historyczne kursy walut dla danego okresu");

            System.out.println("12) Prasowanie JSON - przykład");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            String result;

            switch (option) {
                case 1:
                    result = currentService.returnCurrencies();
                    break;
                case 2:
                    System.out.println("Napisz walutę");
                    String currency = scanner.next();
                    result = currentService.allCurrencies(currency);
                    break;
                case 3:
                    System.out.println("Napisz walutę bazową");
                    String baseCurrency = scanner.next();
                    System.out.println("Napisz walutę wymiany");
                    String exchangeCurrency = scanner.next();
                    result = currentService.currencyExchangeCountries(baseCurrency,exchangeCurrency);
                    break;
                case 4:
                    System.out.println("Napisz walutę bazową");
                    String currency1 = scanner.next();
                    System.out.println("Napisz od kiedy pobieramy datę. Przykład poprawnej daty 2020-10-02");
                    String dateFrom = scanner.next();
                    System.out.println("Napisz do kiedy pobieramy datę. Przykład poprawnej daty 2020-11-03");
                    String dateTo = scanner.next();
                    result = currentService.ratesHistorical(currency1,dateFrom,dateTo);
                    break;
                case 12:
                    CurrencyDto currencyDto = currentService.parseDto();
                    result = currencyDto.toString();

                    break;
                default:
                    result = "Nie rozpoznano wyboru";
            }

            System.out.println(result);

        } catch (CustomException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
