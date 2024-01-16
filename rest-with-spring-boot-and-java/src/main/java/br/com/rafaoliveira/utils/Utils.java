package br.com.rafaoliveira.utils;

public class Utils {
    public static boolean isNumeric(String value){
        if(value.isEmpty()){
            return false;
        }

        value = value.replace(",", ".");

        return value.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static Double convertStringToDouble(String value){
        if(value.isEmpty()){
            return 0D;
        }

        value = value.replaceAll(",", ".");

        if(isNumeric(value)){
            return Double.parseDouble(value);
        }

        return 0D;
    }
}
