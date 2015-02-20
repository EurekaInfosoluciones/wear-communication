package com.eurekainfosoluciones.wearcom.util;

public class StringUtil {

    public static boolean isEmtyOrNull(String text){
        return text == null || text.isEmpty();
    }

    public static boolean isNotEmptyOrNull(String text){
        return !isEmtyOrNull(text);
    }

}
