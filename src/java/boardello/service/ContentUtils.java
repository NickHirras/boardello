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
public class ContentUtils {

  public static final String createSlug(String str) {
    if (str == null || str.length() < 1) {
      return "";
    }

    int length = 255;
    if (str.length() < 255) {
      length = str.length();
    }
    String slug = "";
    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      if (Character.isLetterOrDigit(c)) {
        slug = slug + Character.toLowerCase(c);
      } else {
        slug = slug + "-";
      }
    }

    while (slug.indexOf("--") >= 0) {
      slug = slug.replaceAll("--", "-");
    }

    if (slug.endsWith("-")) {
      slug = slug.substring(0, slug.length() - 1);
    }

    return slug;
  }

  public static final String createTitle(String str) {
    int targetLength = 50;

    if (str == null || str.length() < 1) {
      return "";
    }

    str = str.replaceAll("\\s+", " ");

    String title = "";
    for (byte b : str.getBytes()) {
      if (Character.isJavaIdentifierPart((char) b)) {
        title = title + (char) b;
      } else if (Character.isSpace((char) b)) {
        title = title + " ";
      }
      if (title.length() >= targetLength) {
        title = title.substring(0, targetLength - 3) + "...";
        break;
      }
    }

    title = title.trim();

    return title;
  }

}
