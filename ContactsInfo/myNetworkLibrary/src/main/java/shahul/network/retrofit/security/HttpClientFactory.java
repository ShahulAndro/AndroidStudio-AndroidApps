package shahul.network.retrofit.security;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
/**
 * Created by shahulhameed on 09/03/2017.
 */
public class HttpClientFactory{

    public static OkHttpClient createOkClient (boolean isDebug) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//        clientBuilder.addInterceptor(new CustomInterceptor());

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(interceptor);

        if (isDebug) {
            clientBuilder.sslSocketFactory(SSLSocket.getFactory(true));
        }


        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);


        return clientBuilder.build();
    }

}