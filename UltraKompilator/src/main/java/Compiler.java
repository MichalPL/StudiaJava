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
    ArrayList<Mnemonic> list;
    Map<String, Integer> labelMap = new HashMap<String, Integer>();

    public Compiler(MnemonicMap map, RegistryMap registryMap, FileWorker fileWorker, Converter converter)
    {
        this.map = map;
        this.registryMap = registryMap;
        this.fileWorker = fileWorker;
        this.converter = converter;
        list = new ArrayList<Mnemonic>();
        labelMap = new HashMap<String, Integer>();
    }

    public void compile()
    {
        if(fileWorker.getLinesCount() == -1) return;
        System.out.println("Trwa kompilacja...");
        Mnemonic mnemonic;
        int lineCounter = 0;
        for (int i = 1; i <= fileWorker.getLinesCount(); i++) {
            String line = fileWorker.getLine(i).replaceAll("^\\s+", "");
            if(!line.isEmpty() && !line.startsWith(";")) {
                lineCounter++;
                if (isLabel(line.replaceAll("\\s", ""))) { //jeśli to etykieta
                    labelMap.put(line.replaceAll("\\W+", ""), lineCounter + 1);
                } else {
                    String[] splitString = line.split("\\W+");
                    splitString[0] = splitString[0].toLowerCase();
                    if (isValidMnemonic(splitString[0]) || isLabel(splitString[0])) { //poprawny mnemonik?
                        if (map.getArgCount(splitString[0]) == -1) {
                            System.out.println("Bład podczas kompilacji w linii: " + i);
                            System.exit(0);
                        } else if (map.getArgCount(splitString[0]) == 0) { //typu clear
                            //jeśli mnemonik bez argumentowy
                            mnemonic = new Mnemonic(splitString[0], "a", "0");
                            list.add(mnemonic);
                        } else if (map.getArgCount(splitString[0]) == 1) {
                            splitString[0] = splitString[0].toLowerCase();
                            splitString[1] = splitString[1].toLowerCase();
                            if (isValidArgWhenOnlyOne(splitString[0], splitString[1])) { //jesli poprawny argument mnemonika (nie uwzgledniajac jmp)
                                mnemonic = new Mnemonic(splitString[0], "a", splitString[1]);
                                list.add(mnemonic);
                            } else if (isJump(splitString[0], splitString[1])) { //jesli to jump
                                mnemonic = new Mnemonic(splitString[0], "a", splitString[1]);
                                list.add(mnemonic);
                            } else {
                                System.out.println("Blad! Nieprawidlowy argument 1 w linii: " + i);
                                System.exit(0);
                            }
                        } else if (map.getArgCount(splitString[0]) == 2) {
                            splitString[0] = splitString[0].toLowerCase();
                            splitString[1] = splitString[1].toLowerCase();
                            splitString[2] = splitString[2].toLowerCase();
                            if (isValidArg1(splitString[1])) { //poprawny arg1?
                                if (isValidArg2(splitString[0], splitString[2])) { //poprawny arg2?
                                    if (!isTheSameRegisty(splitString[0], splitString[1], splitString[2])) { // jesli nie jest ten sam rejestr
                                        if (isSyntaxCorrect(line)) { //poprawna skladnia (przecinek miedzy argumentami)
                                            mnemonic = new Mnemonic(splitString[0], splitString[1], splitString[2]);
                                            list.add(mnemonic);
                                        } else {
                                            System.out.println("Blad! Niepoprawna skladnia! Sprawdz przecinek w linii " + i);
                                            System.exit(0);
                                        }
                                    } else {
                                        System.out.println("Blad! Probujesz wykonac operacje na tym samym rejestrze w linii: " + i);
                                        System.exit(0);
                                    }
                                } else {
                                    System.out.println("Blad! Nieprawidlowy argument 2 w linii: " + i);
                                    System.out.println(splitString[2]);
                                    System.exit(0);
                                }
                            } else {
                                System.out.println("Blad! Nieprawidlowy argument 1 w linii: " + i);
                                System.exit(0);
                            }
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
        fileWorker.createFile();
        for (Mnemonic aList : list) {
            fileWorker.addToFile(map.getBinary(aList.getName()));
            fileWorker.addToFile(registryMap.getBinary(aList.getArg1()));
            if (aList.getName().endsWith("x") || aList.getName().equals("inc") || aList.getName().equals("dec")) {
                fileWorker.addToFile("00000" + registryMap.getBinary(aList.getArg2()) + "\n");
            } else if (!aList.getName().endsWith("x") && isNumber(aList.getArg2())) { //jesli rejestr z liczba
                fileWorker.addToFile(converter.getString(converter.convertToU2(aList.getArg2())) + "\n");
            } else if(isJump(aList.getName(), aList.getArg2())) {
                if(labelMap.get(aList.getArg2()) != null) {
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

    private boolean isValidMnemonic(String name)
    {
        return map.isMnemonic(name);
    }

    private boolean isValidArg1(String arg)
    {
        return registryMap.isRegistry(arg);
    }

    private boolean isValidArg2(String name, String arg2)
    {
        if(name.endsWith("x") && registryMap.isRegistry(arg2)) return true;
        else if(isNumber(arg2) && !name.endsWith("x")) return true;
        return false;
    }

    //niestety mnemoniki jednoargumentowe sa zazwyczaj dosc specyficzne, wiec trzeba kazdego osobno rozpatrywac
    private boolean isValidArgWhenOnlyOne(String name, String arg)
    {
        if((name.equals("negx") || name.equals("inc")
                || name.equals("dec")) && registryMap.isRegistry(arg)) return true;
        else if((name.equals("neg") || name.equals("movzx")) && isNumber(arg)) return true;
        return false;
    }

    private boolean isJump(String name, String arg)
    {
        return (name.equals("jmp")) //|| name.equals("jw") || name.equals("jm") || name.equals("jr")
                && !isNumber(arg);
    }

    private boolean isTheSameRegisty(String name, String arg1, String arg2) //zeby uniknac np. kopiowania z tego samego rejestru
    {
        return name.endsWith("x") && (arg1.equals(arg2));
    }

    private boolean isNumber(String name) //czy string to liczba -> do sprawdzania drugiego argumentu
    {
        try
        {
            int d = Integer.parseInt(name);
            if(d > 127 || d < -128) return false;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    private boolean isSyntaxCorrect(String line) //sprawdzanie czy przy dwoch argumentach jest przecinek miedzy nimi
    {
        String[] splitString = line.split("\\W+");
        return (map.getArgCount(splitString[0].toLowerCase()) == 2 && line.contains(","));
    }

    private boolean isLabel(String name)
    {
        return name.endsWith(":");
    }
}
