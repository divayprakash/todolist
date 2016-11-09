package io.github.todolist.todolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PageFragment extends Fragment {
    public static PageFragment newInstance(TodoItem item) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", item);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        final TextView textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        final TextView textViewDesc = (TextView) view.findViewById(R.id.textViewDesc);
        TodoItem item = (TodoItem) getArguments().getSerializable("item");
        textViewTitle.setText(item.getTitle());
        textViewDesc.setText(item.getDesc());
        return view;
    }
}
