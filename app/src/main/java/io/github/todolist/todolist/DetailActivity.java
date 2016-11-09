package io.github.todolist.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageButton;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private int NUMBER_OF_PAGES;
    private ViewPager viewPager;
    private DataFragmentPagerAdapter dataFragmentPagerAdapter;
    private ImageButton imageButton;
    ArrayList<TodoItem> todoItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        todoItemList = new ArrayList<TodoItem>();
        DatabaseHandler db = new DatabaseHandler(DetailActivity.this);
        for (TodoItem item : db.getItems()) {
            todoItemList.add(new TodoItem(item.getTitle(), item.getDesc()));
        }
        NUMBER_OF_PAGES = todoItemList.size();
        dataFragmentPagerAdapter = new DataFragmentPagerAdapter(getSupportFragmentManager(), todoItemList);
        viewPager.setAdapter(dataFragmentPagerAdapter);
        viewPager.setCurrentItem(position);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
