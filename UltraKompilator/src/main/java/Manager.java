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
    String fileName = "";

    public Manager() {
        preStart();
        mnemonicMap = new MnemonicMap();
        registryMap = new RegistryMap();
        fileWorker = new FileWorker(fileName);
        converter = new Converter();
        compiler = new Compiler(mnemonicMap, registryMap, fileWorker, converter);
    }

    public void preStart()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj nazwe pliku do skompilowania!");
        try {
            fileName +=  System.getProperty("user.dir") + "\\" + br.readLine();
            //fileName = System.getProperty("user.dir") + "\\" + "aaa.txt";
        } catch (IOException e) {
            System.out.println("Blad podczas wczytywania pliku...");
        }
    }

    public void run()
    {
        compiler.compile();
    }
}
