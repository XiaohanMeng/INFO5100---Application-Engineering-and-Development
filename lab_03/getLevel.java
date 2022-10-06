import java.util.Scanner;

public class getLevel {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int ass_num = 8;

		// get the total grades
		System.out.println("Please input your 8 assignments total grades: ");
		
		String[] total_arr = sc.nextLine().split(",");
		
		double[] input_total = new double[ass_num];
			
		for(int i = 0; i < ass_num; i++) {
			input_total[i] = Double.parseDouble(total_arr[i]);
		}
		
		// get the earned grades
		System.out.println("Please input your 8 assignments earned grades: ");
		
		String[] earned_arr = sc.nextLine().split(",");
		
		double[] input_earned = new double[ass_num];
		
		for(int i = 0; i < ass_num; i++) {
			input_earned[i] = Double.parseDouble(earned_arr[i]);
		}

		// get the grades percentage
		System.out.println("Please input your 8 assignments percentage: ");
		
		String[] per_arr = sc.nextLine().split(",");
		
		double[] input_per = new double[ass_num];
		
		for(int i = 0; i < ass_num; i++) {
			input_per[i] = Double.parseDouble(per_arr[i]);
		}
		sc.close();

		// get the final level
		WeightedGrade wg;
		wg = new WeightedGrade(input_total, input_earned, input_per);
		System.out.print("The level is: " + wg.getLevel());
		
	}	
}
			
			
			

		






