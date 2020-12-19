package pl.app;

import com.itextpdf.text.DocumentException;
import pl.api.CurrentService;
import pl.exception.CustomException;
import pl.gson.CurrencyDto;
import pl.gson.CurrencyHistoryDto;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainCurrencies {
    public static void main(String[] args) {
        CurrentService currentService = new CurrentService();
        try {

            System.out.println("Wybierz opcję");
            System.out.println("1) Zwróć kursy walut dla euro");
            System.out.println("2) Wyceń kurs w innej walucie");
            System.out.println("3) Wyceń dokładny kurs jednej waluty");
            System.out.println("4) Uzyskaj historyczne kursy walut dla danego okresu");
            System.out.println("5) Zapisz waluty do pliku");

            System.out.println("9) przykład waluta bazowa, waluta do wymiany - baza z datą nieudana ");
            System.out.println("10) Prasowanie JSON - przykład waluta bazowa, waluta do wymiany - baza");
            System.out.println("11) Prasowanie JSON - przykład waluta bazowa, waluta do wymiany");
            System.out.println("12) Prasowanie JSON - przykład");

            Scanner scanner = new Scanner(System.in);
            Scanner scannerNumber = new Scanner(System.in);
            Scanner scannerString = new Scanner(System.in);
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
                case 5:
                    currentService.exportCurrencyToTextFile();
                    currentService.exportCurrencyToCsvFile();
                    String excelPath = currentService.exportCurrencyToExcelFile();

                    System.out.println(excelPath);

                    try {
                        currentService.exportCurrencyToPdf();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                    result = "eksport zakończony";
                    break;
                case 9:
                    System.out.println("Podaj datę (kliknij enter by wybrać bieżącą datę)");
                    String date = scannerString.nextLine();
                    if (date.equals("")) {
                        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    }

                    String from = "";
                    while (from == "") {
                        System.out.println("Podaj walutę bazową (PLN, EUR, USD, GBP)");
                        from = scannerNumber.next();

                        if (!(from.equals("PLN") || from.equals("EUR") || from.equals("USD") || from.equals("GBP"))) {
                            from = "";
                        }
                    }

                    String to = "";
                    while (to == "") {
                        System.out.println("Podaj walutę docelową (PLN, EUR, USD, GBP)");
                        to = scannerNumber.next();

                        if (!(to.equals("PLN") || to.equals("EUR") || to.equals("USD") || to.equals("GBP"))) {
                            to = "";
                        }
                    }

                    CurrencyHistoryDto currencyDto4 = currentService.parseDto4(date,from,to);
                    result = currencyDto4.toString();
                    break;
                case 10:
                    System.out.println("Napisz walutę bazową");
                    String baseCurrency3 = scanner.next();
                    System.out.println("Napisz walutę wymiany");
                    String exchangeCurrency3 = scanner.next();
                    CurrencyDto currencyDto3 = currentService.parseDto3(baseCurrency3,exchangeCurrency3);
                    result = currencyDto3.toString();
                    break;
                case 11:
                    System.out.println("Napisz walutę bazową");
                    String baseCurrency2 = scanner.next();
                    System.out.println("Napisz walutę wymiany");
                    String exchangeCurrency2 = scanner.next();
                    CurrencyDto currencyDto2 = currentService.parseDto2(baseCurrency2,exchangeCurrency2);
                    result = currencyDto2.toString();
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
