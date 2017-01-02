package com.werb.pickphotoview.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.werb.pickphotoview.MyApp;
import com.werb.pickphotoview.PickConfig;
import com.werb.pickphotoview.R;
import com.werb.pickphotoview.util.PickUtils;

import java.util.List;

/**
 * Created by wanbo on 2016/12/31.
 */

public class PickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> imagePaths;
    private boolean isShowCamera;
    private int spanCount;

    public PickAdapter(List<String> imagePaths, boolean isShowCamera, int spanCount) {
        this.imagePaths = imagePaths;
        this.isShowCamera = isShowCamera;
        this.spanCount = spanCount;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GridImageViewHolder(LayoutInflater.from(MyApp.getApp()).inflate(R.layout.item_grid_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == PickConfig.CAMERA_TYPE){

        }else {
            String path = imagePaths.get(position);
            GridImageViewHolder gridImageViewHolder = (GridImageViewHolder) holder;
            gridImageViewHolder.bindItem(path);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(isShowCamera){
            return PickConfig.CAMERA_TYPE;
        }else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        if(isShowCamera){
            return imagePaths.size() + 1;
        }else {
            return imagePaths.size();
        }
    }

    // ViewHolder
    private class GridImageViewHolder extends RecyclerView.ViewHolder{

        private ImageView gridImage;

        GridImageViewHolder(View itemView) {
            super(itemView);
            gridImage = (ImageView) itemView.findViewById(R.id.iv_grid);

            int screenWidth = PickUtils.getWidthPixels();
            int space = PickUtils.dp2px(PickConfig.ITEM_SPACE);
            int width = (screenWidth - (spanCount+1)*space)/spanCount;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) gridImage.getLayoutParams();
            params.width = width;
            params.height = width;
        }

        public void bindItem(String path){
//            Glide.with(MyApp.getApp()).load(Uri.parse("file://" + path)).into(gridImage);
        }
    }
}
