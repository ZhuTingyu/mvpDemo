package com.mpvdemo.app.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mpvdemo.app.R;

import java.util.ArrayList;
import java.util.List;

import model.entity.ProjectContentEntity;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class ProjectContentListAdapter extends BaseQuickAdapter<ProjectContentEntity, BaseViewHolder> {
    public ProjectContentListAdapter() {
        super(R.layout.item_project_content_list_layout, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder holder, ProjectContentEntity item) {
        holder.setText(R.id.name,"sdfasdfasdf");
    }
}
