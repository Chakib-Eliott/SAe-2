package model;
import java.util.ArrayList;

/**
 * Class qui implémente une solution à un scénario
 * @author chak
 * @see Scenario
 */
public class Solution {
    /**
     * Scénario concerné
     */
    Scenario scenario;
    /**
     * Durée qu'a prit la solution
     */
    Integer duration = 0;
    /**
     * Expérience obtenu
     */
    int xp = 0;
    /**
     * Nombre de déplacements
     */
    int travel = 0;
    /**
     * Chemin
     */
    ArrayList<Integer> solution;
    /**
     * Nombre de quêtes faites
     */
    int nbQuests;
    /**
     * Type de la solution
     */
    int type;
    /**
     * Nom de la solution
     */
    String name;



    /**
     * Constructeur de la classe Solution
     * @param scenario Scenario
     * @see Scenario
     */
    public Solution(Scenario scenario){
        this.scenario = scenario;
    }

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
                    if(doneQuests.getQuestById(i) != null) { // si l'objet est dans les quêtes faites
                        precond1 = true; // la première précondition est vérifiée
                    }
                }

            }
            boolean precond2 = false;
            for(Integer i : precond[1]) {
                // l'un des deux objets doit être dans les quêtes faites
                if(i != null) {
                    if (doneQuests.getQuestById(i) != null) { // si l'objet est dans les quêtes faites
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
        duration += Map.distance(new Integer[]{0, 0}, scenario.getQuestById(solution.get(0)).getPosition());
        travel += Map.distance(new Integer[]{0, 0}, scenario.getQuestById(solution.get(0)).getPosition());
        for(Integer i : solution){

            if(i != 0){
                xp += scenario.getQuestById(i).getXp();}

            duration += scenario.getQuestById(i).getDuration();

            if(!i.equals(solution.get(0))) {
                Integer[] actuelle = scenario.getQuestById(i).getPosition();
                Integer[] avant =  scenario.getQuestById(solution.get(solution.indexOf(i)-1)).getPosition();
                duration += Map.distance(actuelle, avant);
                travel += Map.distance(actuelle, avant);
            }
        }
        return new Integer[]{duration, travel, xp};
    }

    /**
     * Renvoie le scénario affecté à la solution
     * @return Scenario
     */
    public Scenario getScenario() {
        return scenario;
    }

    /**
     * Renvoie le type de solution
     * @return int : type
     */
    public int getType() {
        return type;
    }

    /**
     * Renvoie la durée de la solution
     * @return Integer : durée
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Renvoie la distance de la solution
     * @return Integer : distance
     */
    public Integer getTravel() {
        return travel;
    }

    /**
     * Renvoie l'xp de la solution
     * @return Integer : xp
     */
    public Integer getXp() {
        return xp;
    }

    /**
     * Renvoie la liste des quêtes de la solution
     * @return ArrayList<Integer> : liste des quêtes
     */
    public ArrayList<Integer> getSolution() {
        return solution;
    }

    /**
     * Renvoie le nombre de quêtes de la solution
     * @return int : nombre de quêtes
     */
    public int getNbQuests() {
        return nbQuests;
    }

    /**
     * Renvoie le nom de la solution
     * @return String : nom
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de la solution
     * @param name nom
     */
    public void setName(String name) {
        this.name = name;
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
