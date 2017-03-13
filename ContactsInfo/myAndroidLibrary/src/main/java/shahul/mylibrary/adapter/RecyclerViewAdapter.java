package shahul.mylibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import shahul.mylibrary.factory.IViewHolderFactory;
import shahul.mylibrary.listener.OnRecyclerViewItemClickListener;
import shahul.mylibrary.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahulhameed on 12/03/2017.
 */
public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    private List<T> mData = new ArrayList<>();
    private IViewHolderFactory mFactory;
    private OnRecyclerViewItemClickListener mListener;

    /**
     *
     * @param factory
     */
    public RecyclerViewAdapter(IViewHolderFactory factory){
        mFactory = factory;
    }

    /**
     *
     * @param data
     */
    public void addAll(List<T> data){
        if(data == null || data.isEmpty()){
            return;
        }

        if(isNullOrEmpty()){
            mData = new ArrayList<>();
        }

        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     *
     * @param data
     */
    public void add(T data){
        if(isNullOrEmpty()){
            mData = new ArrayList<>();
        }

        mData.add(data);
        notifyItemInserted(mData.size() - 1);
        notifyItemRangeChanged(mData.size() - 1, getItemCount());
    }

    /**
     *
     * @param position
     * @param data
     */
    public void add(int position, T data){
        if(isNullOrEmpty()){
            mData = new ArrayList<>();
        }

        position = position == -1 ? getItemCount()  : position;
        mData.add(position, data);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     *
     * @param dataList
     */
    public void updateAll(List<T> dataList){
        if(dataList == null || dataList.size() < 1){
            return;
        }

        if(isNullOrEmpty()){
            mData = new ArrayList<>();
        }else{
            mData.removeAll(dataList) ;
        }

        mData.addAll(dataList);
        notifyDataSetChanged();
    }

    /**
     *
     * @param position
     * @param data
     */
    public void update(int position, T data){
        mData.set(position, data);
        notifyItemChanged(position);
    }

    /**
     *
     */
    public void remove(){
        if(isNullOrEmpty()){
            return;
        }

        mData.remove(mData.size() - 1);
        notifyItemRemoved(mData.size());
    }

    /**
     *
     * @param position
     */
    public void remove(int position){
        if (position < getItemCount()  ) {
            mData.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    /**
     *
     * @param data
     */
    public void remove(T data){
        int position = mData.indexOf(data);
        mData.remove(data);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     *
     * @param position
     * @param data
     */
    public void remove(int position, T data){
        mData.remove(data);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    /**
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setOnItemClickListener(mListener, position);
        holder.setPosition(position);
        holder.bindData(mData.get(position));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mFactory.create(parent.getContext());
    }

    @Override
    public int getItemCount() {
        if(isNullOrEmpty()) {
            return 0;
        }

        return mData.size();
    }

    /**
     *
     * @return
     */
    public List<T> getTotalItems(){
        if(isNullOrEmpty()){
            return new ArrayList<T>();
        }

        return mData;
    }

    /**
     *
     * @param position
     * @return
     */
    public T getItem(int position){
        if(isNullOrEmpty()){
            return null;
        }

        if (position >= getItemCount()  ) {
            return null;
        }

        return mData.get(position);
    }

    /**
     *
     * @return
     */
    private boolean isNullOrEmpty(){
        if(mData == null || mData.isEmpty()){
            return true;
        }

        return false;
    }
}
