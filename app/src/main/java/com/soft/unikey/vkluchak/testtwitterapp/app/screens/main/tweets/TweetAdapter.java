package com.soft.unikey.vkluchak.testtwitterapp.app.screens.main.tweets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soft.unikey.vkluchak.testtwitterapp.R;
import com.soft.unikey.vkluchak.testtwitterapp.app.utils.DateConverterUtils;
import com.soft.unikey.vkluchak.testtwitterapp.app.utils.ImageLoadUtil;
import com.soft.unikey.vkluchak.testtwitterapp.app.utils.TextViewUtils;
import com.soft.unikey.vkluchak.testtwitterapp.data.model.ui_model.TweetUiModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class TweetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private List<TweetUiModel> dataList = new ArrayList<>();

    public TweetAdapter(Context context, List<TweetUiModel> tweetList) {
        this.mContext = context;
        this.dataList = tweetList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet, parent, false);
        return new TweeterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        TweeterViewHolder holder = (TweeterViewHolder) viewHolder;
        TweetUiModel tweetItem = dataList.get(position);

        String userName = mContext.getString(R.string.placeholder);
        String userProfileImageUrl = null;

        if (tweetItem.getUser() != null) {
            userName = tweetItem.getUser().getName();
            userProfileImageUrl = tweetItem.getUser().getProfileImageUrl();
        }

        holder.tvUserName.setText(
                TextViewUtils.getTextForTextView(mContext, userName));

        ImageLoadUtil.loadPersonImageByUrl(mContext, holder.ivUserAvatar, userProfileImageUrl);

        holder.tvCreatedAt.setText(
                TextViewUtils.getTextForTextView(
                        mContext,
                        DateConverterUtils.convertFromMillisToUIDateFormat(tweetItem.createdAt)));
        holder.tvText.setText(
                TextViewUtils.getTextForTextView(mContext, tweetItem.text));

        if(tweetItem.isTweetSync()) {
            holder.tvTextSyncFlag.setVisibility(View.GONE);
        }else {
            holder.tvTextSyncFlag.setVisibility(View.VISIBLE);
            holder.tvTextSyncFlag.setText(R.string.tv_tweet_not_sync);
        }

    }

    public void setNewData(List<TweetUiModel> tweetList) {
        this.dataList.clear();
        this.dataList.addAll(tweetList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (dataList != null) {
            return dataList.size();
        } else return 0;
    }

    public class TweeterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivUserAvatar)
        ImageView ivUserAvatar;
        @BindView(R.id.tvUserName)
        TextView tvUserName;
        @BindView(R.id.tvCreatedAt)
        TextView tvCreatedAt;
        @BindView(R.id.tvText)
        TextView tvText;
        @BindView(R.id.tvTextSyncFlag)
        TextView tvTextSyncFlag;

        public TweeterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
