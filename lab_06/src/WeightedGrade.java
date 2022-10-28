public class WeightedGrade {
    private double PointTotal;
    private double EarnedPoints;
    private double AssignmentPercentage;
    private double TotalWeightedGrade;

    public WeightedGrade() {
    }

    public WeightedGrade(double PointTotal,double EarnedPoints,double AssignmentPercentage) {
        this.PointTotal = PointTotal;
        this.EarnedPoints = EarnedPoints;
        this.AssignmentPercentage = AssignmentPercentage;
    }

    public double getPointTotal() {
        return PointTotal;
    }

    public double getEarnedPoints() {
        return EarnedPoints;
    }

    public double getAssignmentPercentage() {
        return AssignmentPercentage;
    }

    public double getTotalWeightedGrade() {
        return TotalWeightedGrade;
    }

    public void setPointTotal(int PointTotal) {
        this.PointTotal = PointTotal;
    }

    public void setEarnedPoints(int EarnedPoints) {
        this.EarnedPoints = EarnedPoints;
    }

    public void setAssignmentPercentage(double AssignmentPercentage) {
        this.AssignmentPercentage = AssignmentPercentage;
    }
    public void CalculateGrades() {
        TotalWeightedGrade = AssignmentPercentage * EarnedPoints / PointTotal;
    }


}
