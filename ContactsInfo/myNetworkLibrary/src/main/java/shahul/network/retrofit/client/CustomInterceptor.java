package shahul.network.retrofit.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by shahulhameed on 08/03/2017.
 */

public class CustomInterceptor implements Interceptor {

    Map<String, String> mHeadersMap;

    public CustomInterceptor(){
    }

    public CustomInterceptor(String name, String value) {
        this();
        Map<String, String> header = new HashMap<>();
        header.put(name, value);
        mHeadersMap = header;
    }

    public CustomInterceptor(HashMap<String, String> headers) {
        mHeadersMap = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if(isHeadersAvailable()) {
            Headers.Builder headerBuilder = chain.request().headers().newBuilder();
            headerBuilder = addHeaders(headerBuilder);
            Headers moreHeaders = headerBuilder.build();
            request = request.newBuilder().headers(moreHeaders).build();
        }

        return chain.proceed(request);
    }

    /**
     *
     * @param headerBuilder
     * @return
     */
    public Headers.Builder addHeaders(Headers.Builder headerBuilder){
        if(headerBuilder == null){
            return headerBuilder;
        }
        if(mHeadersMap == null || mHeadersMap.isEmpty() || mHeadersMap.size() < 1){
            return headerBuilder;
        }

        Set<Map.Entry<String, String>> headersEntrySet = mHeadersMap.entrySet();
        if(headersEntrySet == null || headersEntrySet.isEmpty()){
            return headerBuilder;
        }

        Iterator<Map.Entry<String, String>> headersIterator = headersEntrySet.iterator();
        if(headersIterator == null){
            return headerBuilder;
        }

        while (headersIterator.hasNext())
        {
            Map.Entry mapEntry = (Map.Entry) headersIterator.next();
            String keyName = (String) mapEntry.getKey();
            String value = (String) mapEntry.getValue();
            headerBuilder.add(keyName, value);
        }

        return headerBuilder;
    }

    private boolean isHeadersAvailable(){
        if(mHeadersMap == null || mHeadersMap.isEmpty() || mHeadersMap.size() < 1){
            return false;
        }

        Set<Map.Entry<String, String>> headersEntrySet = mHeadersMap.entrySet();
        if(headersEntrySet == null || headersEntrySet.isEmpty()){
            return false;
        }

        Iterator<Map.Entry<String, String>> headersIterator = headersEntrySet.iterator();
        if(headersIterator == null){
            return false;
        }

        return true;
    }
}
