package pl.javaFx.buttons;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import pl.dao.Dao;
import pl.model.Currency;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class Print extends Button {
    public Print() {
        setMinSize(300, 200);
        setFont(new Font("Arial", 16));
        setText("Generuj wydruk z baz danych");
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                    List<Currency> currencies = Dao.getAll();

                    Document iText_xls_2_pdf = new Document();
                    try {
                        PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream("waluty.pdf"));
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
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

                try {
                    iText_xls_2_pdf.add(my_table);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                iText_xls_2_pdf.close();
                    //we created our pdf file..
                }

        });
    }
}
