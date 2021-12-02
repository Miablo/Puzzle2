
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    List<String> depth = Collections.emptyList();
    //Considering every single measurement isn't as useful as you expected:
    // there's just too much noise in the data.
    //
    //Instead, consider sums of a three-measurement sliding window.
    // Again considering the above example:
    //
    //199  A
    //200  A B
    //208  A B C
    //210    B C D
    //200  E   C D
    //207  E F   D
    //240  E F G
    //269    F G H
    //260      G H
    //263        H
    //Start by comparing the first and second three-measurement windows.
    // The measurements in the first window are marked A (199, 200, 208);
    // their sum is 199 + 200 + 208 = 607. The second window is marked B
    // (200, 208, 210); its sum is 618. The sum of measurements in the
    // second window is larger than the sum of the first, so this first
    // comparison increased.
    //
    //Your goal now is to count the number of times the sum of measurements
    // in this sliding window increases from the previous sum.
    // So, compare A with B, then compare B with C, then C with D, and so on.
    // Stop when there aren't enough measurements left to create a new
    // three-measurement sum.
    //
    //In the above example, the sum of each three-measurement window is as follows:
    //
    //A: 607 (N/A - no previous sum)
    //B: 618 (increased)
    //C: 618 (no change)
    //D: 617 (decreased)
    //E: 647 (increased)
    //F: 716 (increased)
    //G: 769 (increased)
    //H: 792 (increased)
    //In this example, there are 5 sums that are larger than the previous sum.
    //
    //Consider sums of a three-measurement sliding window.
    // How many sums are larger than the previous sum?
    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();

        main.readMeasurement();

        System.out.println("Final Count is " + main.isLarger());

    }

    public void readMeasurement() throws FileNotFoundException {
        try {
            depth = Files.readAllLines(Paths.get("/Users/miablo/Desktop/Puzzle2/src/input.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // store them in arraylist
        // file input
        // read measurements
    }

    // perform analysis on the list
    public int isLarger() {
        int measurements = 0;
        int sum1 = 0, sum2 = 10000;

        Iterator<String> itr = depth.iterator();
        int first, second, last;

        first = Integer.parseInt(itr.next());
        second = Integer.parseInt(itr.next());

        while(itr.hasNext()) {
            sum2 = sum1;
            last = Integer.parseInt(itr.next());
            sum1 = first + second + last;
            System.out.println("First " + first + " Second " +
                    second + " last " + last
                    + " sum1 " + sum1 + " sum2 " + sum2);

            if(sum2 > 0 && sum2 < sum1){
                measurements++;
            } else if (!itr.hasNext()) {
                break;
            }

            first = second;
            second = last;

        }
        return measurements;
    }
}
