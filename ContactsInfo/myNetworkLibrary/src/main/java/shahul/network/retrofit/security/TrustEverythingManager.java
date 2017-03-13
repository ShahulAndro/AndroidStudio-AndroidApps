package shahul.network.retrofit.security;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Created by shahulhameed on 09/03/2017.
 */
public class TrustEverythingManager implements X509TrustManager {

    @Override
    public void checkClientTrusted (X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    @Override
    public void checkServerTrusted (java.security.cert.X509Certificate[] certs,
                                    String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers () {
        return new java.security.cert.X509Certificate[]{};
    }
}