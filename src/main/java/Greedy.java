import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Class qui implémente une solution gloutonne efficace à un scénario
 * @author chak
 * @see Scenario
 * @see Map
 */
public class Greedy extends Solution{

    /**
     * Constructeur de la classe Solution
     *
     * @param scenario Scenario
     * @param type Type (0 efficace, 1 exhaustive)
     * @see Scenario
     */
    public Greedy(Scenario scenario, int type) {
        super(scenario);
        solution = algorithm(scenario, type);
        Integer[] caracteristics = caracteristics(solution,scenario);
        duration = caracteristics[0];
        xp = caracteristics[2];
        travel = caracteristics[1];
        nbQuests = solution.size();
    }


    /**
     * Algorithme glouton efficace
     * @param scenario Scenario
     * @param type Type (0 efficace, 1 exhaustive)
     * @return ArrayList<Integer> : solution
     */
    private ArrayList<Integer> algorithm(Scenario scenario, int type) {
        scenario = scenario.clone();
        ArrayList<Integer> solution = new ArrayList<>();
        Integer[] position = {0, 0};
        int xp = 0;
        Scenario doneQuests = new Scenario(); // quêtes déjà faites

        Quest finale = scenario.getQuestbyId(0); // on garde la quête finale de côté
        // pas possible la garder de côté uniquement dans le cas de la solution
        // exhaustive car elle n'est pas détectée dans le second if (à la fin du scénario)
        if(type == 1){ // solution exhaustive
            scenario.removeQuestbyId(0); // on la retire du scenario pour qu'elle ne soit pas prise en compte
        }
        // parcours de la map et se déplacer vers la quête la plus proche

        // parcours du scénario pour récupérer toutes les distances entre la position et les quêtes
        while(scenario.getQuest().size() > 0) { // tant qu'il reste des quêtes

            Scenario doableScenario = doableScenario(scenario, doneQuests, xp); // quêtes faisables

            if(type != 1){ // si ce n'est pas la solution exhaustive
                if(doableScenario.getQuestbyId(0) != null){
                    // si la quête finale est faisable,
                    // on la fait
                    doneQuests.addQuest(finale);
                    solution.add(0);
                    duration += finale.getDuration();
                    duration += Map.distance(position, finale.getPosition());
                    break;
                }
            }

            int nearestQuest = nearestQuest(doableScenario, position); // quête la plus proche

            Quest qDel = scenario.getQuestbyId(nearestQuest); // quête à supprimer

            doneQuests.addQuest(qDel); // ajoute la quête à la liste des quêtes faites

            scenario.removeQuestbyId(nearestQuest); // supprime la quête du scénario

            solution.add(nearestQuest); // ajoute la quête à la solution

            duration += qDel.getDuration(); // ajoute la durée de la quête à la durée totale

            duration += Map.distance(position, qDel.getPosition()); // ajoute la durée du déplacement


            if(qDel.getId() != 0) { // si ce n'est pas la solution finale
                xp += qDel.getXp(); // ajoute l'xp de la quête à l'xp totale
            }

            position = qDel.getPosition(); // se déplace vers la quête



        }
        if(type == 1){ // solution exhaustive
            doneQuests.addQuest(finale);
            solution.add(0);
            duration += finale.getDuration();
            duration += Map.distance(position, finale.getPosition());


        }
        this.nbQuests = doneQuests.getQuest().size();
        this.xp = xp;
        return solution;
    }

    /**
     * Renvoie la quête la plus proche
     * @param distances TreeMap<Integer, Double> : toutes les distances entre la position du joueur et les quêtes
     * @return int : id de la quête la plus proche
     */
    private static int glouton(TreeMap<Integer, Double> distances){
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
    private static TreeMap<Integer, Double> distances(Scenario scenario, Integer[] position) {
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
    private static int nearestQuest(Scenario scenario, Integer[] position){
        TreeMap<Integer, Double> distances = distances(scenario, position);
        return glouton(distances);
    }
}
