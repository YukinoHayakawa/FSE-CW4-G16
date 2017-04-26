import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

public class WorkshopReviewTest {

	WorkshopReview review1 = null;
	WorkshopReview review2 = null;
	static WorkshopReview review3 = null;
	static WorkshopReview review4 = null;
	
	@Test
	public void test1() {
		review1 = new WorkshopReview();
		assertEquals(review1.getRScore(),1);
	}
	
	@Test
	public void test2() {
		review1 = new WorkshopReview();
		assertEquals(review1.getRReview(),"No Review");
	}
	
	@Test
	public void test3() {
		try {
			review2 = new WorkshopReview(0, "This is really awful!");
		}
		catch (Exception e1) {
			try {
				review2 = new WorkshopReview(6, "This is really nice!");
			}
			catch (Exception e2) {
				if (e1.getClass() == Exception.class && e2.getClass() == Exception.class) {
					return; //it passed
				}
			}
		}
		fail();
	}
	
	@Test
	public void test4() {
		try {
			review2 = new WorkshopReview(3, "");
		}
		catch (Exception e) {
			if (e.getClass() == Exception.class) {
				return; //it passed
			}
		}
		fail();
	}
	
	@Test
	public void test5() {
		try {
			review2 = new WorkshopReview(5, "Impressive work!");
		}
		catch (Exception e) {
			fail();
		}
		assertEquals(review2.getRScore(),5);
		assertEquals(review2.getRReview(),"Impressive work!");
	}
	
	@BeforeClass
	public static void setup() {
		try {
			review3 = new WorkshopReview(3, "You can do better.");
			review4 = new WorkshopReview(1, "Very poor work...");
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
		
	}
	
	@Test
	public void test6() {
		try {
			review3.setRScore(0);
		}
		catch (Exception e1) {
			try {
				review3.setRScore(6);
			}
			catch (Exception e2) {
				if (e1.getClass() == Exception.class && e2.getClass() == Exception.class) {
					return; //it passed
				}
			}
		}
		fail();
	}
	
	@Test
	public void test7() {
		try {
			review3.setRReview("");
		}
		catch (Exception e) {
			if (e.getClass() == Exception.class) {
				return; //it passed
			}
		}
		fail();
	}
	
	@Test
	public void test8() {
		assertEquals(review3.toString(), "Score = " + "***" + "\n Review: " + "You can do better." + "\n");
	}
	
	@Test
	public void test9() {
		try {
			review4.setRScore(4);
		}
		catch (Exception e)
		{
			fail();
		}
		assertEquals(review4.getRScore(), 4);
	}
	
	@Test
	public void test10() {
		try {
			review4.setRReview("Generally well done!");
		}
		catch (Exception e)
		{
			fail();
		}
		assertEquals(review4.getRReview(), "Generally well done!");
	}

}
