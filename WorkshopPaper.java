
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
	
	public WorkshopPaper(String pTitle) throws Exception 
	{
		//Fix Info:mID(7) in Table4
		if (pTitle.equals(""))
		{
			throw new Exception("Title is empty!");
		}
		PTitle = pTitle;
		PReviews = new WorkshopReview[3];
		PReviews[0] = null;
		PReviews[1] = null;
		PReviews[2] = null;
	}

	public String getPTitle() {
		return PTitle;
	}

	public void setPTitle(String pTitle) throws Exception
	{
		//Fix Info:mID(8) in Table4
		if (pTitle.equals(""))
		{
			throw new Exception("Title is empty!");
		}
		PTitle = pTitle;
	}
	
	public void addReview(WorkshopReview nReview) throws Exception
	{
		if (PReviews[0] == null)
			PReviews[0] = nReview;
		else {
			if (PReviews[1] == null)
				PReviews[1] = nReview;
			else {
				if (PReviews[2] == null)
					PReviews[2] = nReview;
				//Fix Info:mID(9) in Table4
				else {
					throw new Exception("3 reviews already!");
				}
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
			////Fix Info:mID(11) in Table4
			//AvgScore += PReviews[1].getRScore();(original code)
			AvgScore += PReviews[2].getRScore();
			numReviews++;
		}
		//Fix Info:mID(10) in Table4
		if (numReviews == 0)
		{
			return AvgScore;
		}
		AvgScore = AvgScore/numReviews;
		return AvgScore;
	}
	
	public String toString(){
		//Fix Info:mID(12) in Table4
		if (PReviews[0] == null) {
			return "This paper has not been reviewed";
		}
		String myoutput = "";
		//Fix Info:mID(14) in Table4
		//myoutput = "Average Score = " + ROutputs[Math.round(getAverageScore())] + "\n\n";(original code)
		myoutput = "Average Score = " + ROutputs[Math.round(getAverageScore()) - 1] + "\n\n";
		myoutput += "Review 1:\n" + PReviews[0].toString() + "\n";
		//Fix Info:mID(13) in Table4
		if (PReviews[1] != null) {
			myoutput += "Review 2:\n" + PReviews[1].toString() + "\n";
		}
		//Fix Info:mID(13) in Table4
		if (PReviews[2] != null) {
			myoutput += "Review 3:\n" + PReviews[2].toString() + "\n";
		}
		return myoutput;
	}
	
	/* This method is especially used for JUnit Test */
	/* It helps Tester to check whether the variables in the private member PReviews were set properlt */
	/* Written by Hanbin Qi & Chenxi Han, 4.25 20:02 */
	public WorkshopReview[] getPReviews() {
		return PReviews;
	}
	
}
