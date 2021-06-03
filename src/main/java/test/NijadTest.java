package test;

import model.Question;

public class NijadTest {

    public static void main(String[] args) {

        // Vraag met antwoorden in een willekeurige orde uitprinten
        Question testVraag = new Question(1, "Welk project team is het beste uit de lijst?",
                "Quaranteam", "team 2","team 3", "team 4");
        System.out.println(testVraag);
    }

}
