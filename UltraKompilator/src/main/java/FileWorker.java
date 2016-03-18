import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Michal on 2016-03-17.
 */
public class FileWorker {
    BufferedReader br;
    FileInputStream fs;
    String file;
    String basename;

    public FileWorker(String file)
    {
        this.file = file;
        basename = FilenameUtils.getBaseName(file) + "_compiled.txt";
    }

    public void createFile()
    {
        try {
            deleteFile();
            Files.createFile(Paths.get(basename));
        } catch (IOException e) {
            System.out.println("Nie mozna utworzyc pliku!");
        }
    }

    public void deleteFile()
    {
        try {
            if(Files.exists(Paths.get(basename)))
                Files.delete(Paths.get(basename));
        } catch (IOException e) {
            System.out.println("Nie mozna usunac pliku!");
        }
    }

    public String getLine(int lineNumber)
    {
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            for (int i = 0; i < lineNumber - 1; i++)
                    br.readLine();
            String line = br.readLine();
            br.close();
            return line;
        } catch (IOException e) {
            System.out.println("Nie mozna przeczytac pliku!");
            System.exit(0);
        }
        return "";
    }

    public int getLinesCount()
    {
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            int lines = 0;
            while (br.readLine() != null) lines++;
            br.close();
            return lines;
        } catch (IOException e) {
            System.out.println("Nie mozna odczytac pliku!");
            System.exit(0);
        }
        return -1;
    }

    public void addToFile(String s)
    {
        try {
            Files.write(Paths.get(basename), s.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println("Nie mozna zapisac do pliku!");
            System.exit(0);
        }
    }
}
