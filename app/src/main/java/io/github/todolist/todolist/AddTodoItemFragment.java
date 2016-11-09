package io.github.todolist.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class AddTodoItemFragment extends DialogFragment {
    public AddTodoItemFragment() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.add_todoitem_fragment_text);
        return builder.create();
    }
}
