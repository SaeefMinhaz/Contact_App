package com.example.user.contentproviderapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SHOVON on 1/16/2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    Cursor dataCursor;
    Context context;
    View view;
//    private MyObjectTapListener tapListener;
//    public static String sectionStr=null;
//    ActionModeCallback actionModeCallBack;
//    ActionMode actionMode;


    public ContactsAdapter(Cursor dataCursor, Context context) {
        this.dataCursor = dataCursor;
        this.context = context;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {

        holder.contactNameTV.setText(dataCursor.getPosition());
        holder.contactNumberTV.setText(dataCursor.getColumnName(position));
    }

    @Override
    public int getItemCount() {
        return (dataCursor==null)?0 : dataCursor.getCount();
    }

//    public Cursor swapCursor(Cursor cursor) {
//        if (dataCursor == cursor) {
//            return null;
//        }
//        Cursor oldCursor = dataCursor;
//        this.dataCursor = cursor;
//        if (cursor != null) {
//            this.notifyDataSetChanged();
//        }
//        return oldCursor;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contactNameTV;
        TextView contactNumberTV;

        public ViewHolder(View itemView) {
            super(itemView);

            contactNameTV = itemView.findViewById(R.id.contactNameTV);
            contactNumberTV = itemView.findViewById(R.id.contactNumberTV);
        }
    }
}
