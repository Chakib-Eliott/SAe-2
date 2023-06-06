import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.TreeMap;

/**
 * Classe des critères
 * @author Eliott-B
 * @see Solution
 * @see SolutionConstants
 * @see Greedy
 */
public class Criterion implements SolutionConstants {
    /**
     * Map des solutions
     * (Solution, (Identifiant du type, nombre de fois))
     */
    private TreeMap <String, TreeMap <Integer, Integer>> solutions;
    /**
     * Scénario des solutions
     */
    private final Scenario scenario;

    /**
     * Constructeur de la classe.
     * Instancie le TreeMap
     */
    public Criterion(Scenario scenario){
        solutions = new TreeMap<>();
        this.scenario = scenario;
    }

    /**
     * Ajoute un critère au TreeMap.
     * (solution, meilleur ou pire, nombre de fois)
     * @param solution String
     * @param type boolean
     * @param number int
     * @see ExceptionCriterion
     */
    public void addCriterion(String solution, int type, int number) throws ExceptionCriterion {
        // La solution n'existe pas -> ERROR
        if(!Arrays.asList(SOLUTIONS_NAME).contains(solution)){
            throw new ExceptionCriterion(0);
        }
        // Vérifie si la solution n'existe pas déjà
        if(solutions.get(solution) == null){
            TreeMap <Integer,Integer> map = new TreeMap<>();
            map.put(type, number);
            solutions.put(solution, map);
        }else{
            TreeMap<Integer, Integer> object = solutions.get(solution);
            // Vérifie si le type de cette solution n'a pas déjà été défini
            if(object.get(type) == null){
                object.put(type, number);
            }else{
                object.replace(type, object.get(type), number);
            }
        }
    }

    /**
     * Lance les solutions demandés avec les critères.
     * Le retour sera sous la forme :
     * {Nom scénario + type, {durée: ??, nombre quêtes: ??, déplacements: ??, xp: ??}}
     * @return TreeMap <String, TreeMap <String, OptionalDouble>>
     * @see ExceptionCriterion
     * @see Greedy
     */
    public TreeMap<String, TreeMap <String, OptionalDouble>> launch() throws ExceptionCriterion {
        TreeMap<String, TreeMap<String, OptionalDouble>> results = new TreeMap<>();
        // Parcours les solutions
        for (String solution : solutions.keySet()) {
            // Parcours les types
            for (int type : solutions.get(solution).keySet()) {
                // Récupère le nombre
                int number = solutions.get(solution).get(type);
                // Déclaration et initialisation des listes des données
                ArrayList <Integer> duration = new ArrayList<>();
                ArrayList <Integer> quest = new ArrayList<>();
                ArrayList <Integer> xp = new ArrayList<>();
                ArrayList <Integer> travel = new ArrayList<>();
                // Liste temporaire pour l'ajouter ensuite au résultat
                TreeMap<String, OptionalDouble> tmp = new TreeMap<>();
                switch (solution.toUpperCase()) {
                    case "GLOUTONNE" -> {
                        if (type != 0 && type != 1) {
                            // Le type n'existe pas => ERROR
                            throw new ExceptionCriterion(1);
                        }
                        // On prend la moyenne des résultats du nombre d'exécution qu'on voulait
                        for (int i = 0; i < number; i++) {
                            Greedy greedy = new Greedy(scenario, type);
                            duration.add(greedy.duration);
                            quest.add(greedy.nbQuests);
                            xp.add(greedy.xp);
                            travel.add(greedy.travel);
                        }
                        tmp.put("durée", duration.stream().mapToDouble(a -> a).average());
                        tmp.put("nombre quêtes", quest.stream().mapToDouble(a -> a).average());
                        tmp.put("xp", xp.stream().mapToDouble(a -> a).average());
                        tmp.put("déplacements", travel.stream().mapToDouble(a -> a).average());
                        results.put(scenario.getFile().getName().replace(".txt", "") + "-Gloutonne-" + type, tmp);
                    }
                    case "SPEED RUN" -> {
                        if (type != 0 && type != 1 && type != 2) {
                            // Le type n'existe pas => ERROR
                            throw new ExceptionCriterion(1);
                        }
                        // On prend la moyenne des résultats du nombre d'exécution qu'on voulait
                        for (int i = 0; i < number; i++) {
                            Speedrun speedrun = new Speedrun(scenario, type);
                            duration.add(speedrun.duration);
                            quest.add(speedrun.nbQuests);
                            xp.add(speedrun.xp);
                            travel.add(speedrun.travel);
                        }
                        tmp.put("durée", duration.stream().mapToDouble(a -> a).average());
                        tmp.put("nombre quêtes", quest.stream().mapToDouble(a -> a).average());
                        tmp.put("xp", xp.stream().mapToDouble(a -> a).average());
                        tmp.put("déplacements", travel.stream().mapToDouble(a -> a).average());
                        results.put(scenario.getFile().getName().replace(".txt", "") + "-SpeedRun-" + type, tmp);
                    }
                    default -> {
                        // La solution n'existe pas -> ERROR
                        throw new ExceptionCriterion(0);
                    }
                }
            }
        }
        return results;
    }

    /**
     * Affiche l'objet dans le terminal
     * @return String
     */
    public String toString(){
        return solutions.toString();
    }
}
