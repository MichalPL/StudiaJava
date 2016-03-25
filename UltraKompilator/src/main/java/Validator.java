import java.util.HashMap;

/**
 * Created by Michal on 2016-03-25.
 */
public class Validator {
    RegistryMap registryMap;
    MnemonicMap map;

    public Validator(RegistryMap registryMap, MnemonicMap map) {
        this.registryMap = registryMap;
        this.map = map;
    }

    public boolean isValidMnemonic(String name)
    {
        return map.isMnemonic(name);
    }

    public boolean isValidArg1(String arg)
    {
        return registryMap.isRegistry(arg);
    }

    public boolean isValidArg2(String name, String arg2)
    {
        if(name.endsWith("x") && registryMap.isRegistry(arg2)) return true;
        else if(isNumber(arg2) && !name.endsWith("x")) return true;
        return false;
    }

    //niestety mnemoniki jednoargumentowe sa zazwyczaj dosc specyficzne, wiec trzeba kazdego osobno rozpatrywac
    public boolean isValidArgWhenOnlyOne(String name, String arg)
    {
        if((name.equals("negx") || name.equals("inc")
                || name.equals("dec")) && registryMap.isRegistry(arg)) return true;
        else if((name.equals("neg") || name.equals("movzx")) && isNumber(arg)) return true;
        return false;
    }

    public boolean isJump(String name, String arg)
    {
        return (name.equals("jmp")) //|| name.equals("jw") || name.equals("jm") || name.equals("jr")
                && !isNumber(arg);
    }

    public boolean isTheSameRegisty(String name, String arg1, String arg2) //zeby uniknac np. kopiowania z tego samego rejestru
    {
        return name.endsWith("x") && (arg1.equals(arg2));
    }

    public boolean isNumber(String name) //czy string to liczba -> do sprawdzania drugiego argumentu
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

    public boolean isSyntaxCorrect(String line) //sprawdzanie czy przy dwoch argumentach jest przecinek miedzy nimi
    {
        String[] splitString = line.split("\\W+");
        return (map.getArgCount(splitString[0].toLowerCase()) == 2 && line.contains(","));
    }

    public boolean isLabel(String name)
    {
        return name.endsWith(":");
    }
}
