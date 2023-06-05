import java.util.*;

public class Speedrun extends Solution {

    Map map;
    String solution; // ordre des quêtes effectuées
    int duration; // durée des déplacements (1 déplacment = 1) + somme des durées de chaque quête
    int nbQuests;
    int xp;
    ArrayList<String> travels; // déplacements


    /**
     * Solution optimisée pour le speedrun (optimale en termes de durée)
     * @param scenario Scenario
     */
    public Speedrun(Scenario scenario) {
        super(scenario);
        map = new Map(scenario);
        duration = 0;
        nbQuests = 0;
        travels = new ArrayList<>();
//        solution = algorithm();
    }



    /**
     * Renvoie le scénario faisable à partir d'un scénario actuel, des quêtes déjà effectuées et de l'expérience
     * @param scenario Scenario de base
     * @param doneQuests Scenario avec les quêtes déjà effectuées
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


    public static ArrayList<ArrayList<Integer>> solutions(Scenario baseScenario, int xpEnCours, ArrayList<Integer> solutionEnCours, ArrayList<ArrayList<Integer>> solutions){
        Scenario scenarioEnCours = new Scenario();
        for(Integer i : solutionEnCours)
            scenarioEnCours.addQuest(baseScenario.getQuestbyId(i));
        for(Quest q : doableScenario(baseScenario, scenarioEnCours, xpEnCours).getQuest()){
            ArrayList<Integer> newSolutionEnCours = (ArrayList<Integer>) solutionEnCours.clone();
            newSolutionEnCours.add(q.getId());
            solutions(baseScenario,  xpEnCours + q.getXp(),newSolutionEnCours, solutions);
        }
        if(!solutionEnCours.isEmpty()){
            if(solutionEnCours.get(solutionEnCours.size() - 1) == 0) {
                solutions.add(solutionEnCours); // une fois que c'est fini, on ajoute la solution
            }
        }


        return solutions;
    }

    public static java.util.Map<ArrayList<Integer>,int[]> bestSolution(ArrayList<ArrayList<Integer>> solutions, Scenario scenario){
        ArrayList<Integer> bestSolution = new ArrayList<>();
        int bestDuration = 0;
        int bestXp = 0;
        int bestTravel = 0;
        for(ArrayList<Integer> solution : solutions){
            int duration = 0;
            int xp = 0;
            int travel = 0;
            for(Integer i : solution){
                if(i != 0){
                xp += scenario.getQuestbyId(i).getXp();}
//                duration += scenario.getQuestbyId(i).getDuration();
//                if (scenario.getQuestbyId(i-1) != null)
//                {
//                    duration += Map.distance(new Integer[]{0, 0}, scenario.getQuestbyId(i).getPosition());
//                }
//                if(scenario.getQuestbyId(i+1) != null)
//                {
//                    duration += Map.distance(scenario.getQuestbyId(i).getPosition(), scenario.getQuestbyId(i+1).getPosition());
                }
//            }
            duration += duration(solution, scenario)[0];
            travel += duration(solution, scenario)[1];
            if(duration < bestDuration || bestDuration == 0){
                bestXp = xp;
                bestTravel = travel;
                bestDuration = duration;
                bestSolution = solution;
            }
        }
        java.util.Map<ArrayList<Integer>,int[]> map = new HashMap<>();
        map.put(bestSolution, new int[]{bestDuration, bestXp, bestTravel});
        return map;
    }

    public static Integer[] duration(ArrayList<Integer> solution, Scenario scenario){
        int duration = 0;
        int travel = 0;
        duration += Map.distance(new Integer[]{0, 0}, scenario.getQuestbyId(solution.get(0)).getPosition());
        travel += Map.distance(new Integer[]{0, 0}, scenario.getQuestbyId(solution.get(0)).getPosition());
        for(Integer i : solution){
            duration += scenario.getQuestbyId(i).getDuration();

            if(!i.equals(solution.get(0))) {
                Integer[] actuelle = scenario.getQuestbyId(i).getPosition();
                Integer[] avant =  scenario.getQuestbyId(solution.get(solution.indexOf(i)-1)).getPosition();
                duration += Map.distance(actuelle, avant);
                travel += Map.distance(actuelle, avant);
            }
        }
        return new Integer[]{duration, travel};
    }

}



