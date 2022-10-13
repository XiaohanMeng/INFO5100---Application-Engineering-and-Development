import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // get the number of assignments
        System.out.print("Please enter the number of assignments: ");
        int ass_num = sc.nextInt();
        sc.nextLine();

        boolean valid = false;

        // get the total grades
        String[] total_arr = new String[ass_num];

        // make sure the total grades are double
        while(valid == false){
            System.out.println("Please input your assignments total grades:");

            total_arr = sc.nextLine().split(",");

            try{
                for(int i = 0; i < total_arr.length; i++){
                    double num = Double.parseDouble(total_arr[i]);
                    valid = true;
                }

            }catch (NumberFormatException e){
                valid = false;
                System.out.println("Error, please enter a double value");
            }
        }

        // when the input grades number != assignment number, input again
        while(total_arr.length!= ass_num){
            System.out.println("Please enter the correct number of grades. Try again.");
            total_arr = sc.nextLine().split(",");
        }

        LinkedList<Double> input_total = new LinkedList<Double>();

        for(int i = 0; i < ass_num; i++) { //add to LinkedList
            input_total.add(Double.parseDouble(total_arr[i]));
        }


        // get the earned grades
        valid = false;
        String[] earned_arr = new String[ass_num];

        // make sure the earned grades are double
        while(valid == false){
            System.out.println("Please input your assignments earned grades:");

            earned_arr = sc.nextLine().split(",");

            try{
                for(int i = 0; i < earned_arr.length; i++){
                    double num = Double.parseDouble(earned_arr[i]);
                    valid = true;
                }

            }catch (NumberFormatException e){
                valid = false;
                System.out.println("Error, please enter a double value");
            }
        }

        // when the input grades number != assignment number, input again
        while(earned_arr.length!= ass_num){
            System.out.println("Please enter the correct number of grades. Try again.");
            earned_arr = sc.nextLine().split(",");
        }

        LinkedList<Double> input_earned = new LinkedList<Double>();

        for(int i = 0; i < ass_num; i++) { //add to LinkedList
            input_earned.add(Double.parseDouble(earned_arr[i]));
        }


        // get the grades percentage
        valid = false;
        String[] per_arr = new String[ass_num];

        // make sure the assignments percentages are double
        while(valid == false){
            System.out.println("Please input your assignments percentage:");

            per_arr = sc.nextLine().split(",");

            try{
                for(int i = 0; i < per_arr.length; i++){
                    double num = Double.parseDouble(per_arr[i]);
                    valid = true;
                }

            }catch (NumberFormatException e){
                valid = false;
                System.out.println("Error, please enter a double value");
            }
        }

        // when the input grades percentage number != assignment number, input again
        while(per_arr.length!= ass_num){
            System.out.println("Please enter the correct number of grades percentage. Try again.");
            per_arr = sc.nextLine().split(",");
        }

        LinkedList<Double> input_per = new LinkedList<Double>();

        for(int i = 0; i < ass_num; i++) { //add to LinkedList
            input_per.add(Double.parseDouble(per_arr[i]));
        }

        sc.close();

        // get the final level
        WeightedGrade cg;
        cg = new WeightedGrade(input_total, input_earned, input_per);
        System.out.print("The level is: " + cg.getLevel());
    }

}