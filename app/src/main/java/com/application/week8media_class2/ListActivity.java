package com.application.week8media_class2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = findViewById(R.id.list_view);
        SharedPreferences sharedPref = getSharedPreferences("URL_LIST", MODE_PRIVATE);
        Set<String> urlList = sharedPref.getStringSet("urlList", new HashSet<>());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>(urlList));

        listView.setAdapter(arrayAdapter);
    }
}

