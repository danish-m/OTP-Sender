package com.example.otpsender;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.otpsender.R;

public class ContactViewAdapter extends RecyclerView.Adapter<ContactViewAdapter.ViewHolder> {

    static   List<ContactModel> dbList;
    static  Context context;

    ContactViewAdapter(Context context, List<ContactModel> dbList ){
        this.dbList = new ArrayList<ContactModel>();
        this.context = context;
        this.dbList = dbList;

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
    public ContactViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.contact_card_layout,null,false);

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLayoutView.setLayoutParams(lp);
        //return new RecyclerViewHolder(rootView);

        // create ViewHolder

        ContactViewAdapter.ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ContactViewAdapter.ViewHolder holder, int position) {

        holder.f_name_intent = dbList.get(position).getFname();
        holder.l_name_intent = dbList.get(position).getLname();
        holder.p_number_intent = dbList.get(position).getPhone_number();


        holder.f_name.setText(holder.f_name_intent);
        holder.l_name.setText(holder.l_name_intent);

        //holder.r_Image.setImageResource(R.drawable.lunchmenu);
        holder.p_number.setText(holder.p_number_intent);


       //Log.v("RID",holder.r_id);
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //List<> mList;
        DatabaseHelper dbHelper;
        private String f_name_intent, l_name_intent, p_number_intent ;
        public TextView f_name,l_name, p_number;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            f_name = (TextView) itemLayoutView
                    .findViewById(R.id.fname);
            l_name = (TextView)itemLayoutView.findViewById(R.id.lname);
            p_number = (TextView)itemLayoutView.findViewById(R.id.pnumber);

            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {


            Intent intent = new Intent(context, ContactDetailsActivity.class);

            Bundle extras = new Bundle();
            extras.putString("fname",f_name_intent);
            extras.putString("lname",l_name_intent);
            extras.putString("pnumber",p_number_intent);
            //Log.v("REC",Integer.toString(r.getR_id()));
            //Log.v("REC",Integer.toString(r_id));

            //mList = new ArrayList<MenuItemsModel>();
            //dbHelper = new DatabaseHelper(context);
            //mList = dbHelper.getMenuItems(r_id);
            //extras.putSerializable("List",ArrayList<MenuItemsModel>mList);
            //extras.putSerializable("List",(Serializable)mList);
            intent.putExtras(extras);


            //int i=getAdapterPosition();
            //intent.putExtra("position", getAdapterPosition());
            //Toast.makeText(context,""+getAdapterPosition(),Toast.LENGTH_SHORT).show();
           context.startActivity(intent);

        }
    }
}
