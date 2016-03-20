import java.util.ArrayList;

/**
 * Created by Michal on 2016-03-17.
 */
public class Compiler {
    MnemonicMap map;
    RegistryMap registryMap;
    FileWorker fileWorker;
    Converter converter;
    ArrayList<Mnemonic> list;

    public Compiler(MnemonicMap map, RegistryMap registryMap, FileWorker fileWorker, Converter converter)
    {
        this.map = map;
        this.registryMap = registryMap;
        this.fileWorker = fileWorker;
        this.converter = converter;
        list = new ArrayList<Mnemonic>();
    }

    public void compile()
    {
        if(fileWorker.getLinesCount() == -1) return;
        System.out.println("Trwa kompilacja...");
        Mnemonic mnemonic;
        for (int i = 1; i <= fileWorker.getLinesCount(); i++) {
            String line = fileWorker.getLine(i).replaceAll("^\\s+", "").replaceAll(", ", ",");
            if(!line.isEmpty() && !line.startsWith(";")) {
                String[] splitString = line.split("[ ,]");
                splitString[0] = splitString[0].toLowerCase();
                //System.out.println(splitString[0] + "|" + splitString[1] + "|" + splitString[2]);
                if (isValidMnemonic(splitString[0])) { //poprawny mnemonik?

                    if(map.getArgCount(splitString[0]) == -1) {
                        System.out.println("Bład podczas kompilacji w linii: " + i);
                        System.exit(0);
                    } else if(map.getArgCount(splitString[0]) == 0) { //typu clear
                        mnemonic = new Mnemonic(splitString[0], "a", "0");
                        list.add(mnemonic);
                    } else if(map.getArgCount(splitString[0]) == 1) {
                        splitString[0] = splitString[0].toLowerCase();
                        splitString[1] = splitString[1].toLowerCase();
                        if (isValidArgWhenOnlyOne(splitString[0], splitString[1])) { //poprawny arg? typu jump
                            mnemonic = new Mnemonic(splitString[0], "a", splitString[1]);
                            list.add(mnemonic);
                        } else {
                            System.out.println("Blad! Nieprawidlowy argument 1 w linii: " + i);
                            System.exit(0);
                        }
                    } else if(map.getArgCount(splitString[0]) == 2) {
                        splitString[0] = splitString[0].toLowerCase();
                        splitString[1] = splitString[1].toLowerCase();
                        splitString[2] = splitString[2].toLowerCase();
                        if (isValidArg1(splitString[1])) { //poprawny arg1?
                            if (isValidArg2(splitString[0], splitString[2])) { //poprawny arg2?
                                if (!isTheSameRegisty(splitString[0], splitString[1], splitString[2])) {
                                    mnemonic = new Mnemonic(splitString[0], splitString[1], splitString[2]);
                                    list.add(mnemonic);
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
                    System.exit(0);
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
            //System.out.println(map.getBinary(aList.getName()));
            fileWorker.addToFile(registryMap.getBinary(aList.getArg1()));
            //System.out.println(registryMap.getBinary(aList.getArg1()));
            if (aList.getName().endsWith("x")) { //jesli rejestr z rejestrem
                fileWorker.addToFile("00000" + registryMap.getBinary(aList.getArg2()));
                //System.out.println("00000" + registryMap.getBinary(aList.getArg2()));
            } else if (isNumber(aList.getArg2()) && !aList.getName().endsWith("x")) { //jesli rejestr z liczba
                fileWorker.addToFile(converter.getString(converter.convertToU2(aList.getArg2())));
                //System.out.println(converter.getString(converter.convertToU2(aList.getArg2())));
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
        else if(name.startsWith("j")) return true; //TODO: jump nie jest zrobiony!
        return false;
    }

    private boolean isTheSameRegisty(String name, String arg1, String arg2)
    {
        return name.endsWith("x") && (arg1.equals(arg2));
    }

    private boolean isNumber(String name)
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

}
