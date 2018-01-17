package com.example.user.contentproviderapp;

import android.Manifest;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PERMISSION_CONTACTS = 200;
    private static int LOADER_CONTACTS = 100;

    public static String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    public static String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    Button getDataBtn;
//    TextView contactsTV;

    RecyclerView contactsRV;
    ContactsAdapter contactsAdapter;
    LinearLayoutManager linearLayoutManager;
    Cursor data;

    List<CursorModel> cursorModelList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRV = findViewById(R.id.contactsRV);
        contactsRV.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        contactsRV.setLayoutManager(linearLayoutManager);
        contactsAdapter = new ContactsAdapter(data,this);
//        contactsTV = findViewById(R.id.contactsTV);
        contactsRV.setAdapter(contactsAdapter);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},PERMISSION_CONTACTS);
        } else {
            getLoaderManager().initLoader(LOADER_CONTACTS,null,this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CONTACTS:
                if (grantResults.length >0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    getLoaderManager().initLoader(LOADER_CONTACTS,null,this);
                }else {
                    Toast.makeText(this,"DURE GIA MOR",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
            if(id==LOADER_CONTACTS){
                Uri CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                return new CursorLoader(this,CONTENT_URI,null,null,null, ContactsContract.Contacts.SORT_KEY_PRIMARY);
            }else {
                return null;
            }
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//            StringBuilder sb = new StringBuilder();
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()){
//                sb.append("\n" + cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)));
//                sb.append(":" + cursor.getString(cursor.getColumnIndex(NUMBER)));
//                cursor.moveToNext();
//            }
//            contactsTV.setText(sb);

            contactsAdapter.swapCursor(data);

    }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            contactsAdapter.swapCursor(null);
        }


//    https://stackoverflow.com/questions/29915919/permission-denial-opening-provider-com-android-providers-contacts-contactsprovi/35522711
//    https://androidkennel.org/android-loaders-tutorial/



}
