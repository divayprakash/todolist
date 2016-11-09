package io.github.todolist.todolist;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Button addTodoItemButton;
    private Button addTodoItemCancelButton;
    private EditText addTodoItemTitle;
    private EditText addTodoItemDesc;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] titles = new String[100];
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        List<TodoItem> todoItemList = db.getItems();
        int i = 0;
        for (TodoItem item : todoItemList) {
            titles[i]= (item.getTitle());
            i++;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        recyclerView = (RecyclerView)findViewById(R.id.todoitems_view);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), titles);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    public void onAddItem(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.fragment_add_todo_item);
        dialog.setTitle(R.string.add_todoitem_fragment_text);
        addTodoItemButton = (Button) dialog.findViewById(R.id.add_todoitem_button);
        addTodoItemCancelButton = (Button) dialog.findViewById(R.id.add_todoitem_button_cancel);
        addTodoItemTitle = (EditText) dialog.findViewById(R.id.add_todoitem_title);
        addTodoItemDesc = (EditText) dialog.findViewById(R.id.add_todoitem_desc);

        addTodoItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addTodoItemTitle.getText().toString().trim().length() > 0 && addTodoItemDesc.getText().toString().trim().length() > 0) {
                    String[] pass = new String[100];
                    DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                    db.addItem(new TodoItem((addTodoItemTitle.getText().toString()), addTodoItemDesc.getText().toString()));
                    Toast.makeText(MainActivity.this,"ToDoItem added!",Toast.LENGTH_LONG).show();
                    List<TodoItem> todoItemList = db.getItems();
                    int i = 0;
                    for (TodoItem item : todoItemList) {
                        pass[i] = item.getTitle();
                        i++;
                    }
                    recyclerView = (RecyclerView)findViewById(R.id.todoitems_view);
                    staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setLayoutManager(staggeredGridLayoutManager);
                    recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), pass);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(MainActivity.this, "Please enter title and description correctly!", Toast.LENGTH_LONG).show();
                }
            }
        });
        addTodoItemCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}