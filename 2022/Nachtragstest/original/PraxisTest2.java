import java.util.NoSuchElementException;

// This class can be modified and is for testing your solution.
// Modifications will NOT be reviewed or assessed.
//
public class PraxisTest2 {

    public static void main(String[] args) {

        //              mobile1|
        //              +------2---+----+
        //            m2|          |    |
        //        +-----2--+--+    *    *
        //        |      m3|  |    12   5
        //        |        |  *
        //        *        |  2
        //        7        |
        //              +--1--+
        //              |     |
        //              *     *
        //              3     3

//         d: uncomment this block.
        try {
            Star star7 = new Star(7);
            Star star3a = new Star(3);
            Star star3b = new Star(3);
            Star star2 = new Star(2);
            Star star12 = new Star(12);
            Star star5 = new Star(5);

            Stick m3 = new Stick(1, star3a, star3b);
            Stick m2 = new Stick(2, star7, m3, star2);
            Stick m4 = new Stick(2, new Star(2), new Star(2));

            Mobile mobile1, mobile2, mobile3, mobile4;
            mobile1 = new Stick(5, m2, star12, star5);


            mobile2 = new Stick(2, m3, m4);
            mobile3 = new Stick(2, new Stick(1, star3a, star3b), m4);
            mobile4 = new Stick(2, new Stick(1, new Star(3), star3b), m4);

            System.out.println("Test 1:");
            System.out.println(star7.getWeight()); // 7
            System.out.println(m2.getWeight()); // 18

            System.out.println("\nTest 2:");
            System.out.println(mobile1); // 5[2[*7, 1[*3, *3], *2], *12, *5]
            System.out.println(mobile2); // 2[1[*3, *3], 2[*2, *2]]
            System.out.println(mobile3); // 2[1[*3, *3], 2[*2, *2]]

            System.out.println("\nTest 3:");
            System.out.println(mobile1.equals(mobile2)); // false
            System.out.println(mobile2.equals(mobile3)); // true
            System.out.println(mobile2.equals(mobile4)); // false


            System.out.println("\nTest 4:");
            StarIterator iter = mobile1.iterator();
            while(iter.hasNext()) {
                System.out.print(iter.next()); //*7*3*3*2*12*5 (order is irrelevant)
            }
            System.out.println();

            System.out.println("\nTest 5:");
            try {
                System.out.print(iter.next()); // "no star element!"
                System.out.println("This message should not be printed!");

            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\nTest 6:");
            StarCollection sc = mobile1.getStarCollection();
            System.out.println(sc.contains(star2)); // true
            System.out.println(sc.contains(star7)); // true
            System.out.println(sc.contains(new Star(star2.getWeight()))); // false
            System.out.println(mobile1.replace(star12, new Star(12))); // true
            System.out.println(sc.contains(star12)); // false

            System.out.println("\nTest 7:");
            mobile2 = new Stick(2,m2, m4); // overweight! -> Exception

            System.out.println("This message should not be printed!");

        } catch (StickBreaksException e) {
            System.out.println(e.getMessage()); // Stick breaks!

        }
        // DONE: end of block to uncomment.

        /*

        Test 1:
        7
        18

        Test 2:
        5[2[*7, 1[*3, *3], *2], *12, *5]
        2[1[*3, *3], 2[*2, *2]]
        2[1[*3, *3], 2[*2, *2]]

        Test 3:
        false
        true
        false

        Test 4:
        *7*3*3*2*12*5

        Test 5:
        no star element!

        Test 6:
        true
        true
        false
        true
        false

        Test 7:
        Stick breaks!

        */
    }
}
