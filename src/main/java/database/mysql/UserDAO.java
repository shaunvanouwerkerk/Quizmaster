package database.mysql;

import javafx.scene.control.Alert;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO implements GenericDAO<User>{

    public UserDAO(DBAccess dbAccess) {
        super(dbAccess);
    }


    @Override
    public ArrayList<User> getAll() {
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
    public User getOneById(int id) {
        User user = null;
        String sql = "SELECT * FROM user WHERE idUser = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if(resultSet.next()) {
                int idUser = resultSet.getInt("idUser");
                String password = resultSet.getString("password");
                String username = resultSet.getString("name");
                String role = resultSet.getString("roleName");
                user = new User(idUser, password, username, role);
            } else {
                Alert foutmelding = new Alert(Alert.AlertType.ERROR);
                foutmelding.setContentText("Er bestaat geen gebruiker met dit ID");
                foutmelding.show();
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return user;
    }

    @Override
    public void storeOne(User user) {
        String sql = "INSERT INTO user(password, name, roleName) VALUES(?, ?, ?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getRoleName());
            int key = executeInsertStatementWithKey();
            user.setIdUser(key);
        } catch (SQLException sqlException) {
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            foutmelding.setContentText("Gebruiker kon niet worden opgeslagen.");
            foutmelding.show();
            System.out.println(sqlException.getMessage());
        }
    }
}
