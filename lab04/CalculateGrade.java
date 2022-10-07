import java.util.LinkedList;

public class CalculateGrade {

    public CalculateGrade() {
    } //constructor

    private LinkedList<Double> PointTotal_list;
    private LinkedList<Double> EarnedPoints_list;

    private LinkedList<Double> AssignmentPercentage_list;

    private boolean is_calculate = false;
    private String level;

    public CalculateGrade(LinkedList<Double> pointTotal_list, LinkedList<Double> earnedPoints_list, LinkedList<Double> assignmentPercentage_list) {
        PointTotal_list = pointTotal_list;
        EarnedPoints_list = earnedPoints_list;
        AssignmentPercentage_list = assignmentPercentage_list;
    }//constructor

    public LinkedList<Double> getPointTotal_list() {
        return PointTotal_list;
    }

    public void setPointTotal_list(LinkedList<Double> pointTotal_list) {
        PointTotal_list = pointTotal_list;
    }

    public LinkedList<Double> getEarnedPoints_list() {
        return EarnedPoints_list;
    }

    public void setEarnedPoints_list(LinkedList<Double> earnedPoints_list) {
        EarnedPoints_list = earnedPoints_list;
    }

    public LinkedList<Double> getAssignmentPercentage_list() {
        return AssignmentPercentage_list;
    }

    public void setAssignmentPercentage_list(LinkedList<Double> assignmentPercentage_list) {
        AssignmentPercentage_list = assignmentPercentage_list;
    }

    public String getLevel() {
        if(this.is_calculate ==false) { //make sure the final grade been calculated
            return calculateLevel();
        }else return this.level;
    } // getLevel

    private String calculateLevel(){

        this.is_calculate = true;

        double grades = 0;

        // calculate the final grades
        for(int index = 0; index < this.PointTotal_list.size(); index++) {
            grades += (this.EarnedPoints_list.get(index) * this.AssignmentPercentage_list.get(index))/this.PointTotal_list.get(index);
//            System.out.println(grades);
        }

        // get the final level
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






}
