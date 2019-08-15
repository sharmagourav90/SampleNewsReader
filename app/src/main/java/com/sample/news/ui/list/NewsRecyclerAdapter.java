package com.sample.news.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.news.R;
import com.sample.news.data.models.MediaEntity;
import com.sample.news.data.models.NewsEntity;
import com.sample.news.databinding.NewsItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycler adapter for news list screen
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {
    private List<NewsEntity> mNewsItems = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ItemClickListener mItemClickListener;

    public NewsRecyclerAdapter(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setNewsItems(List<NewsEntity> newsItems) {
        mNewsItems = newsItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.news_item, parent, false);
        return new NewsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.itemBinding.setNewsItem(mNewsItems.get(position));
        String url = null;
        List<MediaEntity> multiMediaList = mNewsItems.get(position).getMultimedia();
        if(multiMediaList != null) {
            url = multiMediaList.get(0).getUrl();
        }
        holder.itemBinding.setUrl(url);
        holder.itemBinding.setItemClickListener(mItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    /**
     * Holder class for showing items in recycler view.
     */
    static class NewsViewHolder extends RecyclerView.ViewHolder {
        public NewsItemBinding itemBinding;

        public NewsViewHolder(@NonNull NewsItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
