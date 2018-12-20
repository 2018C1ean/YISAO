package com.example.dell.c1ean.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.c1ean.Fragment.Worker.WorkerHomePageFragment;

public class TabFragment extends Fragment {
    private Context context;
    private String content;

    public TabFragment() {

    }

    public TabFragment(Context context, String content) {
        this.context = context;
        this.content = content;
    }

    public TabFragment(WorkerHomePageFragment workerHomePageFragment, String content) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(context);
        textView.setText(content);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
