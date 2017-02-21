package com.example.storm.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storm.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("FirstFragment", "onAttach");


    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("FirstFragment", "onCreate");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("FirstFragment", "onCreateView");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("FirstFragment", "onActivityCreated");
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.i("FirstFragment", "onStart");
    }

    public FirstFragment() {
    }
    static  FirstFragment fragment;
    public static FirstFragment newInstance() {
        if(fragment==null){
            Bundle args = new Bundle();
            fragment=new FirstFragment();
            fragment.setArguments(args);
        }
        return fragment;
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.i("FirstFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("FirstFragment", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("FirstFragment", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("FirstFragment", "onDestroyView");

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FirstFragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("FirstFragment", "onDetach");
    }
}
