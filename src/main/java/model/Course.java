package model;

public class Course {

    private int idCourse;
    private String nameCourse;
    private int idCoordinator;

    //constructors
    public Course(int idCourse, String nameCourse, int idCoordinator) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.idCoordinator = idCoordinator;
    }

    public Course(String nameCourse, int idCoordinator){
        this(0,nameCourse,idCoordinator);
    }



    //getters/setters
    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public int getIdCoordinator() {
        return idCoordinator;
    }

    public void setIdCoordinator(int idCoordinator) {
        this.idCoordinator = idCoordinator;
    }

    //toString methode

    @Override
    public String toString() {
        return String.format("Course id: %d\nCourse name: %s\nCourse Coordinator: %d\n",idCourse,nameCourse,idCoordinator);
    }
}
