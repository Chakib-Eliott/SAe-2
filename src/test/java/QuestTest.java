import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder( MethodOrderer.OrderAnnotation.class)
class QuestTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void compareTo() {
        System.out.println("compareTo() test ...");

        Integer [] position = {4,3};
        Integer[][] precondition = new Integer[2][2];
        Quest q1 = new Quest(1,position,precondition,1,150,"Test Quest");
        Quest q2 = new Quest(1,position,precondition,1,150,"Test Quest");
        assertEquals(0,q1.compareTo(q2));

        q2 = new Quest(2,position,precondition,1,150,"Test Quest");
        assertEquals(-1,q1.compareTo(q2));

        q1 = new Quest(3,position,precondition,1,150,"Test Quest");
        q2 = new Quest(1,position,precondition,1,150,"Test Quest");
        assertEquals(2,q1.compareTo(q2));

        System.out.println("Success!\n");
    }
}