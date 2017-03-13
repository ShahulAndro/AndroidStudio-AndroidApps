package shahul.network.retrofit.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shahul.network.retrofit.security.HttpClientFactory;

/**
 * Created by shahulhameed on 08/03/2017.
 */

public class ApiClient extends BaseApiClient {
    private static Retrofit mRetrofit = null;

    public ApiClient(){
    }

    private static Retrofit getRetrofitClient() {
        if (mRetrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(HttpClientFactory.createOkClient(isDebug))
                    .build();
        }
        return mRetrofit;
    }

    public static <T> T getService(Class<T> serviceClass){
        return getRetrofitClient().create(serviceClass);
    }
}
