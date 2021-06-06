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

    @Override
    public String toString() {
        return String.format("Course %d has a coordinator %d and part-taking group %d",
                idCourse, idCooridnator, idGroup );
    }

    public int getIdGroup() {
        return idGroup;
    }

    public int getIdCooridnator() {
        return idCooridnator;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}





