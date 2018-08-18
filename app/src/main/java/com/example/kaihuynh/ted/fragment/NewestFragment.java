package com.example.kaihuynh.ted.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kaihuynh.ted.R;
import com.example.kaihuynh.ted.TalkActivity;
import com.example.kaihuynh.ted.adapter.TalksListRecyclerAdapter;
import com.example.kaihuynh.ted.models.Talk;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewestFragment extends Fragment implements TalksListRecyclerAdapter.ListItemClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Talk> talks;
    private TalksListRecyclerAdapter adapter;
    public static final int SEND_CODE = 111;

    public NewestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newest, container, false);

        getWidgets(view);
        init();
        setWidgets();
        setWidgetsListener();

        return view;
    }

    private void getWidgets(View view) {
        recyclerView = view.findViewById(R.id.rv_newest_talks);
    }

    private void init() {
        talks = new ArrayList<>();
        talks.add(new Talk());
        talks.add(new Talk());
        talks.add(new Talk());
        talks.add(new Talk());
        talks.add(new Talk());
        talks.add(new Talk());
        talks.add(new Talk());
        talks.add(new Talk());

        adapter = new TalksListRecyclerAdapter(getContext(), R.layout.list_talks_item, talks, this);
    }

    private void setWidgets() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    private void setWidgetsListener() {
    }

    @Override
    public void onListItemClick(int clickItemIndex) {
        Intent intent = new Intent(getActivity(), TalkActivity.class);
        getActivity().startActivityForResult(intent, SEND_CODE);
    }
}
