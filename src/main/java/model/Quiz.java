package model;

/**
 * @author Shaun van Ouwerkerk
 */

public class Quiz {
    // TODO: 03/06/2021 Ik heb hieronder de idQuiz meegenomen, dient die los te staan en geen onderdeel te zijn van de constructor?

    private String nameQuiz;
    private int succesDefinition;
    private int idQuiz;
    private int idCourse;

    public Quiz ( String nameQuiz, int succesDefinition, int idQuiz, int idCourse){
        this.idQuiz = idQuiz;
        this.idCourse = idCourse;
        this.nameQuiz = nameQuiz;
        this.succesDefinition = succesDefinition;
    }
    public Quiz (String nameQuiz, int succesDefinition){
        this(nameQuiz,succesDefinition,1,1);
    }

    //Getters & Setters

    public String getNameQuiz() { return nameQuiz; }

    public int getSuccesDefinition() { return succesDefinition; }

    public void setNameQuiz(String nameQuiz) { this.nameQuiz = nameQuiz;
    }

    public void setSuccesDefinition(int succesDefinition) {
        this.succesDefinition = succesDefinition;
    }

    @Override
    public String toString() {

        StringBuilder resultString = new StringBuilder("");
        resultString.append(idQuiz + " ");
        resultString.append(idCourse + " ");
        resultString.append(nameQuiz + " ");
        resultString.append(succesDefinition);
        return resultString.toString();

    }
}

