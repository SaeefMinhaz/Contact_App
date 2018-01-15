package com.example.user.contentproviderapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>{

    public static String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    public static String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

    TextView outTV = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outTV = (TextView) findViewById(R.id.outTV);

        getSupportLoaderManager().initLoader(1,null,this);
    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        return new android.support.v4.content.CursorLoader(this,CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor cursor) {
        StringBuilder sb = new StringBuilder();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            sb.append("\n" + cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)));
            sb.append(":" + cursor.getString(cursor.getColumnIndex(NUMBER)));
            cursor.moveToNext();
        }
        outTV.setText(sb);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
    }

//    https://stackoverflow.com/questions/29915919/permission-denial-opening-provider-com-android-providers-contacts-contactsprovi/35522711
}
