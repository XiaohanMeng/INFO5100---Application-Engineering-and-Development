import java.util.LinkedList;
import java.util.Scanner;

public class GetGrade {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // get the number of assignments
        System.out.print("Please enter the number of assignments:");
        int ass_num = sc.nextInt();
        sc.nextLine();


        // get the total grades
        System.out.println("Please input your assignments total grades:");

        String[] total_arr = sc.nextLine().split(","); // separate by comma

        LinkedList<Double> input_total = new LinkedList<Double>();

        for(int i = 0; i < ass_num; i++) {
            input_total.add(Double.parseDouble(total_arr[i]));
        }
//        System.out.println(input_total);


        // get the earned grades
        System.out.println("Please input your assignments earned grades:");

        String[] earned_arr = sc.nextLine().split(",");

        LinkedList<Double> input_earned = new LinkedList<Double>();

        for(int i = 0; i < ass_num; i++) {
            input_earned.add(Double.parseDouble(earned_arr[i]));
        }

//        System.out.println(input_earned);

        // get the grades percentage
        System.out.println("Please input your assignments percentage:");

        String[] per_arr = sc.nextLine().split(",");

        LinkedList<Double> input_per = new LinkedList<Double>();

        for(int i = 0; i < ass_num; i++) {
            input_per.add(Double.parseDouble(per_arr[i]));
        }

//        System.out.println(input_per);

        sc.close();

        // get the final level
        CalculateGrade cg;
        cg = new CalculateGrade(input_total, input_earned, input_per);
        System.out.print("The level is: " + cg.getLevel());
    }
}


