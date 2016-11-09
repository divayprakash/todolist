package io.github.todolist.todolist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Divay Prakash on 09-Nov-16.
 */

public class DataFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<TodoItem> todoItemArrayList;
    public DataFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<TodoItem> todoItemArrayList) {
        super(fragmentManager);
        this.todoItemArrayList = todoItemArrayList;
    }
    @Override
    public Fragment getItem(int index) {
        return PageFragment.newInstance(todoItemArrayList.get(index));
    }
    @Override
    public int getCount() {
        return todoItemArrayList.size();
    }
}
