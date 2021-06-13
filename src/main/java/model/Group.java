package model;
/*
* @author Fiona Lampers
* */

public class Group {
    private int idGroup;
    private int idCooridnator;
    private int idCourse;
    private String nameGroup;

    // Constructor
    public Group(int idGroup, String nameGroup, int idCoordinator, int idCourse) {
        this.idGroup = idGroup;
        this.nameGroup = nameGroup;
        this.idCooridnator = idCoordinator;
        this.idCourse = idCourse;
    }

    public Group(String nameGroup, int idCoordinator, int idCourse) {
        this(0, nameGroup,  idCoordinator, idCourse);
    }

    @Override
    public String toString() {
        return String.format("Group: %s", nameGroup);
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public int getIdCooridnator() {
        return idCooridnator;
    }

    public void setIdCooridnator(int idCooridnator) {
        this.idCooridnator = idCooridnator;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
}





