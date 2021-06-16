package database.couchDB;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Course;
import model.Quiz;


public class CourseCouchDAO {

    private CouchDBaccessCourse couchDBaccessCourse;
    private Gson gson;

    public CourseCouchDAO(CouchDBaccessCourse couchDBaccessCourse) {
        this.couchDBaccessCourse = couchDBaccessCourse;
        gson = new Gson();
    }

    public String saveSingleCourse(Course course) {
        String jsonstring = gson.toJson(course);
        System.out.println(jsonstring);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonstring).getAsJsonObject();
        String doc_Id = couchDBaccessCourse.saveDocument(jsonObject);
        return doc_Id;
    }

    public Course getCourseByDocId(String doc_id) {
        JsonObject json = couchDBaccessCourse.getClient().find(JsonObject.class, doc_id);
        Course resultaat = gson.fromJson(json, Course.class);
        return resultaat;
    }


}
