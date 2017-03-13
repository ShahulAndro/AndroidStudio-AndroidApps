package shahul.mylibrary.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import shahul.mylibrary.listener.OnRecyclerViewItemClickListener;


/**
 * Created by shahulhameed on 12/03/2017.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    public abstract void bindData(T data);

    public abstract void setPosition(int position);

    public abstract void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener, int position);

    public BaseViewHolder(View view){
        super(view);
    }
}
