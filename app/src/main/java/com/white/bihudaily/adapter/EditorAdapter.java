package com.white.bihudaily.adapter;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.white.bihudaily.R;
import com.white.bihudaily.bean.Editor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author White
 * Date 2016/8/16
 * Time 21:31
 */
public class EditorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Editor> mEditors;
    private Fragment mContext;

    public EditorAdapter(Fragment context, List<Editor> editors) {
        this.mEditors = editors;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_item, parent, false);
        return new EditorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Editor editor = mEditors.get(position);
        if (holder instanceof EditorViewHolder) {
            final EditorViewHolder editorViewHolder = (EditorViewHolder) holder;
            Glide.with(mContext).load(editor.getAvatar()).asBitmap().fitCenter().centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new BitmapImageViewTarget(editorViewHolder.ivEditorImage) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            editorViewHolder.ivEditorImage.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return mEditors.size();
    }

    class EditorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_editor_image)
        ImageView ivEditorImage;

        public EditorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
