package model;
import java.util.ArrayList;

/**
 * Class qui implémente une solution à un scénario
 * @author chak
 * @see Scenario
 */
public class Solution {
    Scenario scenario;

    Integer duration = 0;
    int xp = 0;
    int travel = 0;
    ArrayList<Integer> solution;
    int nbQuests;



    /**
     * Constructeur de la classe Solution
     * @param scenario Scenario
     * @see Scenario
     */
    public Solution(Scenario scenario){
        this.scenario = scenario;}

    /**
     * Renvoie le scénario faisable à partir d'un scénario actuel, des quêtes déjà effectuées et de l'expérience
     * @param scenario Scenario de base
     * @param doneQuests Scenario avec les quêtes déjà effectuées
     * @param xp XP au moment de la vérification
     * @return Scenario : le scénario des quêtes faisables
     */
    static Scenario doableScenario(Scenario scenario, Scenario doneQuests, int xp){
        Scenario doableScenario = new Scenario();
        for(Quest q: scenario.getQuest()) {
            Integer[][] precond = q.getPrecondition();
            boolean precond1 = false;
            // préconditions : grand tuple = ET ; petit tuple = OU
            for(Integer i : precond[0]) {
                // l'un des deux objets doit être dans les quêtes faites
                if(i != null) {
                    if(doneQuests.getQuestbyId(i) != null) { // si l'objet est dans les quêtes faites
                        precond1 = true; // la première précondition est vérifiée
                    }
                }

            }
            boolean precond2 = false;
            for(Integer i : precond[1]) {
                // l'un des deux objets doit être dans les quêtes faites
                if(i != null) {
                    if (doneQuests.getQuestbyId(i) != null) { // si l'objet est dans les quêtes faites
                        precond2 = true; // la deuxième précondition est vérifiée
                    }
                }

            }
            if (precond[0][0] == null && precond[0][1] == null) {
                precond1 = true;
            }
            if (precond[1][0] == null && precond[1][1] == null) {
                precond2 = true;
            }

            if(precond1 && precond2) { // si les deux préconditions sont vérifiées
                if(!doneQuests.getQuest().contains(q)) { // on ne peut pas faire une quête deux fois

                    if (q.getId() != 0) {
                        doableScenario.addQuest(q); // ajoute la quête à la liste des quêtes faisables
                    } else {
                        if (xp >= q.getXp()) {
                            doableScenario.addQuest(q); // ajoute la quête à la liste des quêtes faisables
                        }
                    }
                }
            }
        }
        return doableScenario;
    }

    /**
     * Renvoie les caractéristiques d'une solution
     * @param solution liste des quêtes
     * @param scenario scénario
     * @return Integer[] : durée, distance, xp
     */
    static Integer[] caracteristics(ArrayList<Integer> solution, Scenario scenario){
        int duration = 0;
        int travel = 0;
        int xp = 0;
        duration += Map.distance(new Integer[]{0, 0}, scenario.getQuestbyId(solution.get(0)).getPosition());
        travel += Map.distance(new Integer[]{0, 0}, scenario.getQuestbyId(solution.get(0)).getPosition());
        for(Integer i : solution){

            if(i != 0){
                xp += scenario.getQuestbyId(i).getXp();}

            duration += scenario.getQuestbyId(i).getDuration();

            if(!i.equals(solution.get(0))) {
                Integer[] actuelle = scenario.getQuestbyId(i).getPosition();
                Integer[] avant =  scenario.getQuestbyId(solution.get(solution.indexOf(i)-1)).getPosition();
                duration += Map.distance(actuelle, avant);
                travel += Map.distance(actuelle, avant);
            }
        }
        return new Integer[]{duration, travel, xp};
    }

    /**
     * Renvoie la solution et ses caractéristiques sous la forme suivante :
     * "Solution : [liste des quêtes]
     *  Durée : [durée]
     *  Distance : [distance]
     *  Xp : [xp]
     *  Nombre de quêtes : [nombre de quêtes]"
     * @return String : solution et ses caractéristiques
     */
    public String toString() {
        return "Solution : " + solution + "\nDurée : " + duration + "\nDistance : " + travel + "\nXp : " + xp + "\nNombre de quêtes : " + nbQuests;
    }
}
