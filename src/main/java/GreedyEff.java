import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Class qui implémente une solution gloutonne efficace à un scénario
 * @author chak
 * @see Scenario
 * @see Map
 */
public class GreedyEff extends Solution{
    Map map;
    String solution;
    /**
     * Constructeur de la classe Solution
     *
     * @param scenario Scenario
     * @see Scenario
     */
    public GreedyEff(Scenario scenario) {
        super(scenario);
        map = new Map(scenario);
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
     * @return String
     */
    public String algorithm() {
        ArrayList<Integer> solution = new ArrayList<>();
        int[] position = {0, 0};
        int xp = 0;
        int duree = 0; // duree des déplacements (1 déplacment = 1) + somme des durées de chaque quêtes

        // parcours de la map et se déplacer vers la quête la plus proche

        // parcours du scénario pour récupérer toutes les distances entre la position et les quêtes
        while(scenario.getQuests().size() > 0) {
            TreeMap<Integer, Double> distances = distances(scenario, position);
            // A FINIR

        }

        return "";
    }

    public static TreeMap<Integer, Double> distances(Scenario scenario, int[] position) {
        TreeMap<Integer, Double> distances = new TreeMap<>();
        for(Quest q: scenario.getQuests()){
            distances.put(q.getId(), java.lang.Math.sqrt(java.lang.Math.pow(position[0] - q.getPosition()[0], 2) + java.lang.Math.pow(position[1] - q.getPosition()[1],2)));
        }
        return distances;
    }


}
