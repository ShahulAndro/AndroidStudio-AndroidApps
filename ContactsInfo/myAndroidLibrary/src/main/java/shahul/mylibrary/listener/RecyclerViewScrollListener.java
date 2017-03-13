package shahul.mylibrary.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by shahulhameed on 12/03/2017.
 */

public class RecyclerViewScrollListener extends RecyclerView.OnScrollListener{
    // The minimum amount of items to have below your current scroll position before loading more.
    private int mVisibleThreshold = 5;
    private boolean mScrolled = false;
    private int mPastVisibleItems;
    private int mVisibleItemCount;
    private int mTotalItemCount;

    private LinearLayoutManager mLayoutManager;

    public RecyclerViewScrollListener(LinearLayoutManager layoutManager){
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView,
                                     int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx,
                           int dy) {
        super.onScrolled(recyclerView, dx, dy);
        // Here get the child count, item count and visible items from layout manager
        mVisibleItemCount = mLayoutManager.getChildCount();
        mTotalItemCount = mLayoutManager.getItemCount();
        mPastVisibleItems = mLayoutManager
                .findFirstVisibleItemPosition();

        if(mTotalItemCount < mVisibleThreshold){
            return;
        }

        // Now check if mScrolled is true and also check if the item is end then update recycler
        // view and set mScrolled to false
        if (!mScrolled
                && (mVisibleItemCount + mPastVisibleItems) == mTotalItemCount) {
            mScrolled = true;
        }
    }

    /**
     *
     * @return
     */
    public boolean isScrolled() {
        return mScrolled;
    }

    /**
     *
     * @param scrolled
     */
    public void setScrolled(boolean scrolled) {
        mScrolled = scrolled;
    }
}
