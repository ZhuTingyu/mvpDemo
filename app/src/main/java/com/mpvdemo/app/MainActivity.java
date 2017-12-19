package com.mpvdemo.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mpvdemo.app.adapter.ProjectListAdapter;

import java.util.ArrayList;
import java.util.List;

import model.entity.ProjectEntity;
import presenter.MainPresenter;
import presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView list;
    private TextView textView;
    private MainPresenter presenter;
    private ProjectListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImpl(this);
        initView();
        initData();
    }

    private void initData() {
        presenter.onGetData(entity -> {
            adapter.setNewData(entity);
        });
        /*List<ProjectEntity> data = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            data.add(new ProjectEntity());
        }
        adapter.setNewData(data);*/
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.text);
        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectListAdapter();
        adapter.setListener(() -> {
            Intent intent = new Intent(MainActivity.this, ProjectContentActivity.class);
            startActivity(intent);
        });
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
