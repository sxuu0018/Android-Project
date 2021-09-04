package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Map;
import com.example.myapplication.PainDataEntry;
import com.example.myapplication.databinding.AddFragmentBinding;
import com.example.myapplication.viewmodel.SharedViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFragment extends Fragment {
    private AddFragmentBinding addBinding;
    public AddFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addBinding = AddFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        SharedViewModel model = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
//        addBinding.addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = addBinding.editText.getText().toString();
//                if (!message.isEmpty() ) {
//                    model.setMessage(message);
//                }
//            }
//        });
//        addBinding.clearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addBinding.editText.setText("");
//            }
//        });

//        addBinding.map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Map.class);
//                startActivity(intent);
//            }
//        });
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String dateString = simpleDateFormat.format(date);

        addBinding.painTopic.setText("Hello!\nToday's date is: "+dateString+"\nYou need to answer the following questions\n1. Pain level\n2. Pain location\n3. Your mood\n4. Steps\n5. Time");
        addBinding.painDataEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PainDataEntry.class);
                startActivity(intent);
            }
        });
        addBinding.textBottom.setText("You can find two pie charts after you insert the pain data!\nAfter you have 'Save', please click 'Confirm' again, otherwise the data will not be added.");
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}