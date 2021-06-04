package model;

import database.mysql.GenericDAO;

import java.util.ArrayList;

public class Quiz implements GenericDAO {

    private String nameQuiz;
    private double succesDefinition;
    private int idQuiz; // TODO: 02/06/2021 Moet hier nog AI? 

    public Quiz (int idQuiz, Course idCourse, String nameQuiz, double succesDefinition){
        this.idQuiz = idQuiz;
        this.nameQuiz = nameQuiz;
        this.succesDefinition = succesDefinition;
        // TODO: 02/06/2021 Hoe vult Course idCourse zich?
        //
        
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    @Override
    public ArrayList getAll() {
        return null;
    }

    @Override
    public Object getOneById(int id) {
        return null;
    }

    @Override
    public void storeOne(Object type) {

    }
}
