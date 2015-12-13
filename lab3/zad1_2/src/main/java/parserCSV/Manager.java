package parserCSV;

/**
 * Created by Michal on 2015-11-30.
 */
public class Manager {
    Divider div;
    ConfigManager config;
    PeselParser peselp;
    EmailParser emailp;
    Service service;
    public Manager() throws Exception {
        config = new ConfigManager();
        div = new Divider(config.getIntegerProperty("rowsPerFile"), config.getProperty("outputFilename"), config.getProperty("errorsFilename"));
        peselp = new PeselParser();
        emailp = new EmailParser();
        service = new Service(config.getProperty("filename"));
        run();
    }

    private void run() throws Exception {
        for (Row row : service.getList()) {
            String currentPesel = row.getPesel();
            String currentEmail = row.getEmail();
            try {
                if (peselp.isValid(currentPesel) && emailp.isValid(currentEmail)) {
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
