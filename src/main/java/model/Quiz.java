package model;

/**
 * @author Shaun van Ouwerkerk
 */

public class Quiz {
    // Attributen
    private int idQuiz;
    private int idCourse;
    private String nameQuiz;
    private int succesDefinition;

    //Constructor
    public Quiz(String nameQuiz, int succesDefinition, int idQuiz, int idCourse) {
        this.idQuiz = idQuiz;
        this.idCourse = idCourse;
        this.nameQuiz = nameQuiz;
        this.succesDefinition = succesDefinition;
    }

    public Quiz(String nameQuiz, int succesDefinition, int idCourse) {
        this(nameQuiz, succesDefinition,0,idCourse);
    }

    public  Quiz(String nameQuiz, int succesDefinition){
        this(nameQuiz,succesDefinition,0);
    }


    //Getters & Setters
    public int getIdQuiz() { return idQuiz; }

    public int getIdCourse() { return idCourse; }

    public String getNameQuiz() { return nameQuiz; }

    public int getSuccesDefinition() { return succesDefinition; }

    public void setIdQuiz(int idQuiz) { this.idQuiz = idQuiz; }

    // Methode
    @Override
        public String toString () {

            StringBuilder resultString = new StringBuilder("");
            resultString.append("idQuiz: " + idQuiz + ". " + nameQuiz);
            return resultString.toString();

        }
    }


