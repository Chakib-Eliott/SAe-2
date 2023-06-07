package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import utils.Parsing;
import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de test de la classe Solution
 * @author Chak
 * @see Solution
 */
public class SolutionTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test du constructeur Solution
     * @throws FileNotFoundException erreur si fichier n'existe pas
     */
    @Test
    @Order(1)
    void Solution() throws FileNotFoundException {
        System.out.println("Solution() test ...");

        int[][][][] toCheck = {
                { // scenario 0
                        { // efficace
                                {27, 30},
                                {350, 450},
                                {14, 20},
                                {4, 4}
                        },
                        { // exhaustive
                                {36, 40},
                                {550, 550},
                                {20, 24},
                                {5, 5}

                        }
                },
                { // scenario 1
                        {// efficace
                                {34, 40},
                                {450, 500},
                                {17, 23},
                                {5, 6}
                        },
                        { // exhaustive
                                {34, 40},
                                {500, 500},
                                {17, 23},
                                {6, 6}

                        }
                },
                { // scenario 2
                        {// efficace
                                {80, 106},
                                {1000, 1050},
                                {35, 57},
                                {9, 9}
                        },
                        { // exhaustive
                                {91, 117},
                                {1200, 1200},
                                {39, 65},
                                {10, 10}

                        }
                },
                { // scenario 3
                        {// efficace
                                {53, 72},
                                {650, 950},
                                {26, 36},
                                {6, 8}
                        },
                        { // exhaustive
                                {64, 74},
                                {950, 950},
                                {28, 38},
                                {8, 8}

                        }
                },
                { // scenario 4
                        {// efficace
                                {95, 167},
                                {900, 1100},
                                {49, 107},
                                {7, 10}
                        },
                        { // exhaustive
                                {115, 171},
                                {1100, 1100},
                                {55, 111},
                                {10, 10}

                        }
                }

        };

        Scenario scenario;
        for(int s = 0; s <= 4; s++) { // pour chaque scenario
            scenario = Parsing.parsing(new File("data/scenario_" + s + ".txt"));
            for(int type = 0; type < 2; type++) { // pour chaque type de solution
                Solution speedrun = new Speedrun(scenario, type);
                Solution greedy = new Greedy(scenario, type);
                for (Solution solution : new Solution[]{speedrun, greedy})
                    for (int i = 0; i < 4; i++) {
                        int[] test = new int[]{solution.getDuration(), solution.getXp(), solution.getTravel(), solution.getNbQuests()};
                        System.out.println("testing : " + toCheck[s][type][i][0] + " <= " + test[i] + " <= " + toCheck[s][type][i][1]);
                        assertTrue(toCheck[s][type][i][0] <= test[i] && test[i] <= toCheck[s][type][i][1]);
                    }
            }
        }

        System.out.println("Solution() test passed !");
    }
}
