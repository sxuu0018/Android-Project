package com.example.myapplication.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.FindMyLocation;
import com.example.myapplication.LineChart;
import com.example.myapplication.Map;
import com.example.myapplication.PositionPie;
import com.example.myapplication.ViewPage;
import com.example.myapplication.databinding.ViewFragmentBinding;
import com.example.myapplication.stepsPie;
import com.example.myapplication.viewmodel.SharedViewModel;

public class ViewFragment extends Fragment {
    private ViewFragmentBinding binding;
    public ViewFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = ViewFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binding.textMessage.setText(s);
            }
        });
//        binding.positionPie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), PositionPie.class);
//                intent.putExtra("Confirm","1");
//                startActivity(intent);
//            }
//        });
//        binding.stepsPie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),stepsPie.class);
//                startActivity(intent);
//            }
//        });
        binding.findLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FindMyLocation.class);
                startActivity(intent);
            }
        });
        binding.textMessage.setText("Map and Line chart!\nClick the Map button to start!\nSteps pie chart and Pain position pie chart are in 'Add and View'");
        binding.linechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LineChart.class);
                startActivity(intent);
            }
        });
        binding.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Map.class);
                startActivity(intent);
            }
        });

        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}