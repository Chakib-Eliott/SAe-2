import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Class qui implémente une solution gloutonne efficace à un scénario
 * @author chak
 * @see Scenario
 * @see Map
 */
public class GreedyEff extends Solution{
    Map map;
    String solution; // ordre des quêtes effectués
    int duration; // durée des déplacements (1 déplacment = 1) + somme des durées de chaque quêtes
    int nbQuests;

    ArrayList<Integer[][]> travels; // déplacements
    /**
     * Constructeur de la classe Solution
     *
     * @param scenario Scenario
     * @see Scenario
     */
    public GreedyEff(Scenario scenario) {
        super(scenario);
        map = new Map(scenario);
        duration = 0;
        nbQuests = 0;
        travels = new ArrayList<>();
        solution = algorithm();
    }

    /**
     * Affiche la solution de l'algorithme glouton efficace
     * @return String
     */
    public String toString() {
        return solution;
    }

    /**
     * Algorithme glouton efficace
     * @return String : solution
     */
    public String algorithm() {
        ArrayList<Integer> solution = new ArrayList<>();
        Integer[] position = {0, 0};
        int xp = 0;
        Scenario doneQuests = new Scenario(); // quêtes déjà faites

        // parcours de la map et se déplacer vers la quête la plus proche

        // parcours du scénario pour récupérer toutes les distances entre la position et les quêtes
        while(scenario.getQuest().size() > 0) { // tant qu'il reste des quêtes

            Scenario doableScenario = doableScenario(scenario, doneQuests, xp); // quêtes faisables

            int nearestQuest = nearestQuest(doableScenario, position); // quête la plus proche

            Quest qDel = scenario.getQuestbyId(nearestQuest); // quête à supprimer

            doneQuests.addQuest(qDel); // ajoute la quête à la liste des quêtes faites

            this.scenario.removeQuestbyId(nearestQuest); // supprime la quête du scénario

            solution.add(nearestQuest); // ajoute la quête à la solution

            duration += qDel.getDuration(); // ajoute la durée de la quête à la durée totale

            xp += qDel.getXp(); // ajoute l'xp de la quête à l'xp totale

            travels.add(new Integer[][]{position, qDel.getPosition()}); // ajout du déplacement

            position = qDel.getPosition(); // se déplace vers la quête

            if(qDel.getId() == 0) { // quête 0 = quête de fin
                break;
            }

        }
        this.nbQuests = doneQuests.getQuest().size();
        return solution.toString();
    }

    /**
     * Renvoie la quête la plus proche
     * @param distances TreeMap<Integer, Double> : toutes les distances entre la position du joueur et les quêtes
     * @return int : id de la quête la plus proche
     */
    public static int glouton(TreeMap<Integer, Double> distances){
        int min = distances.firstKey();
        for (int i: distances.keySet()) {
            if (distances.get(i) < distances.get(min)) {
                min = i;
            }
        }
        return min;
    }


    /**
     * Renvoie toutes les distances entre la position du joueur et les quêtes
     * @param scenario Scenario
     * @param position Integer[] : position du joueur
     * @return TreeMap<Integer, Double> : toutes les distances entre la position du joueur et les quêtes
     */
    public static TreeMap<Integer, Double> distances(Scenario scenario, Integer[] position) {
        TreeMap<Integer, Double> distances = new TreeMap<>();
        for(Quest q: scenario.getQuest()){
            distances.put(
                    q.getId(), java.lang.Math.sqrt(
                            java.lang.Math.pow(position[0] - q.getPosition()[0], 2)
                                    + java.lang.Math.pow(position[1] - q.getPosition()[1],2)));
        }
        return distances;
    }

    /**
     * Renvoie la quête la plus proche
     * @param scenario Scenario
     * @param position Integer[] : position du joueur
     * @return int : id de la quête la plus proche
     */
    public static int nearestQuest(Scenario scenario, Integer[] position){
        TreeMap<Integer, Double> distances = distances(scenario, position);
        return glouton(distances);
    }

    /**
     * Renvoie le scénario faisable à partir d'un scénario actuel, des quêtes déjà effectuées et de l'expérience
     * @param scenario Scenario de base
     * @param doneQuests Scenario avec les quêtes déjà effectués
     * @param xp XP au moment de la vérification
     * @return Scenario : le scénario des quêtes faisables
     */
    public static Scenario doableScenario(Scenario scenario, Scenario doneQuests, int xp){
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
                if(q.getId() != 0) {
                    doableScenario.addQuest(q); // ajoute la quête à la liste des quêtes faisables
                } else {
                    if(xp > q.getXp()) {
                        doableScenario.addQuest(q); // ajoute la quête à la liste des quêtes faisables
                    }
                }
            }
        }
        return doableScenario;
    }


}
