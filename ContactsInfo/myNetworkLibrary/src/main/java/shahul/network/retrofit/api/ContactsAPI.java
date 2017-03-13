package shahul.network.retrofit.api;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import rx.Observable;
import shahul.network.retrofit.client.ApiResult;
import shahul.network.retrofit.entity.ContactsResult;

/**
 * Created by shahulhameed on 09/03/2017.
 */

public interface ContactsAPI {
    @GET("contacts.json?alt=media&token=431c2754-b3f9-4485-8c5d-0365d5f8f0e5")
    Observable<ApiResult<ContactsResult>> getAllContacts();
    @GET("contact-thumb.png?alt=media&token=d68298eb-9879-4c9a-8319-5ef0a9eb3c57")
    Observable<ResponseBody> getProfileThumbnail();
}
