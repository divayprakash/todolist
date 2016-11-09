package io.github.todolist.todolist;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private static final int NUMBER_OF_PAGES = 10;
    private ViewPager viewPager;
    private DataFragmentPagerAdapter dataFragmentPagerAdapter;
    ArrayList<TodoItem> todoItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        todoItemList = new ArrayList<TodoItem>();
        DatabaseHandler db = new DatabaseHandler(DetailActivity.this);
        for (TodoItem item : db.getItems()) {
            todoItemList.add(new TodoItem(item.getTitle(), item.getDesc()));
        }
        dataFragmentPagerAdapter = new DataFragmentPagerAdapter(getSupportFragmentManager(), todoItemList);
        viewPager.setAdapter(dataFragmentPagerAdapter);
    }
}
