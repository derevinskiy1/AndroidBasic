package com.example.storm.htmljs;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragment extends BasePageFragment {
//
//    private String title;
    private TextView tv;
//
//    public static PageFragment newInstance(String title) {
//        PageFragment fragment = new PageFragment();
//        Bundle args = new Bundle();
//        args.putString("key_fragment_title", title);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        title = getArguments().getString("key_fragment_title");
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tv = new TextView(getActivity());
        Log.i(TAG, "onCreateView: ");
        return tv;
    }

    @Override
    public void fetchData() {

    }
//
//    @Override
//    public void fetchData() {
//        tv.setText(title);
//        /** * 在这里请求网络。 */
//    }


}
