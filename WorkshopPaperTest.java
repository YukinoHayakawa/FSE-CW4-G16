import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

public class WorkshopPaperTest {

	WorkshopPaper paper1 = null;
	double epsilon = 0.00000001;
	static WorkshopPaper paper2 = null;
	static WorkshopPaper empty_paper = null;
	static WorkshopPaper paper_one_review = null;
	static WorkshopPaper paper_two_reviews = null;
	static WorkshopPaper paper_three_reviews = null;
	static WorkshopReview review01;
	static WorkshopReview review02;
	static WorkshopReview review03;
	
	@Test
	public void test11() {
		paper1 = new WorkshopPaper();
		assertEquals(paper1.getPTitle(), "New Paper");
		assertEquals(paper1.getPReviews().length, 3);
		assertEquals(paper1.getPReviews()[0], null);
		assertEquals(paper1.getPReviews()[1], null);
		assertEquals(paper1.getPReviews()[2], null);
	}
	
	@Test
	public void test12() {
		try {
			paper1 = new WorkshopPaper("");
		}
		catch (Exception e) {
			if (e.getClass() == Exception.class) {
				return; //it passed
			}
		}
		fail();
	}
	
	@Test
	public void test13() {
		try {
			paper1 = new WorkshopPaper("Paper Title");
		}
		catch (Exception e)
		{
			fail();
		}
		assertEquals(paper1.getPTitle(), "Paper Title");
		assertEquals(paper1.getPReviews().length, 3);
		assertEquals(paper1.getPReviews()[0], null);
		assertEquals(paper1.getPReviews()[1], null);
		assertEquals(paper1.getPReviews()[2], null);
	}
	
	@BeforeClass
	public static void setup() {
		paper2 = new WorkshopPaper();
		empty_paper = new WorkshopPaper();
		try {
			paper_one_review = new WorkshopPaper("Only One Review");
			paper_two_reviews = new WorkshopPaper("Only Two Reviews");
			paper_three_reviews = new WorkshopPaper("Three Reviews");
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		try {
			review01 = new WorkshopReview(3, "Generally not bad.");
			review02 = new WorkshopReview(4, "Brilliant!");
			review03 = new WorkshopReview(5, "Excellent!");
		}catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	@Test
	public void test14() {
		try {
			paper2.setPTitle("");
		}
		catch (Exception e) {
			if (e.getClass() == Exception.class) {
				return; //it passed
			}
		}
		fail();
	}
	
	@Test
	public void test15() {
		try {
			paper2.setPTitle("New Paper Title");
		}
		catch (Exception e) {
			fail();
		}
		assertEquals(paper2.getPTitle(), "New Paper Title");
	}
	
	@Test
	public void test16() {
		try {
			paper2.addReview(review01);
			paper2.addReview(review02);
			paper2.addReview(review03);
			paper2.addReview(review01);
		}
		catch(Exception e)
		{
			if (e.getClass() == Exception.class) {
				return; //it passed
			}
		}
		fail();
	}
	
	@Test
	public void test17() {
		try {
			paper_one_review.addReview(review01);
			paper_two_reviews.addReview(review01);
			paper_two_reviews.addReview(review02);
			paper_three_reviews.addReview(review01);
			paper_three_reviews.addReview(review02);
			paper_three_reviews.addReview(review03);
		}
		catch(Exception e)
		{
			fail();
		}
		assertEquals(review01, paper_one_review.getPReviews()[0]);
		assertEquals(null, paper_one_review.getPReviews()[1]);
		assertEquals(null, paper_one_review.getPReviews()[2]);
		assertEquals(review01, paper_two_reviews.getPReviews()[0]);
		assertEquals(review02, paper_two_reviews.getPReviews()[1]);
		assertEquals(null, paper_two_reviews.getPReviews()[2]);
		assertEquals(review01, paper_three_reviews.getPReviews()[0]);
		assertEquals(review02, paper_three_reviews.getPReviews()[1]);
		assertEquals(review03, paper_three_reviews.getPReviews()[2]);
		
	}
	
	@Test
	public void test18() {
		assertEquals(Math.abs(empty_paper.getAverageScore() - 0) < epsilon, true);
	}
	
	@Test
	public void test19() {
		assertEquals(Math.abs(paper_one_review.getAverageScore() - 3.0) < epsilon, true);
		assertEquals(Math.abs(paper_two_reviews.getAverageScore() - 3.5) < epsilon, true);
		assertEquals(Math.abs(paper_three_reviews.getAverageScore() - 4.0) < epsilon, true);
	}
	
	@Test
	public void test20() {
		assertEquals(empty_paper.toString(), "This paper has not been reviewed");
	}
	
	@Test
	public void test21() {
		assertEquals(paper_one_review.toString(), "Average Score = " + "***" + "\n\n"+"Review 1:\n" + "Score = " + "***" + "\n Review: " + "Generally not bad." + "\n" + "\n");
		assertEquals(paper_two_reviews.toString(), "Average Score = " + "****" + "\n\n"+"Review 1:\n" + "Score = " + "***" + "\n Review: " + "Generally not bad." + "\n" + "\n" + "Review 2:\n" + "Score = " + "****" + "\n Review: " + "Brilliant!" + "\n" + "\n");
		
	}
	
	@Test
	public void test22() {
		assertEquals(paper_three_reviews.toString(), "Average Score = " + "****" + "\n\n"+"Review 1:\n" + "Score = " + "***" + "\n Review: " + "Generally not bad." + "\n" + "\n" + "Review 2:\n" + "Score = " + "****" + "\n Review: " + "Brilliant!" + "\n" + "\n" + "Review 3:\n" + "Score = " + "*****" + "\n Review: " + "Excellent!" + "\n" + "\n");
	}

}
