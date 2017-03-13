package shahul.network.retrofit.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahulhameed on 09/03/2017.
 */

public class ContactsResult {
    private List<ContactsInfo> results = new ArrayList<>();

    public List<ContactsInfo> getContactsInfoList() {
        return results;
    }
}
