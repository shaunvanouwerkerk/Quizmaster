package view;

import com.google.gson.Gson;
import database.couchDB.CouchDBaccessCourse;
import database.couchDB.CourseCouchDAO;
import database.mysql.CourseDAO;
import database.mysql.DBAccess;
import database.mysql.QuizDAO;
import model.Course;
import model.Quiz;

import java.util.ArrayList;

public class CouchDBCourseLauncher {

    private CouchDBaccessCourse couchDBaccessCourse;
    private CourseCouchDAO courseCouchDAO;
    private CourseDAO courseDAO;
    private DBAccess dbAccess;

    public CouchDBCourseLauncher() {
        this.couchDBaccessCourse = new CouchDBaccessCourse();
        this.courseCouchDAO = new CourseCouchDAO(couchDBaccessCourse);
        this.dbAccess = Main.getDBaccess();
    }

    public static void main(String[] args) {
        CouchDBCourseLauncher courseLauncher = new CouchDBCourseLauncher();
        courseLauncher.run();
        //courseLauncher.saveAllCourses();
        courseLauncher.testWithCourses();

    }

    //Maak een connectie met couchDB
    public void run() {
        try {
            couchDBaccessCourse.setupConnection();
            System.out.println("Connectie open");
        } catch (Exception exception) {
            System.out.println("Er is iets mis gegaan");
            exception.printStackTrace();
        }
    }

    //methode om een cursus op te slaan
    public void saveOneCourse (Course course) {courseCouchDAO.saveSingleCourse(course);}

    //methode om alle cursussen uit de MySQL database op de slaan in CouchDB
    public void saveAllCourses () {
        this.courseDAO = new CourseDAO(dbAccess);
        ArrayList<Course> allCoursesInDatabase = courseDAO.getAll();
        for (Course course : allCoursesInDatabase) {
            saveOneCourse(course);
        }
    }

    public void testWithCourses () {
        //Een cursus om te zetten naar een Json String
        Course cursus1 = new Course("Voetbaltrainer", 5);
        Gson gson = new Gson();
        String cursus1Json = gson.toJson(cursus1);
        System.out.println("cursus1 omgezet naar Json String: ");
        System.out.println(cursus1Json);
        System.out.println("================================================================");

        //Van een Json string weer een Course(class) te maken
        Course cursus2 = gson.fromJson(cursus1Json, Course.class);
        System.out.println("Json omgezet naar course object. Toon de ToString van Course ");
        System.out.println(cursus2);
        System.out.println("================================================================");


        //Een cursus op halen uit CouchDb
        Course courseId = courseCouchDAO.getCourseByDocId("3d925f5d225644a0a84d39d9a2e788d2");
        System.out.println("Course uit COUCH DB: \n" + courseId);
        System.out.println("================================================================");
    }

}