/**
 * Class qui implémente une solution à un scénario
 * @author chak
 * @see Scenario
 */
public class Solution {
    Scenario scenario;

    /**
     * Constructeur de la classe Solution
     * @param scenario Scenario
     * @see Scenario
     */
    public Solution(Scenario scenario){
        this.scenario = scenario.clone();
    }
}
