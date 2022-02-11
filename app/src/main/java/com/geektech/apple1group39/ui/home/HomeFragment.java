package com.geektech.apple1group39.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.apple1group39.R;
import com.geektech.apple1group39.databinding.FragmentHomeBinding;
import com.geektech.apple1group39.ui.App;
import com.geektech.apple1group39.ui.models.News;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter newsAdapter;
    News news;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsAdapter = new NewsAdapter();
        setHasOptionsMenu(true);
        newsAdapter.addAll(App.getAppDataBase().newsDao().getAll());

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.exit){
            requireActivity().finish();
            return true;
        }
        if (item.getItemId() == R.id.sort){
            newsAdapter.setList(App.getAppDataBase().newsDao().sortAll());
            binding.rvHome.setAdapter(newsAdapter);

            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        return  binding.getRoot();


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment();
            }
        });

        binding.rvHome.setAdapter(newsAdapter);

        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("news");
                Log.e("TAG", "успешно: " + news.getTitle());
                newsAdapter.addItem(news);
                binding.rvHome.setAdapter(newsAdapter);

            }
        });

        newsAdapter.setOnItemClick(new NewsAdapter.onItemClick() {
            @Override
            public void onItemClick(int pos) {

            }

            @Override
            public void onItemLongClick(int pos) {
                new AlertDialog.Builder(getContext()).setTitle("Delete").setMessage("You are sure?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                news = newsAdapter.getNews(pos);
                                App.getAppDataBase().newsDao().delete(news);
                                newsAdapter.deleteNews(pos);

                            }
                        }).show();
            }
        });
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

}