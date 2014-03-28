package com.mc2e.swiperefreshsample.app;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private ListView mListView;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = new ArrayList<String>();

        for (int i = 0; i < 30; i++) mList.add("Sample String Value -" + i);

        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        mListView = (ListView)findViewById(R.id.list_view);

        // Simple String ArrayAdapter
        mListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                mList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                textView.setTextColor(Color.BLACK);

                return textView;
            }
        });

        // change refresh animation color.
        mRefreshLayout.setColorScheme(R.color.simple_purple,
                R.color.simple_red,
                R.color.simple_blue,
                R.color.simple_orange);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Handler mHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {

                        // this method can stop refesh animation
                        if (msg.what == 0)
                            mRefreshLayout.setRefreshing(false);
                    }
                };
                // call stop refresh animation after 5sec.
                mHandler.sendEmptyMessageDelayed(0, 5000);
            }
        });
    }
}
