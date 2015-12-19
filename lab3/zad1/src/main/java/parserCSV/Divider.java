package parserCSV;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 2015-11-30.
 */
public class Divider {

    private String outputFilename;
    private int linesCount;
    private int outputFilesCount = 0;
    private List<String> validRecords;
    private PrintWriter writerForInvalid;

    public Divider(int linesCount, String outputFilename, String errorsFilename) throws FileNotFoundException {
        validRecords = new ArrayList<>();
        outputFilesCount = 0;
        this.outputFilename = outputFilename;
        this.linesCount = linesCount;
        writerForInvalid = new PrintWriter("results/" + errorsFilename + ".csv");
    }

    public void clear() throws IOException {
        //proceed_1000_1.csv
        PrintWriter pwriter = new PrintWriter("results/" + outputFilename
                + "_" + validRecords.size() + "_" + (++outputFilesCount) + ".csv");

        validRecords.forEach(pwriter::println);
        validRecords.clear();
        pwriter.close();
    }

    public void saveValidRecord(String row) throws IOException {
        validRecords.add(row);
        if(validRecords.size() == linesCount) {
            clear();
        }
    }

    public void saveInvalidRecrod(String row) {
        writerForInvalid.println(row);
    }

    public void closeWriters() throws IOException {
        if(!validRecords.isEmpty()) {
            clear();
        }
        writerForInvalid.close();
    }
}
