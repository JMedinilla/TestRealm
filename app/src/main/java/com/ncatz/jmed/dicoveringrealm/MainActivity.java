package com.ncatz.jmed.dicoveringrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ncatz.jmed.dicoveringrealm.adapter.UserAdapter;
import com.ncatz.jmed.dicoveringrealm.pojo.User;
import com.ncatz.jmed.dicoveringrealm.realm.UserService;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private EditText editText;

    private UserService userService;
    private UserAdapter adapter;
    private User[] userList;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addUser) {
            userService.insertUser(
                    new User(
                            new Random().nextInt(),
                            "NOMBRE",
                            new Random().nextInt(),
                            "EMAIL"));
            updateList();
        }
        if (v.getId() == R.id.findUser) {
            String usid = editText.getText().toString();
            User u = userService.getUser(Integer.valueOf(usid));
            if (u != null) {
                Toast.makeText(this, "Encontrado : " + u.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateList() {
        getAllusers();
        adapter.updateList(Arrays.asList(userList));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userService = new UserService(App.getRealmObject());

        listView = findViewById(R.id.list);
        editText = findViewById(R.id.userToFind);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user = adapter.getItem(position);
                if (user != null) {
                    userService.deleteUser(user);
                    updateList();
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllusers();
        instanceList();
    }

    private void getAllusers() {
        userList = userService.getUsers();
    }

    private void instanceList() {
        adapter = new UserAdapter(this);
        adapter.updateList(Arrays.asList(userList));
        listView.setAdapter(adapter);
    }
}
