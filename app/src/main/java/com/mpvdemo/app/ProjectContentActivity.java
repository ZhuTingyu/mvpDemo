package com.mpvdemo.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mpvdemo.app.adapter.ProjectContentListAdapter;

import java.util.ArrayList;
import java.util.List;

import model.entity.ProjectContentEntity;
import presenter.ProjectContentPresenter;
import presenter.ProjectContentPresenterImpl;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class ProjectContentActivity extends AppCompatActivity implements MainView {

    private RecyclerView list;
    private ProjectContentPresenter presenter;
    private ProjectContentListAdapter adapter;
    private TextView textView;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new ProjectContentPresenterImpl(this);
        initView();
        initData();

    }

    private void initData() {

        presenter.onGetData("",projectContentEntities -> {

        });


        List<ProjectContentEntity> data = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            data.add(new ProjectContentEntity());
        }
        adapter.setNewData(data);
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.text);
        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectContentListAdapter();
        list.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        textView.setVisibility(View.GONE);
    }
}
