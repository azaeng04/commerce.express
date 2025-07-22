package api.commerce.express.services.general.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cna canal walk
 */
public class PasswordEncrypt {

    public static String encrypt(String password) {
        StringBuilder getString = new StringBuilder();
        try {
            MessageDigest msg = MessageDigest.getInstance("MD5", "SUN");
            byte[] bs = password.getBytes();
            byte[] digest = msg.digest(bs);
            for (byte b : digest) {
                getString.append(Integer.toHexString(0x0100 + (b & 0x00FF)).substring(1));

            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Logger.getLogger(PasswordEncrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getString.toString();

    }
}
