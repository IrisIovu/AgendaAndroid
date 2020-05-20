package eu.ase.angedasincronizareonline.database.service;

import android.content.Context;
import android.os.AsyncTask;

import eu.ase.angedasincronizareonline.database.DBManager;
import eu.ase.angedasincronizareonline.database.dao.UserDao;
import eu.ase.angedasincronizareonline.database.model.User;

public class UserService {

    private static UserDao userDao;

    public static class GetOneByUsername extends AsyncTask<String, Void, User> {
        public GetOneByUsername(Context context) {
            userDao = DBManager
                    .getInstance(context)
                    .getUserDao();
        }

        @Override
        protected User doInBackground(String... strings) {
            if (strings != null && strings.length != 1) {
                return null;
            }

            String name = strings[0];

            return userDao.findUserByUsername(name);
        }
    }

    public static class GetOneById extends AsyncTask<Long, Void, User> {
        public GetOneById(Context context) {
            userDao = DBManager.getInstance(context).getUserDao();
        }

        @Override
        protected User doInBackground(Long... longs) {
            if (longs != null && longs.length != 1) {
                return null;
            }

            long id = longs[0];

            return userDao.findUserById(id);
        }
    }

    public static class Insert extends AsyncTask<User, Void, User> {

        public Insert(Context context) {
            userDao = DBManager
                    .getInstance(context)
                    .getUserDao();
        }

        @Override
        protected User doInBackground(User... users) {
            if (users != null && users.length != 1) {
                return null;
            }

            User user = users[0];
            long id = userDao.insert(user);

            if (id != -1) {
                user.setId(id);
                return user;
            }
            return null;
        }
    }

    public static class Delete extends
            AsyncTask<User, Void, Integer> {
        public Delete(Context context) {
            userDao = DBManager
                    .getInstance(context)
                    .getUserDao();
        }

        @Override
        protected Integer doInBackground(User... users) {
            if (users == null || users.length != 1) {
                return -1;
            }
            return userDao.delete(users[0]);
        }
    }
}
