import java.util.*;

public class Speedrun extends Solution {


    /**
     * Solution optimisée pour le speedrun (optimale en termes de durée)
     * @param scenario Scenario
     * @param type Type de solution
     *             (0 : speedrun durée minimale,
     *             1 : speedrun 100% ,
     *             2 : speedrun déplacements minimaux)
     */
    public Speedrun(Scenario scenario, int type) {
        super(scenario);
        java.util.Map<ArrayList<Integer>, int[]> solution =
                bestSolution(solutions(scenario, 0, new ArrayList<>(), new ArrayList<>()), scenario, type);
        this.solution = solution.keySet().iterator().next();
        this.duration = solution.get(solution.keySet().iterator().next())[0];
        this.xp = solution.get(solution.keySet().iterator().next())[1];
        this.travel = solution.get(solution.keySet().iterator().next())[2];
        this.nbQuests = this.solution.size();
    }


    /**
     * Renvoie la liste des solutions possibles pour un scénario
     * @param scenario scénario de base
     * @param pendingXp xp en cours
     * @param pendingSolution  solution en cours
     * @param solutions solutions
     * @return ArrayList<ArrayList<Integer>> : liste des solutions
     */
    private static ArrayList<ArrayList<Integer>> solutions(Scenario scenario, int pendingXp, ArrayList<Integer> pendingSolution, ArrayList<ArrayList<Integer>> solutions){
        Scenario scenarioEnCours = new Scenario();
        for(Integer i : pendingSolution)
            scenarioEnCours.addQuest(scenario.getQuestbyId(i));
        for(Quest q : doableScenario(scenario, scenarioEnCours, pendingXp).getQuest()){
            ArrayList<Integer> newSolutionEnCours = (ArrayList<Integer>) pendingSolution.clone();
            newSolutionEnCours.add(q.getId());
            solutions(scenario,  pendingXp + q.getXp(),newSolutionEnCours, solutions);
        }
        if(!pendingSolution.isEmpty()){
            if(pendingSolution.get(pendingSolution.size() - 1) == 0) {
                solutions.add(pendingSolution); // une fois que c'est fini, on ajoute la solution
            }
        }
        return solutions;
    }

    /**
     * Renvoie la meilleure solution pour un scénario
     * @param solutions Liste des solutions possibles
     * @param scenario scénario
     * @return Map<ArrayList<Integer>,int[]> : meilleure solution (liste des quêtes, durée, xp, distance)
     */
    private static java.util.Map<ArrayList<Integer>,int[]> bestSolution(ArrayList<ArrayList<Integer>> solutions, Scenario scenario, int type){
        ArrayList<Integer> bestSolution = new ArrayList<>();
        int bestDuration = 0;
        int bestXp = 0;
        int bestTravel = 0;
        if(type == 1){ // 100%
            for(int i = 0; i < solutions.size() ; i++ ){
                if(solutions.get(i).size() != scenario.getQuest().size()){
                    solutions.remove(i);
                    i--;
                }
            }

        }
        for(ArrayList<Integer> solution : solutions){
            int duration = 0;
            int xp = 0;
            int travel = 0;
            Integer[] caracteristics = caracteristics(solution, scenario);
            duration += caracteristics[0];
            travel += caracteristics[1];
            xp += caracteristics[2];
            if(type == 0){ // durée minimale
                if(duration < bestDuration || bestDuration == 0){
                    bestXp = xp;
                    bestTravel = travel;
                    bestDuration = duration;
                    bestSolution = solution;
                }
            } else if (type == 2) { // déplacements minimaux
                if(travel < bestTravel || bestTravel == 0){
                    bestXp = xp;
                    bestTravel = travel;
                    bestDuration = duration;
                    bestSolution = solution;
                }
            }

        }
        java.util.Map<ArrayList<Integer>,int[]> map = new HashMap<>();
        map.put(bestSolution, new int[]{bestDuration, bestXp, bestTravel});
        return map;
    }



}



