package com.yandex.yandexdataschool.samplewithtests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listView = ((ListView)findViewById(android.R.id.list));
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, R.id.list_item_text, new String[] {
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
        }));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) parent.getAdapter().getItem(position);
                int number = Integer.parseInt(text);
                if (number % 2 == 0) {
                    toolbar.setTitle(text);
                } else {
                    toolbar.setTitle(String.valueOf(-number));
                }
            }
        });
    }

}
