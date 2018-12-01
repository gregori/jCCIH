package br.eti.gregori.ccih.util;

public class EnumUtil {
    public static <T extends Enum> String getStrings(Class<T> clazz){
        String a = "";
        for (T v: clazz.getEnumConstants()) {
            a += "\"" + v + "\",";
        }
        return a.substring(0,a.length() - 1);
    }
}
