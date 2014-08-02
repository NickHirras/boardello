/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package boardello.service;

/**
 *
 * @author nicholas
 */
public class SlugUtil {
    public static final String create(String str) {
        if(str == null || str.length() < 1) {
            return "";
        }

        int length = 255;
        if(str.length() < 255) {
            length = str.length();
        }
        String slug = "";
        for(int i=0; i<length; i++) {
            char c = str.charAt(i);
            if(Character.isLetterOrDigit(c)) {
                slug = slug + Character.toLowerCase(c);            
            } else {
                slug = slug + "-";
            }
        }
        
        slug = slug.replaceAll("--", "-");
        
        return slug;
    }
}
