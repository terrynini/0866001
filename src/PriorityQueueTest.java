import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriorityQueueTest {
    static Random ran;

    @BeforeAll
    static void setUp(){
        ran = new Random();
    }

    static Stream<ArrayList<Integer>> caseGen() {
        ArrayList<ArrayList<Integer>> a = new ArrayList();
        for(int i = 0 ; i < 5 ; i++){
            a.add(new ArrayList<Integer>());
            int temp = 1+ran.nextInt(1000);
            for(int j = 0 ; j <  temp ; j++)
                a.get(i).add(ran.nextInt(100));
        }
        return a.stream();
    }

    @ParameterizedTest
    @MethodSource("caseGen")
    void prioityQTest(ArrayList<Integer> elements) {
        PriorityQueue<Integer> Q = new PriorityQueue<>(elements);
        Collections.sort(elements);
        for (int e : elements) {
            assertEquals(e, Q.poll());
        }
    }

    @Test
    void exception_1() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> Q = new PriorityQueue<>(-1);
        });
    }

    @Test
    void exception_2() {
        assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> Q = new PriorityQueue<>();
            Q.add(null);
        });
    }

    @Test
    void exception_3() {
        assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> Q = null;
            Q.toString();
        });
    }

}

