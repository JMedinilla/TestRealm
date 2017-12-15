package com.ncatz.jmed.dicoveringrealm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ncatz.jmed.dicoveringrealm.R;
import com.ncatz.jmed.dicoveringrealm.pojo.User;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    private Context context;

    public UserAdapter(@NonNull Context context) {
        super(context, R.layout.adapter_user);
        this.context = context;
    }

    public void updateList(List<User> list) {
        clear();
        if (list != null) {
            if (list.size() > 0) {
                addAll(list);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        AdapterHolder adapterHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_user, parent, false);
            adapterHolder = new AdapterHolder();
            adapterHolder.user = view.findViewById(R.id.adapter_user_string);
            view.setTag(adapterHolder);
        } else {
            adapterHolder = (AdapterHolder) view.getTag();
        }

        User user = getItem(position);
        if (user != null) {
            adapterHolder.user.setText(user.toString());
        }

        return view;
    }

    @Nullable
    @Override
    public User getItem(int position) {
        return super.getItem(position);
    }

    private class AdapterHolder {
        TextView user;
    }
}
