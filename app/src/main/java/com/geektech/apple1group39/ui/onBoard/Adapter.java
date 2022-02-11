package com.geektech.apple1group39.ui.onBoard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.Navigation;
import androidx.navigation.NavController;
import com.geektech.apple1group39.R;
import com.geektech.apple1group39.databinding.ItemOnboardBinding;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ItemOnboardBinding binding;
    private int[]img=new int[]{R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground};
    private String[]title=new String[]{"first","second","past"};
    private String[]subTitle=new String[]{"erh", "rgfd","gerthhyt"};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=ItemOnboardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(position);

    if (position==2){
        binding.buttonStart.setVisibility(View.VISIBLE);
    }else {
        binding.buttonStart.setVisibility(View.INVISIBLE);
    }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemOnboardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }


        public void bind(int position) {
            binding.titleTv.setText(title[position]);
            binding.subtitleTv.setText(subTitle[position]);
            binding.boardIv.setImageResource(img[position]);
            binding.buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController((Activity) view.getContext(),R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();

                }
            });
        }


    }

}
