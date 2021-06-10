package model;
/*
* @author Fiona Lampers
* */

public class Group {
    private int idGroup;
    private int idCooridnator;
    private int idCourse;

    // Constructor
    public Group(int idGroup, int idCoordinator, int idCourse) {
        this.idGroup = idGroup;
        this.idCooridnator = idCoordinator;
        this.idCourse = idCourse;
    }

    public Group(int idCoordinator, int idCourse) {
        this(0, idCoordinator, idCourse);
    }

    @Override
    public String toString() {
        return String.format("Group id: %d", idGroup);
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
}





