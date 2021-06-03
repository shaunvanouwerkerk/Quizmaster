package database.mysql;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO implements GenericDAO{

    public UserDAO(DBAccess dbAccess) {
        super(dbAccess);
    }



    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        User user;
        String sql = "SELECT * from user";

        try {
            setupPreparedStatement(sql);

            ResultSet resultSet = executeSelectStatement();

            while (resultSet.next()){
                String password = resultSet.getString("password");
                String username = resultSet.getString("name");
                String roleName = resultSet.getString("roleName");

                user = new User(password, username, roleName);
                int idUser = resultSet.getInt("idUser");
                user.setIdUser(idUser);

                users.add(user);

            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return users;
    }

    @Override
    public ArrayList getAll() {
        return null;
    }

    @Override
    public Object getOneById(int id) {
        return null;
    }

    @Override
    public void storeOne(Object type) {

    }
}
