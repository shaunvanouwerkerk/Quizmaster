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
    private String [] categorie;

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

    public void setIdCourse(int idCourse) { this.idCourse = idCourse; }

    public void setNameQuiz(String nameQuiz) { this.nameQuiz = nameQuiz; }

    public void setSuccesDefinition(int succesDefinition) { this.succesDefinition = succesDefinition; }

    public void setCategorie(String[] categorie) { this.categorie = categorie; }

    // Methode
    @Override
        public String toString () {

            StringBuilder resultString = new StringBuilder("");
            resultString.append(nameQuiz);
            return resultString.toString();

        }
    }


