import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter the non-zero coordinates of x[n] in the form 'x1,y1'. Press E to continue with h[n] coordinates.");
        String input = keyboard.nextLine();

        ArrayList<Integer> xn_coordinates_x = new ArrayList<>();
        ArrayList<Integer> xn_coordinates_y = new ArrayList<>();

        ArrayList<Integer> hn_coordinates_x = new ArrayList<>();
        ArrayList<Integer> hn_coordinates_y = new ArrayList<>();

        while (!input.equalsIgnoreCase("e")) {
            xn_coordinates_x.add(Integer.parseInt(String.valueOf(input.charAt(0))));
            xn_coordinates_y.add(Integer.parseInt(String.valueOf(input.charAt(2))));
            input = keyboard.nextLine();
        }

        System.out.println("Enter the non-zero coordinates of h[n] in the form 'x1,y1'. Press E to continue.");
        input = keyboard.nextLine();

        while (!input.equalsIgnoreCase("e")) {
            hn_coordinates_x.add(Integer.parseInt(String.valueOf(input.charAt(0))));
            hn_coordinates_y.add(Integer.parseInt(String.valueOf(input.charAt(2))));
            input = keyboard.nextLine();
        }

        ArrayList<Integer> reversed_hk_coordinates_x = new ArrayList<>();
        ArrayList<Integer> reversed_hk_coordinates_y = new ArrayList<>();

        ArrayList<Integer> yn_coordinates_x = new ArrayList<>();
        ArrayList<Integer> yn_coordinates_y = new ArrayList<>();

        for (int i = hn_coordinates_x.size() - 1; i >= 0; i--) {
            reversed_hk_coordinates_x.add(-hn_coordinates_x.get(i));
            //No need to reverse y values
            reversed_hk_coordinates_y.add(hn_coordinates_y.get(i));
        }

        //Range of min and max shift
        int n_start = -30;
        int n_end = 30;

        System.out.println("Minimum shift amount and maximum shift amount are set to -30 and 30 respectively. If you want to change them, press E. Use any other button to continue.");

        if(keyboard.nextLine().equalsIgnoreCase("e")){
            System.out.print("Minimum shift amount: ");
            n_start = keyboard.nextInt();
            System.out.print("Maximum shift amount: ");
            n_end = keyboard.nextInt();
        }

        for (int i = n_start; i <= n_end; i++) {
            ArrayList<Integer> reversed_and_shifted_hk_coordinates_x = new ArrayList<>();
            ArrayList<Integer> reversed_and_shifted_hk_coordinates_y = new ArrayList<>();

            for (int j = 0; j < reversed_hk_coordinates_x.size(); j++) {
                reversed_and_shifted_hk_coordinates_x.add(reversed_hk_coordinates_x.get(j) + i);
                //No need to shift y values
                reversed_and_shifted_hk_coordinates_y.add(reversed_hk_coordinates_y.get(j));
            }

            int sum_of_overlaps = 0;

            for (int j = 0; j < reversed_and_shifted_hk_coordinates_x.size(); j++) {
                int a = xn_coordinates_x.indexOf(reversed_and_shifted_hk_coordinates_x.get(j)); //If it is -1, no overlap on x-coordinate for current j value
                if (a != -1) {
                    sum_of_overlaps += xn_coordinates_y.get(a) * reversed_and_shifted_hk_coordinates_y.get(j);
                }
            }
            if (sum_of_overlaps != 0) {
                yn_coordinates_x.add(i);
                yn_coordinates_y.add(sum_of_overlaps);
            }
        }

        System.out.println("y[n] has non-zero values in these coordinates: ");
        for (int i = 0; i < yn_coordinates_y.size(); i++) {
            System.out.println(yn_coordinates_x.get(i) + ", " + yn_coordinates_y.get(i));
        }
    }
    
}
