package cat.uab.elai.hmacwithsha256;
//4.30.2019
//Verified working with the online HMAC generator: https://codebeautify.org/hmac-generator
//https://developer.android.com/reference/javax/crypto/Mac

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String myMsg = "1";// message to send
        String myKey = "MySecret";//shared secret
        HMAC_SHA256(myMsg, myKey);
    }
    //code below adopted from https://stackoverflow.com/questions/36004761/is-there-any-function-for-creating-hmac256-string-in-android
    private void HMAC_SHA256(String message, String key) {
        try {
            final String hashingAlgorithm = "HmacSHA256"; //or "HmacSHA1", "HmacSHA512"

            byte[] bytes = hmac(hashingAlgorithm, key.getBytes(), message.getBytes());

            final String messageDigest = bytesToHex(bytes);

            Log.i("TAG", "message digest: " + messageDigest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static byte[] hmac(String algorithm, byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(message);
    }
    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0, v; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
