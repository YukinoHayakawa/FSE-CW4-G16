
public class WorkshopReview {
	private int RScore;
	private String RReview;
	private static String[] ROutputs = new String[]{"*","**","***","****","*****"};
	
	public WorkshopReview() {
		//Fix Info:mID(1) in Table2
		//RScore = 0;(original code)
		RScore = 1;
		RReview = "No Review";
	}
	
	public WorkshopReview(int rScore, String rReview) throws Exception
	{
		//Fix Info:mID(2) in Table2
		if (rScore < 1 || rScore > 5)
		{
			throw new Exception("Invalid input score");
		}
		//Fix Info:mID(3) in Table2
		if (rReview.length() < 9)
		{
			throw new Exception("Review too short");
		}
		RScore = rScore;
		RReview = rReview;
	}
	
	public int getRScore() {
		return RScore;
	}
	
	public void setRScore(int rScore) throws Exception
	{
		//Fix Info:mID(4) in Table2
		if (rScore < 1 || rScore > 5)
		{
			throw new Exception("Invalid input score");
		}
		RScore = rScore;
	}
	
	public String getRReview() {
		return RReview;
	}
	
	public void setRReview(String rReview) throws Exception
	{
		//Fix Info:mID(5) in Table2
		if (rReview.length() < 9)
		{
			throw new Exception("Review too short");
		}
		RReview = rReview;
	}

	@Override
	public String toString() {
		//Fix Info:mID(6) in Table2
		//return "Score = " + ROutputs[RScore] + "\n Review: " + RReview + "\n";(original code)
		return "Score = " + ROutputs[RScore-1] + "\n Review: " + RReview + "\n";
	}
	
	
	
}
