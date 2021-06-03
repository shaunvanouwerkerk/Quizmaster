package database.mysql;

import model.Course;

import java.util.ArrayList;

public class CourseDAO extends AbstractDAO implements GenericDAO<Course>{

    public CourseDAO (DBAccess dbAccess) {super(dbAccess);}


    @Override
    public ArrayList<Course> getAll() {

        return null;
    }

    @Override
    public Course getOneById(int id) {
        return null;
    }

    @Override
    public void storeOne(Course type) {

    }
}
