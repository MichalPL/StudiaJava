import javafx.scene.shape.VLineTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michal on 2016-03-17.
 */
public class Compiler {
    MnemonicMap map;
    RegistryMap registryMap;
    FileWorker fileWorker;
    Converter converter;
    Validator validator;
    ArrayList<Mnemonic> list;
    Map<String, Integer> labelMap;
    Mnemonic mnemonic;

    public Compiler(MnemonicMap map, RegistryMap registryMap, FileWorker fileWorker,
                    Converter converter, Validator validator)
    {
        this.map = map;
        this.registryMap = registryMap;
        this.fileWorker = fileWorker;
        this.converter = converter;
        this.validator = validator;
        list = new ArrayList<Mnemonic>();
        labelMap = new HashMap<String, Integer>();
    }

    public void compile()
    {
        if(fileWorker.getLinesCount() == -1) return; //jesli nie udalo sie pobrac ilosci wierszy w pliku
        System.out.println("Trwa kompilacja...");
        int lineCounter = 0; //zlicza kolejne wiersze w skompilowanym pliku
        for (int i = 1; i <= fileWorker.getLinesCount(); i++) {
            String line = fileWorker.getLine(i).replaceAll("^\\s+", ""); //usuniecie z wiersza spacji na lewo od poczatku pierwszego slowa
            if(!line.isEmpty() && !line.startsWith(";")) { //jesli linia nie jest pusta lub nie jest komentarzem
                lineCounter++; //zwiekszamy ilosc wierszy w skompilowanym pliku
                if (validator.isLabel(line.replaceAll("\\s", ""))) { //jeśli to etykieta
                    labelMap.put(line.replaceAll("\\W+", ""), lineCounter + 1); //dodanie do listy etykiet i usuniecie dwukropkow itp itd
                } else {
                    String[] splitString = line.split("\\W+"); //podzielenie stringa do tablicy
                    splitString[0] = splitString[0].toLowerCase();
                    if (validator.isValidMnemonic(splitString[0]) || validator.isLabel(splitString[0])) { //jesli poprawny mnemonik lub jest to etykieta
                        if (map.getArgCount(splitString[0]) == -1) { //jakis blad
                            System.out.println("Bład podczas kompilacji w linii: " + i);
                            System.exit(0);
                        } else if (map.getArgCount(splitString[0]) == 0) { //kiedy ilosc arg to 0 typu clear
                            whenArgsNumberIs0(splitString); //jeśli mnemonik bez argumentowy
                        } else if (map.getArgCount(splitString[0]) == 1) { //kiedy ilosc arg to 1
                            whenArgsNumberIs1(splitString, i);
                        } else if (map.getArgCount(splitString[0]) == 2) { //kiedy ilosc arg to 2
                            whenArgsNumberIs2(splitString, line, i);
                        } else {
                            System.out.println("Bład kompilacji w linii: " + i);
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Blad! Nieprawidlowy mnemonik w linii: " + i);
                        System.out.println(splitString[0]);
                        System.exit(0);
                    }
                }
            }
        }
        System.out.println("Zapisywanie...");
        saveToFile();
        System.out.println("Kompilacja zakonczyla sie sukcesem!");
    }

    private void saveToFile()
    {
        //zapis do pliku jest podzielony na 3 etapy:
        //zapis binarny mnemonika | zapis binarny rejestru | zapis binarny rejestru lub liczby
        fileWorker.createFile();
        for (Mnemonic aList : list) {
            fileWorker.addToFile(map.getBinary(aList.getName())); //zapis binarny mnemonika
            fileWorker.addToFile(registryMap.getBinary(aList.getArg1())); //zapis binarny rejestru
            //zapis binarny rejestru lub liczby
            if (aList.getName().endsWith("x") || aList.getName().equals("inc") || aList.getName().equals("dec")) { //jesli mnemonik konczy sie na x lub jest to inc lub dec
                fileWorker.addToFile("00000" + registryMap.getBinary(aList.getArg2()) + "\n");
            } else if (!aList.getName().endsWith("x") && validator.isNumber(aList.getArg2())) { //jesli nie konczy sie na x to musi byc liczba
                fileWorker.addToFile(converter.getString(converter.convertToU2(aList.getArg2())) + "\n");
            } else if(validator.isJump(aList.getName(), aList.getArg2())) { //jesli to jump
                if(labelMap.get(aList.getArg2()) != null) { //szukamy etykiety
                    fileWorker.addToFile(converter.getString(converter.convertToU2(labelMap.get(aList.getArg2()).toString())));
                } else {
                    System.out.println("Blad! Sprawdz etykiety!");
                    System.exit(0);
                }
            } else {
                System.out.println("Blad podczas przetwarzania!");
                System.exit(0);
            }
        }
    }

    private void whenArgsNumberIs0(String [] splitString)
    {
        mnemonic = new Mnemonic(splitString[0], "a", "0");
        list.add(mnemonic);
    }

    private void whenArgsNumberIs1(String [] splitString, int counter)
    {
        splitString[0] = splitString[0].toLowerCase();
        splitString[1] = splitString[1].toLowerCase();
        if (validator.isValidArgWhenOnlyOne(splitString[0], splitString[1])) { //jesli poprawny argument mnemonika
            mnemonic = new Mnemonic(splitString[0], "a", splitString[1]);
            list.add(mnemonic);
        } else if (validator.isJump(splitString[0], splitString[1])) { //jesli to jump
            mnemonic = new Mnemonic(splitString[0], "a", splitString[1]);
            list.add(mnemonic);
        } else {
            System.out.println("Blad! Nieprawidlowy argument 1 w linii: " + counter);
            System.out.println(splitString[1]);
            System.exit(0);
        }
    }

    private void whenArgsNumberIs2(String [] splitString, String line, int counter)
    {
        splitString[0] = splitString[0].toLowerCase();
        splitString[1] = splitString[1].toLowerCase();
        splitString[2] = splitString[2].toLowerCase();
        if (validator.isValidArg1(splitString[1])) { //poprawny arg1?
            if (validator.isValidArg2(splitString[0], splitString[2])) { //poprawny arg2?
                if (!validator.isTheSameRegisty(splitString[0], splitString[1], splitString[2])) { // jesli nie jest ten sam rejestr
                    if (validator.isSyntaxCorrect(line)) { //poprawna skladnia (przecinek miedzy argumentami)
                        mnemonic = new Mnemonic(splitString[0], splitString[1], splitString[2]);
                        list.add(mnemonic);
                    } else {
                        System.out.println("Blad! Niepoprawna skladnia! Sprawdz przecinek w linii " + counter);
                        System.exit(0);
                    }
                } else {
                    System.out.println("Blad! Probujesz wykonac operacje na tym samym rejestrze w linii: " + counter);
                    System.exit(0);
                }
            } else {
                System.out.println("Blad! Nieprawidlowy argument 2 w linii: " + counter);
                System.out.println(splitString[2]);
                System.exit(0);
            }
        } else {
            System.out.println("Blad! Nieprawidlowy argument 1 w linii: " + counter);
            System.out.println(splitString[1]);
            System.exit(0);
        }
    }
}
