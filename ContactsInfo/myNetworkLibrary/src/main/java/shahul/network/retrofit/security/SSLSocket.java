package shahul.network.retrofit.security;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * Created by shahulhameed on 09/03/2017.
 */

public class SSLSocket {

    public static SSLSocketFactory getFactory (Boolean debugEnabled) {

        if (!debugEnabled) {
            return (SSLSocketFactory) SSLSocketFactory.getDefault();
        }

        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{new TrustEverythingManager()}, new SecureRandom());
            return ctx.getSocketFactory();

        }
        catch (NoSuchAlgorithmException | KeyManagementException e) {
            // if there's failure
            return (SSLSocketFactory) SSLSocketFactory.getDefault();
        }
    }
}
