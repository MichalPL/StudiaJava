import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Michal on 2016-03-17.
 */
public class Manager {
    MnemonicMap mnemonicMap;
    RegistryMap registryMap;
    Compiler compiler;
    FileWorker fileWorker;
    Converter converter;
    Validator validator;
    String fileName = "";

    public Manager() {
        preStart();
        mnemonicMap = new MnemonicMap();
        registryMap = new RegistryMap();
        fileWorker = new FileWorker(fileName);
        converter = new Converter();
        validator = new Validator(registryMap, mnemonicMap);
        compiler = new Compiler(mnemonicMap, registryMap, fileWorker, converter, validator);
    }

    public void preStart()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj nazwe pliku do skompilowania!");
        try {
            fileName +=  System.getProperty("user.dir") + "\\" + br.readLine();
        } catch (IOException e) {
            System.out.println("Blad podczas wczytywania pliku...");
        }
    }

    public void run()
    {
        compiler.compile();
    }
}
