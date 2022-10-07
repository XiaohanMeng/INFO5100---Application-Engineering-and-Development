
public class WeightedGrade {
	
	private double PointTotal;
	private double EarnedPoints;
	private String AssignmentPercentage;
	private double TotalWeightedGrade;
	
	//lab03 attributes
	private double[] PointTotal_array;
	private double[] EarnedPoints_array;
	private double[] AssignmentPercentage_array;
	private double[] TotalWeightedGrade_array  = new double[8];
	
	private boolean is_calculate = false;
	private String level;
	
	
	public WeightedGrade() {
	} // constructor
	
	public WeightedGrade(double pointtotal,double earnedpoints,String assignmentpercentage) {
		PointTotal = pointtotal;
		EarnedPoints = earnedpoints;
		AssignmentPercentage = assignmentpercentage;
	} // constructor
	
	
	// lab03 method
	public WeightedGrade(double[] total_array, double[] earned_array, double[] per_array) {
		this.PointTotal_array = total_array;
		this.EarnedPoints_array = earned_array;
		this.AssignmentPercentage_array = per_array;
	} //constructor
	
	
	public double getPointTotal() {
		return PointTotal;
	} // getPointTotal
	
	public double getEarnedPoints() {
		return EarnedPoints;
	} // getEarnedPoints
	
	public String getAssignmentPercentage() {
		return AssignmentPercentage;
	} // getAssignmentPercentage
	
	public double getTotalWeightedGrade() {
		return TotalWeightedGrade;
	} // getTotalWeightedGrade
	
	public void setPointTotal(int point) {
		PointTotal = point;
	} // setPointTotal
	
	public void setEarnedPoints(int Epoint) {
		PointTotal = Epoint;
	} // setEarnedPoints
	
	public void setAssignmentPercentage(String per) {
		AssignmentPercentage = per;
	} // setAssignmentPercentage
	
	public void CalculateGrades() {
		double Percent = Double.parseDouble(AssignmentPercentage.replace('%',' ')); // turn String to double
		TotalWeightedGrade = Percent*EarnedPoints/PointTotal; // calculate the weighted grade
	} //CalculateGrades
	
	
	//lab03 method
	private String calculateLevel(){
		
		this.is_calculate = true;
		
		double grades = 0;
		
		for(int i = 0; i < 8; i++) {
			TotalWeightedGrade_array[i] += (this.EarnedPoints_array[i] * this.AssignmentPercentage_array[i])/this.PointTotal_array[i];	
		}
		
		for(double item:TotalWeightedGrade_array){
			grades += item;
		}
		
		if(grades >= 90.0) {
			this.level = "A";
		}else if(grades >= 80.0) {
			this.level = "B";
		}else if(grades >= 70.0) {
			this.level = "C";
		}else if (grades >= 60.0) {
			this.level = "D";
		}else {
			this.level = "F";
		}
		
		System.out.println("grade is: " + grades);
		return this.level;
	} // calculateLevel
	
	//lab03 method
	public String getLevel() {
		if(this.is_calculate ==false) {
			return calculateLevel();
		}else return this.level;
	} // getLevel


}
