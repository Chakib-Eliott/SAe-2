package model;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder( MethodOrderer.OrderAnnotation.class)
class ParsingTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void questParsing() {
        System.out.println("questParsing() test ...");
        // Test de toutes la valeurs du scénario obtenu avec le parsing
        String line = "1|(4, 3)|()|2|100|explorer pic de Bhanborim";
        Quest testQuest = Parsing.questParsing(line);
        Integer [] position = {4,3};
        Integer[][] precondition = new Integer[2][2];
        assertEquals(testQuest.getId(), 1);
        assertArrayEquals(testQuest.getPosition(), position);
        assertArrayEquals(testQuest.getPrecondition(), precondition);
        assertEquals(testQuest.getDuration(), 2);
        assertEquals(testQuest.getXp(), 100);
        assertEquals(testQuest.getTitle(), "explorer pic de Bhanborim");

        line = "2|(3, 1)|((1,),)|1|150|dialoguer avec Kaela la chaman des esprits";
        testQuest = Parsing.questParsing(line);
        position = new Integer[]{3, 1};
        precondition = new Integer[][]{{1, null}, {null, null}};
        assertEquals(testQuest.getId(), 2);
        assertArrayEquals(testQuest.getPosition(), position);
        assertArrayEquals(testQuest.getPrecondition(), precondition);
        assertEquals(testQuest.getDuration(), 1);
        assertEquals(testQuest.getXp(), 150);
        assertEquals(testQuest.getTitle(), "dialoguer avec Kaela la chaman des esprits");

        line = "0|(1,1)|((3,4),)|4|350|vaincre Araignée lunaire";
        testQuest = Parsing.questParsing(line);
        position = new Integer[]{1, 1};
        precondition = new Integer[][]{{3,4}, {null, null}};
        assertEquals(testQuest.getId(), 0);
        assertArrayEquals(testQuest.getPosition(), position);
        assertArrayEquals(testQuest.getPrecondition(), precondition);
        assertEquals(testQuest.getDuration(), 4);
        assertEquals(testQuest.getXp(), 350);
        assertEquals(testQuest.getTitle(), "vaincre Araignée lunaire");

        line = "3|(1, 0)|((4,), (1, 2))|7|100|dialoguer avec Morrigan la déesse de la mort";
        testQuest = Parsing.questParsing(line);
        position =  new Integer[]{1,0};
        precondition = new Integer[][]{{4,null},{1,2}};
        assertEquals(testQuest.getId(), 3);
        assertArrayEquals(testQuest.getPosition(), position);
        assertArrayEquals(testQuest.getPrecondition(), precondition);
        assertEquals(testQuest.getDuration(), 7);
        assertEquals(testQuest.getXp(), 100);
        assertEquals(testQuest.getTitle(), "dialoguer avec Morrigan la déesse de la mort");

        line = "5|(4, 3)|((1, 4), (2,))|1|150|explorer jardin de Syhe Lenora";
        testQuest = Parsing.questParsing(line);
        position =  new Integer[]{4,3};
        precondition = new Integer[][]{{1,4},{2,null}};
        assertEquals(testQuest.getId(), 5);
        assertArrayEquals(testQuest.getPosition(), position);
        assertArrayEquals(testQuest.getPrecondition(), precondition);
        assertEquals(testQuest.getDuration(), 1);
        assertEquals(testQuest.getXp(), 150);
        assertEquals(testQuest.getTitle(), "explorer jardin de Syhe Lenora");

        line = "3|(0, 5)|((2,), (7,))|7|150|explorer forêt de Them Ulihm";
        testQuest = Parsing.questParsing(line);
        position =  new Integer[]{0,5};
        precondition = new Integer[][]{{2,null},{7,null}};
        assertEquals(testQuest.getId(), 3);
        assertArrayEquals(testQuest.getPosition(), position);
        assertArrayEquals(testQuest.getPrecondition(), precondition);
        assertEquals(testQuest.getDuration(), 7);
        assertEquals(testQuest.getXp(), 150);
        assertEquals(testQuest.getTitle(), "explorer forêt de Them Ulihm");

        line = "8|(2, 0)|((3, 7), (5, 1))|5|150|explorer steppe de Dhamedur";
        testQuest = Parsing.questParsing(line);
        position =  new Integer[]{2,0};
        precondition = new Integer[][]{{3,7},{5,1}};
        assertEquals(testQuest.getId(), 8);
        assertArrayEquals(testQuest.getPosition(), position);
        assertArrayEquals(testQuest.getPrecondition(), precondition);
        assertEquals(testQuest.getDuration(), 5);
        assertEquals(testQuest.getXp(), 150);
        assertEquals(testQuest.getTitle(), "explorer steppe de Dhamedur");

        System.out.println("Success!\n");
    }

    @Test
    @Order(2)
    void parsing() throws FileNotFoundException {
        System.out.println("parsing() test ...");
        File testFile = new File("data"+File.separator+"scenario_0.txt");
        Scenario testScenario = Parsing.parsing(testFile);
        int size = testScenario.getQuest().size();
        assertEquals(size, 5);
        System.out.println("Success!\n");
    }
}