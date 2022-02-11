package com.geektech.apple1group39.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.apple1group39.databinding.ItemHomeBinding;
import com.geektech.apple1group39.ui.models.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> list = new ArrayList<>();
    private onItemClick onItemClick;
    private ItemHomeBinding binding;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent , false);
        return new ViewHolder(binding);
    }

    public void setOnItemClick(NewsAdapter.onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void addAll(List<News> news){
        list.addAll(news);
        notifyDataSetChanged();
    }


    public void addItem(News news){
        list.add(news);
        notifyItemInserted(list.size());
    }

    public void setList(List<News> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public News getNews(int pos) {
        return list.get(pos);
    }

    public void deleteNews(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ItemHomeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(News news) {
            binding.tvDate.setText(news.getCreatedAt());
            binding.tvTitle.setText(news.getTitle());

            binding.getRoot().setOnLongClickListener(view -> {
                onItemClick.onItemLongClick(getAdapterPosition());
            return true;
            });
        }
    }
    public interface onItemClick{
        void onItemClick(int pos);
        void onItemLongClick(int pos);
    }
}
