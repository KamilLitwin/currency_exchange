package pl.file;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ObjectToCsvPrinter {

    private static final String NEW_LINE_SEPARATOR = "\n";
    public static Object[] FILE_HEADER = {};

    public boolean writeCsvFileWithStrInput(String outFileName, List<List<String>> inputData) {

        if (null == FILE_HEADER || FILE_HEADER.length == 0) {
            return false;
        }

        if (null == inputData || inputData.size() == 0) {
            return false;
        }

        FileWriter fileWriter = null;

        CSVPrinter csvFilePrinter = null;

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {
            fileWriter = new FileWriter(outFileName);

            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            csvFilePrinter.printRecord(FILE_HEADER);

            for (List<String> inputStr : inputData) {
                csvFilePrinter.printRecord(inputStr);
            }
            System.out.println("CSV file was created successfully !!!");
        } catch (Exception e) {
            System.out.println("Error file was created successfully !!!");
        } finally {
            FILE_HEADER = null;
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
        FILE_HEADER = null;
        return true;
    }
}
