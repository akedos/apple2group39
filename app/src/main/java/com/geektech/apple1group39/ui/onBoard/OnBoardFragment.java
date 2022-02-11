package com.geektech.apple1group39.ui.onBoard;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.apple1group39.R;
import com.geektech.apple1group39.databinding.FragmentOnBoardBinding;


public class OnBoardFragment extends Fragment {


    Adapter adapter;
    private FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new Adapter();
        binding.VpOnBoard.setAdapter(adapter);

        binding.VpOnBoard.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position==2){
                    binding.buttonSkip.setVisibility(View.INVISIBLE);
                }else {
                    binding.buttonSkip.setVisibility(View.VISIBLE);
                }
                binding.buttonSkip.setOnClickListener(view1 -> {
                    NavController navController = Navigation.findNavController((Activity) view.getContext(),R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();
                });
            }
        });
    }
}