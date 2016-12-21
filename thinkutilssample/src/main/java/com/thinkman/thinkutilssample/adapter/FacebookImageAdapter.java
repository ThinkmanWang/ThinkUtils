package com.thinkman.thinkutilssample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.at.facebooktypeimagegrid.Assymetric.AsymmetricRecyclerView;
import com.at.facebooktypeimagegrid.Assymetric.AsymmetricRecyclerViewAdapter;
import com.at.facebooktypeimagegrid.Assymetric.Utils;
import com.at.facebooktypeimagegrid.adapter.ChildAdapter;
import com.at.facebooktypeimagegrid.model.ItemList;
import com.thinkman.thinkutils.commonutils.ToastUtils;
import com.thinkman.thinkutilssample.R;

import java.util.List;

public class FacebookImageAdapter extends RecyclerView.Adapter<FacebookImageAdapter.MyViewHolder> {

    private List<ItemList> mItemList;
    private Context mCon;
    private int mDisplay= 0;
    private int mTotal= 0;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public AsymmetricRecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            recyclerView = (AsymmetricRecyclerView) view.findViewById(R.id.recyclerView);


            recyclerView.setRequestedColumnCount(3);
            recyclerView.setDebugging(true);
            recyclerView.setRequestedHorizontalSpacing(Utils.dpToPx(mCon, 3));
            recyclerView.addItemDecoration(
                    new SpacesItemDecoration(mCon.getResources().getDimensionPixelSize(R.dimen.card_corner_radius)));


        }
    }


    public FacebookImageAdapter(Context con, List<ItemList> moviesList, int max_display, int mTotal_size) {
        mCon = con;
        this.mItemList = moviesList;
        mDisplay = max_display;
        mTotal = mTotal_size;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.facebook_image_item, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItemList item = mItemList.get(position);
        String title = "FXXK";
        holder.tvTitle.setText(Html.fromHtml(title));
        ChildAdapter adapter = new ChildAdapter(item.getImages(),mDisplay,mTotal);
        holder.recyclerView.setAdapter(new AsymmetricRecyclerViewAdapter<>(mCon, holder.recyclerView, adapter));
        holder.recyclerView.setOnItemClickListener(new AsymmetricRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View convertView, int position) {
                ToastUtils.showToast(mCon, "FXXK " + position);
            }
        });
    }
 
    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}