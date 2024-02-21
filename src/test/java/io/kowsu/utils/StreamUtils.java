package io.kowsu.utils;

/*
    @created February/14/2024 - 5:42 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/

public class StreamUtils {

    public static String reverseString(String abc) {
        char[] chars = abc.toCharArray();
        char result[] = new char[abc.length()];
        int j = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            result[j] = chars[i];
            j++;
        }
        return new String(result);
    }
}
