package com.example.user.contentproviderapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SHOVON on 1/16/2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    private ArrayList<CursorModel> modelData;
    Cursor dataCursor;
    Context context;
    View view;
    private int lastSelectedPosition = -1;


    public ContactsAdapter(Cursor dataCursor, Context context, ArrayList<CursorModel> modelData) {
        this.dataCursor = dataCursor;
        this.context = context;
        this.modelData = modelData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ContactsAdapter.ViewHolder holder, final int position) {

        holder.contactNameTV.setText(modelData.get(position).getContactName());
        holder.contactNumberTV.setText(modelData.get(position).getContactNumber());

        holder.selectionStateRB.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
        return modelData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contactNameTV;
        TextView contactNumberTV;
        RelativeLayout contactsRowRL;
        RadioButton selectionStateRB;

        public ViewHolder(View itemView) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.contactNameTV);
            contactNumberTV = itemView.findViewById(R.id.contactNumberTV);
            contactsRowRL = itemView.findViewById(R.id.contactsRowRL);
            selectionStateRB = itemView.findViewById(R.id.selectionStateRB);

            selectionStateRB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                    Toast.makeText(context,""+modelData.get(lastSelectedPosition).getContactName(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
