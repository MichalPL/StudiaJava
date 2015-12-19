package parserCSV;

import java.io.IOException;

/**
 * Created by Michal on 2015-11-30.
 */
public class Manager {
    private Divider div;
    private Service service;

    public Manager() throws IOException {
        ConfigManager config = new ConfigManager();
        div = new Divider(config.getIntegerProperty("rowsPerFile"), config.getProperty("outputFilename"), config.getProperty("errorsFilename"));
        service = new Service(config.getProperty("filename"));
        run();
    }

    private void run() throws IOException {
        for (Row row : service.getList()) {
            String currentPesel = row.getPesel();
            String currentEmail = row.getEmail();
            try {
                if (PeselParser.isValid(currentPesel) && EmailParser.isValid(currentEmail)) {
                    div.saveValidRecord(row.toString());
                } else {
                    div.saveInvalidRecrod(row.toString());
                }
            } catch (Exception e) {
                div.saveInvalidRecrod(row.toString());
            }
        }
        div.closeWriters();
    }

}
