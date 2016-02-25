package com.asosapp.phone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asosapp.phone.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ASOS_zhulr on 2015/12/28.
 * 咨询适配器
 */
public class NewsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private LayoutInflater mInfalater;
    private Context context;
    private List<Map<String, Object>> mDatas;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public List<Map<String, Object>> getList() {
        return mDatas;
    }






    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public NewsAdapter(Context context, List<Map<String, Object>> datas) {
        this.context = context;
        this.mDatas = datas;
        mInfalater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = mInfalater.inflate(R.layout.item_single_card_list, viewGroup, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
        if (i == TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_single_card_list, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ItemViewHolder(view);
        } else if (i == TYPE_FOOTER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.footerview, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int pos) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).intro.setText(mDatas.get(pos).get("intro").toString());
            ((ItemViewHolder) holder).title.setText(mDatas.get(pos).get("title").toString());
        }
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
            //longclick
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }



    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView intro;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.id_title);
            intro = (TextView) itemView.findViewById(R.id.id_intro);
        }
}


}


