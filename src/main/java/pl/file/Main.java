package pl.file;

import pl.api.CurrentService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectToCsvPrinter objectToCsvPrinter = new ObjectToCsvPrinter();
        CurrentService currentService = new CurrentService();

        /*Scanner scanner = new Scanner(System.in);

        System.out.println("Napisz walutę bazową");
        String baseCurrency3 = scanner.next();
        System.out.println("Napisz walutę wymiany");
        String exchangeCurrency3 = scanner.next();*/


        ArrayList<String> m1 = new ArrayList<String>(){
            {
                add("1");
                add("PLN");
                add("EUR");
                add("2020-12-19");
                add("4.2323");
                add("NULL");
            }
        };

        List<List<String>> object = new ArrayList<List<String>>();
        object.add(m1);

        Object[] header = {"CUR_ID","CUR_BASE_CURRENCY", "CUR_CURRENCY","CUR_DATE", "CUR_VALUE", "CUR_RATE"};
       ObjectToCsvPrinter.FILE_HEADER = header;
       String outFile = "C:/Users/arekm/OneDrive/Pulpit/git/przelicznik/src/main/java/pl/file/testObjectToCsvPrinter.csv";

       boolean expectedOutput = true;
        System.out.println(objectToCsvPrinter.writeCsvFileWithStrInput(outFile,object));


    }
}
