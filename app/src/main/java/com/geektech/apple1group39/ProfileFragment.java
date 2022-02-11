package com.geektech.apple1group39;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.geektech.apple1group39.databinding.FragmentProfileBinding;
import com.geektech.apple1group39.ui.local_bace.Prefs;


public class ProfileFragment extends Fragment {
    Uri uri;
    Prefs prefs;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs=new Prefs(requireContext());
    }

    private FragmentProfileBinding binding;
    private ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode()== Activity.RESULT_OK){
                        uri=result.getData().getData();
                        prefs.saveImg(String.valueOf(uri));
                        binding.add.setImageURI(uri);
                    }

                }

            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                        resultLauncher.launch(intent);
            }
        });
        binding.etName.setText(prefs.getName());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (prefs.getImg()!=null) uri= Uri.parse(prefs.getImg());
        Glide.with(requireActivity()).load(uri).circleCrop().into(binding.add);

    }

    @Override
    public void onStop() {
        super.onStop();

        prefs.saveName(binding.etName.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(requireActivity()).load(uri).circleCrop().into(binding.add);

    }
}