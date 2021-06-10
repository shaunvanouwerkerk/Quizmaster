package database.mysql;
/*
 * @author Fiona Lampers
 * */

import javafx.scene.control.Alert;
import model.Course;
import model.Group;
import model.Question;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class

GroupDAO extends AbstractDAO implements GenericDAO<Group>{

    public GroupDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    @Override
    public ArrayList getAll() {
        ArrayList<Group> groups = new ArrayList<>();
        Group group;
        String sql = "SELECT * from `group`";
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()){
                int idGroup= resultSet.getInt("idGroup");
                int idCoordinatorGroup = resultSet.getInt("idCoordinatorGroup");
                int idCourse = resultSet.getInt("idCourse");
                group = new Group(idGroup, idCoordinatorGroup, idCourse);
                groups.add(group);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return groups;
    }

    @Override
    public Group getOneById(int idGroup) {
        String sql = "SELECT * FROM `group` WHERE idGroup = ?";
        Group result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idGroup);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int idCoordinatorGroup = resultSet.getInt("idCoordinatorGroup");
                int idCourse = resultSet.getInt("idCourse");
                result = new Group(idGroup, idCoordinatorGroup, idCourse);
            } else {
                Alert foutmelding = new Alert(Alert.AlertType.ERROR);
                foutmelding.setContentText("Er is geen groep met deze groep ID in de database");
                foutmelding.show();
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return result;
    }

    @Override
    public void storeOne(Group group) {
              String sql = "Insert INTO `group`(idCoordinatorGroup, idCourse) VALUES (?, ?);";
            try{
                setupPreparedStatementWithKey(sql);
                preparedStatement.setInt(1, group.getIdCooridnator());
                preparedStatement.setInt(2, group.getIdCourse());
                int primaryKey = executeInsertStatementWithKey();
                group.setIdGroup(primaryKey);
            } catch (SQLException sqlException) {
                System.out.println(sqlException.getMessage());
            }
    }

    public Group getAllByIdCoordinator(int idCoordinator) {
        String sql = "SELECT * FROM `group` WHERE idCoordinator = ?";
        Group groupWithCoordinator = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idCoordinator);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int idGroup = resultSet.getInt("idGroup");
                int idCourse = resultSet.getInt("idCourse");
                groupWithCoordinator = new Group(idGroup, idCoordinator, idCourse);
            } else {
                Alert foutmelding = new Alert(Alert.AlertType.ERROR);
                foutmelding.setContentText("Er is geen groep met deze coordinator in de database");
                foutmelding.show();
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return groupWithCoordinator;
    }

    public Group getAllByIdCourse(int idCourse) {
        String sql = "SELECT * FROM `group` WHERE idCourse = ?";
        Group groupInCourse = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idCourse);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int idGroup = resultSet.getInt("idGroup");
                int idCoordinator = resultSet.getInt("idCoordinator");
                groupInCourse = new Group(idGroup, idCoordinator, idCourse);
            } else {
                Alert foutmelding = new Alert(Alert.AlertType.ERROR);
                foutmelding.setContentText("Er is geen groep in deze course in de database");
                foutmelding.show();
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return groupInCourse;
    }

    public void deleteGroup(Group group) {
        String sql = "DELETE FROM `group` WHERE idgroup = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, group.getIdGroup());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public void updateGroup(Group group) {
        String sql = "UPDATE `group` SET idCoordinatorGroup = ?, idCourse = ? WHERE idGroup = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, group.getIdCooridnator());
            preparedStatement.setInt(2, group.getIdCourse());
            preparedStatement.setInt(3, group.getIdGroup());
           System.out.println(group);
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            foutmelding.show();
            System.out.println(sqlException.getMessage());
        }
    }

}
