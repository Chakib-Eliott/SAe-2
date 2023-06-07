package model;

import org.junit.jupiter.api.*;
import utils.Parsing;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test de la classe Map
 * @author Eliott-B
 * @see Map
 */
@TestMethodOrder( MethodOrderer.OrderAnnotation.class)
class MapTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test du constructeur Map
     */
    @Test
    @Order(1)
    void Map() {
        System.out.println("Map() test ...");

        Quest q1 = Parsing.questParsing("1|(4, 3)|()|2|100|explorer pic de Bhanborim");
        Quest q2 = Parsing.questParsing("2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits");
        Quest q3 = Parsing.questParsing("3|(0, 4)|((2,),)|3|200|explorer palais de Ahehona");
        Quest q4 = Parsing.questParsing("4|(3, 2)|((2,),)|6|100|vaincre Loup Géant");
        Quest q0 = Parsing.questParsing("0|(1,1)|((3,4),)|4|350|vaincre Araignée lunaire");

        Scenario scenarioTest = new Scenario();
        scenarioTest.addQuest(q1);
        scenarioTest.addQuest(q2);
        scenarioTest.addQuest(q3);
        scenarioTest.addQuest(q4);
        scenarioTest.addQuest(q0);

        Map mapTest = new Map(scenarioTest);

        // Map à obtenir
        Object[][] map = {
                {null,null,null,null,q3},
                {null,q0,null,null,null},
                {null,null,null,null,null},
                {null,q2,q4,null,null},
                {null,null,null,q1,null}
        };

        assertArrayEquals(map, mapTest.getMap());

        System.out.println("Success!\n");
    }
}