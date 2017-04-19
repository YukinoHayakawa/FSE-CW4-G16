
public class WorkshopPaper {
	private String PTitle;
	private WorkshopReview[] PReviews;
	private static String[] ROutputs = new String[]{"*","**","***","****","*****"};

	public WorkshopPaper() {
		PTitle = "New Paper";
		PReviews = new WorkshopReview[3];
		PReviews[0] = null;
		PReviews[1] = null;
		PReviews[2] = null;
	}
	
	public WorkshopPaper(String pTitle) {
		PTitle = pTitle;
		PReviews = new WorkshopReview[3];
		PReviews[0] = null;
		PReviews[1] = null;
		PReviews[2] = null;
	}

	public String getPTitle() {
		return PTitle;
	}

	public void setPTitle(String pTitle) {
		PTitle = pTitle;
	}
	
	public void addReview(WorkshopReview nReview) {
		if (PReviews[0] == null)
			PReviews[0] = nReview;
		else {
			if (PReviews[1] == null)
				PReviews[1] = nReview;
			else {
				if (PReviews[2] == null)
					PReviews[2] = nReview;
			}
		}
	}
	
	public float getAverageScore(){
		float AvgScore = 0;
		int numReviews = 0;
		if (PReviews[0] != null){
			AvgScore += PReviews[0].getRScore();
			numReviews++;
		}
		if (PReviews[1] != null){
			AvgScore += PReviews[1].getRScore();
			numReviews++;
		}
		if (PReviews[2] != null){
			AvgScore += PReviews[1].getRScore();
			numReviews++;
		}
		AvgScore = AvgScore/numReviews;
		return AvgScore;
	}
	
	public String toString(){
		String myoutput = "";
		myoutput = "Average Score = " + ROutputs[Math.round(getAverageScore())] + "\n\n";
		myoutput += "Review 1:\n" + PReviews[0].toString() + "\n";
		myoutput += "Review 2:\n" + PReviews[1].toString() + "\n";
		myoutput += "Review 3:\n" + PReviews[2].toString() + "\n";
		return myoutput;
	}
	
}
