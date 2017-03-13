package shahul.mylibrary.factory;

import android.content.Context;

import shahul.mylibrary.viewholder.BaseViewHolder;

/**
 * Created by shahulhameed on 11/03/2017.
 */

public interface IViewHolderFactory {
    BaseViewHolder create(Context context);
}
