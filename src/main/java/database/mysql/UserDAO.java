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
            Alert opgeslagen = new Alert(Alert.AlertType.CONFIRMATION);
            opgeslagen.setHeaderText(String.format("Gebruiker %s is opgeslagen", user.getUsername()));
            opgeslagen.setContentText(String.format("ID: %d, Rol: %s", user.getIdUser(),user.getRoleName()));
            opgeslagen.show();
        } catch (SQLException sqlException) {
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            if(sqlException.getMessage().contains("Duplicate")) {
                foutmelding.setContentText("Deze gebuikersnaam bestaat al! Gebruiker is niet opgslagen.");
            } else {
                foutmelding.setContentText("Gebruiker kon niet worden opgeslagen.");
            }
            foutmelding.show();
            System.out.println(sqlException.getMessage());
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE user SET password = ?, name = ?, roleName = ? WHERE idUser = ?";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getRoleName());
            preparedStatement.setInt(4, user.getIdUser());
            System.out.println(user);
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            if(sqlException.getMessage().contains("Duplicate")) {
                foutmelding.setContentText("Deze gebuikersnaam bestaat al! Gebruiker is niet gewijzigd.");
            } else {
                foutmelding.setContentText("Gebruiker kon niet worden gewijzigd.");
            }
            foutmelding.show();
            System.out.println(sqlException.getMessage());
        }
    }

    public ArrayList<String> getAllRoles() {
        String sql = "SELECT * FROM role;";
        ArrayList<String> allRoles = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();

            while (resultSet.next()) {
                String role = resultSet.getString("roleName");
                allRoles.add(role);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return allRoles;
    }

    public void deleteUser(User user) {
        String sql = "DELETE FROM user WHERE idUser = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, user.getIdUser());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public ArrayList<Integer> getAllCourseId(int idStudent) {
        System.out.println("Begin van pull courses");
        ArrayList<Integer> allCourseId = new ArrayList<>();
        int courseId;
        String sql = "SELECT * FROM studentincourse WHERE idStudent = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idStudent);
            ResultSet resultSet = executeSelectStatement();

            while (resultSet.next()) {
                courseId = resultSet.getInt("idCourse");
                allCourseId.add(courseId);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return allCourseId;
    }
}
