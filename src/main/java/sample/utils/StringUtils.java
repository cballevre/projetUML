package sample.utils;

public class StringUtils {

    public static String ucfirst(String chaine){
        return chaine.substring(0, 1).toUpperCase()+ chaine.substring(1).toLowerCase();
    }

    public static String lcfirst(String chaine) {
        return chaine.substring(0, 1).toLowerCase() + chaine.substring(1);
    }

}
