package com.mpvdemo.app.adapter;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mpvdemo.app.R;

import java.util.ArrayList;

import model.entity.ProjectEntity;

/**
 * Created by chenshuai on 2017/11/3.
 */

public class ProjectListAdapter extends BaseQuickAdapter<ProjectEntity,BaseViewHolder> {

    private onClickListener listener;

    public ProjectListAdapter() {
        super(R.layout.item_project_list_layout, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder holder, ProjectEntity item) {
        TextView name = holder.getView(R.id.name);
        name.setOnClickListener(v -> {
            listener.OnNameClick();
        });

        holder.setText(R.id.content, "详细：夹角阿拉丁会计法连接的 阿来得及发力法警队法律监督 asjdlffja");
        holder.setText(R.id.name, "项目名称");
    }

    public interface onClickListener{
        void OnNameClick();
    }

    public void setListener(onClickListener onClickListener){
        this.listener = onClickListener;
    }
}
