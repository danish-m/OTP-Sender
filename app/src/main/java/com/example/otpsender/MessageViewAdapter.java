package com.example.otpsender;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.otpsender.R;


public class MessageViewAdapter extends RecyclerView.Adapter<MessageViewAdapter.ViewHolder> {

    static   List<MessageModel> dbList;
    static  Context context;

    MessageViewAdapter(Context context, List<MessageModel> dbList ){
        this.dbList = new ArrayList<MessageModel>();
        this.context = context;
        this.dbList = dbList;
        Log.v("MessageAdapter", ""+dbList.size());

        /*for(int i=0;i<dbList.size();i++)
        {
            RestuarantModel m = dbList.get(i);
            Log.v("Rec", Integer.toString(m.getR_id()));
            Log.v("rLIst", m.getR_name());
            Log.v("rLIst", m.getR_address());
            Log.v("rLIst", m.getR_timing());
            Log.v("rLIst", Integer.toString(m.getR_rating()));

        }*/

    }

    @Override
    public MessageViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.message_card_layout,null,false);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLayoutView.setLayoutParams(lp);
        //return new RecyclerViewHolder(rootView);

        // create ViewHolder

        MessageViewAdapter.ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MessageViewAdapter.ViewHolder holder, int position) {



        holder.name.setText("NAME: "+ dbList.get(position).getName());
        holder.otp.setText("OTP    : "+Integer.toString(dbList.get(position).getOtp()));

        //holder.r_Image.setImageResource(R.drawable.lunchmenu);
        holder.time.setText("TIME  : "+dbList.get(position).getTime());


       //Log.v("RID",holder.r_id);
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //List<> mList;
        DatabaseHelper dbHelper;

        public TextView name,otp, time;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView
                    .findViewById(R.id.message_name);
            otp = (TextView)itemLayoutView.findViewById(R.id.message_otp);
            time = (TextView)itemLayoutView.findViewById(R.id.message_time);

            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {



            //Toast.makeText(context,""+getAdapterPosition(),Toast.LENGTH_SHORT).show();


        }
    }
}
