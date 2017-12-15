package com.ncatz.jmed.dicoveringrealm.realm;

import com.ncatz.jmed.dicoveringrealm.pojo.User;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserService {

    private Realm realm;

    public UserService(Realm realm) {
        this.realm = realm;
    }

    public User[] getUsers() {
        RealmResults<User> results = realm.where(User.class).findAll();
        return results.toArray(new User[results.size()]);
    }

    public User getUser(int id) {
        return realm.where(User.class).equalTo("id", id).findFirst();
    }

    public void updateUser(User user) {

    }

    public void insertUser(User user) {
        realm.beginTransaction();

        User u = realm.createObject(User.class, user.getId());
        u.setName(user.getName());
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());

        realm.commitTransaction();
    }

    public void deleteUser(User user) {
        realm.beginTransaction();

        user.deleteFromRealm();

        realm.commitTransaction();
    }
}
