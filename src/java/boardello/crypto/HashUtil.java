/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardello.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

  public static String sha512(byte[] plainText, byte[] salt) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.reset();
    md.update(salt);
    byte[] encodedPassword = md.digest(plainText);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < encodedPassword.length; i++) {
      if ((encodedPassword[i] & 0xff) < 0x10) {
        sb.append("0");
      }

      sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
    }
    return sb.toString();
  }
}
