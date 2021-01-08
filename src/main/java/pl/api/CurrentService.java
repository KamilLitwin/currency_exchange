package pl.api;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.dao.Dao;
import pl.exception.CustomException;
import pl.gson.CurrencyDto;
import pl.gson.CurrencyHistoryDto;
import pl.mapper.CurrencyMapper;
import pl.model.Currency;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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


    public CurrencyDto parseDto3(String baseCurrency, String exchangeCurrency) throws CustomException {
        CurrencyDto currencyDto;
        Currency currency = Dao.getByDateAndByFromAndTo(baseCurrency,exchangeCurrency);

        if (currency != null) {
            currencyDto = CurrencyMapper.mapCurrencyToCurrencyDto(currency);
        } else {
            String uri = "https://api.exchangeratesapi.io/latest?base=" + baseCurrency + "&symbols=" + exchangeCurrency;
            String json = get(uri);

            Gson gson = new Gson();
            currencyDto = gson.fromJson(json, CurrencyDto.class);

            List<Currency> entities = CurrencyMapper.mapCurrencyDtoToEntity(currencyDto);
            for (Currency entity : entities) {
                Dao.create(entity);
            }
        }

        return currencyDto;
    }

    public CurrencyHistoryDto parseDto4(String dateFrom, String baseCurrency,String exchangeCurrency) throws CustomException{
        CurrencyHistoryDto currencyDto;
        //Currency currency = Dao.getByDateAndByFromAndTo2(dateFrom,baseCurrency,exchangeCurrency);

//        if (currency != null) {
//            currencyDto = CurrencyMapper.mapCurrencyToCurrencyDto(currency);
//        } else {
        String uri = "https://api.exchangeratesapi.io/history?start_at=" + dateFrom + "&end_at=" + dateFrom +"&base=" + baseCurrency + "&symbols=" + exchangeCurrency;
        String json = get(uri);

            Gson gson = new Gson();
            currencyDto = gson.fromJson(json, CurrencyHistoryDto.class);

//            List<Currency> entities = CurrencyMapper.mapCurrencyDtoToEntity(currencyDto);
//            for (Currency entity : entities) {
//                Dao.create(entity);
//            }
//        }
        return currencyDto;
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

            if (stringBuilder.toString().
                    equals("")) {
                throw new CustomException("Jakiś inny komunikat o błędzie");
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

    public void exportCurrencyToCsvFile() {

        List<Currency> currencies = Dao.getAll();

        try {
            PrintWriter writer = new PrintWriter("waluty.csv");
            writer.println("CUR_ID;CUR_BASE_CURRENCY;CUR_DATE;CUR_VALUE");
            for (Currency currency : currencies) {
                writer.printf("%d;%.2f;%s;%s;%s;%.2f\n",
                        currency.getId(),
                        currency.getBaseCurrency(),
                        currency.getOrderDate(),
                        currency.getValue());
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String exportCurrencyToExcelFile() {
        List<Currency> currencies = Dao.getAll();

        Workbook workbook = new XSSFWorkbook(); // obiekt reprezentujący plik xlsx

        Sheet sheet = workbook.createSheet("Kursy walut"); // arkusz w excelu

        String[] columns = "CUR_ID;CUR_BASE_CURRENCY;CUR_DATE;CUR_VALUE".split(";");

        // stworzenie nagłówka (wiersz o indeksie 0)
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // wypełniamny arkusz danymi
        int dataRowIndex = 1;
        for (Currency currency : currencies) {
            Row dataRow = sheet.createRow(dataRowIndex++);

            int dataColumnIndex = 0;
            dataRow.createCell(dataColumnIndex++).setCellValue(currency.getId());
            dataRow.createCell(dataColumnIndex++).setCellValue(currency.getOrderDate());
            dataRow.createCell(dataColumnIndex++).setCellValue(currency.getValue());
            dataRow.createCell(dataColumnIndex++).setCellValue(currency.getBaseCurrency());
        }

        // zapisujemy excel do pliku
        FileOutputStream fileOutputStream = null;
        try {

            File myFile = new File("waluty.xlsx");

            fileOutputStream = new FileOutputStream(myFile);
            workbook.write(fileOutputStream);

            fileOutputStream.close();
            workbook.close();

            return myFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void exportCurrencyToPdf() throws FileNotFoundException, DocumentException {

        List<Currency> currencies = Dao.getAll();

        Document iText_xls_2_pdf = new Document();
        PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream("waluty.pdf"));
        iText_xls_2_pdf.open();
        //we have two columns in the Excel sheet, so we create a PDF table with two columns
        //Note: There are ways to make this dynamic in nature, if you want to.

        String[] columns = "id;amount;base;date;rateName;rateValue".split(";");

        PdfPTable my_table = new PdfPTable(columns.length);
        for (String str : columns) {
            my_table.addCell(new PdfPCell(new Phrase(str)));
        }

        //Loop through rows.
        for (Currency currency : currencies) {
            my_table.addCell(new PdfPCell(new Phrase(currency.getId() + "")));
            my_table.addCell(new PdfPCell(new Phrase(currency.getOrderDate())));
            my_table.addCell(new PdfPCell(new Phrase(currency.getValue() + "")));
            my_table.addCell(new PdfPCell(new Phrase(currency.getBaseCurrency())));
        }

        //Finally add the table to PDF document
        iText_xls_2_pdf.add(my_table);
        iText_xls_2_pdf.close();
        //we created our pdf file..
    }
}
