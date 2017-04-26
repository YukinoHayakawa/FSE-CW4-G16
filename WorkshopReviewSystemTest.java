import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by Yuki on 4/26/2017.
 */
public class WorkshopReviewSystemTest
{
    private InputStream original_stdin;
    private PrintStream original_stdout;
    private String original_line_sep;

    private static String ln_menu = "What do you want to do?\n O = Overview, P = Add Paper, R = Add Review, [num] = Detail of that paper, X = exit\n";
    private static String ln_exit = "Goodbye!\n";
    private static String lns_start_papers_overview =
        "1) Paper 1 is great - 3.0\n"
        + "2) Paper 2 is my best work - 1.3333334\n";
    private static String lns_add_paper_success =
        "What is the title of the paper?\n[Paper added]\n";
    private static String lns_add_review_prompts =
        "Which paper do you want to add a review to?\nWhat score do you give it?\nPlease enter your review:\n";
    private static String str_error_begin = "Something went wrong: ";

    @Before
    // backup original system properties & IO streams
    public void setUp() throws Exception
    {
        // make sure newline characters is only \n
        original_line_sep = System.getProperty("line.separator");
        System.setProperty("line.separator", "\n");

        original_stdin = System.in;
        original_stdout = System.out;
    }

    @After
    // restore system IO streams and properties
    public void tearDown() throws Exception
    {
        System.setProperty("line.separator", original_line_sep);
        System.setIn(original_stdin);
        System.setOut(original_stdout);
    }

    @Test
    public void exit()
    {
        assertIOEquals(
            "X\n",
            ln_menu + ln_exit
        );
    }

    @Test
    public void overview__exit()
    {
        assertIOEquals(
            "O\nX\n",
            ln_menu + lns_start_papers_overview +
            ln_menu + ln_exit
        );
    }

    @Test
    public void add_paper__overview__exit()
    {
        assertIOEquals(
            "P\nAwesome Paper\nO\nX\n",
            ln_menu + lns_add_paper_success +
                ln_menu + lns_start_papers_overview + "3) Awesome Paper - 0.0\n" +
                ln_menu + ln_exit
        );
    }

    @Test
    public void add_paper__add_review__overview__detail__exit()
    {
        assertIOEquals(
            "P\nAwesome Paper\nR\n3\n4\nIt's awesome.\nO\n3\nX\n",
            ln_menu + lns_add_paper_success +
                ln_menu + lns_add_review_prompts + "[Review added to Paper 3]\n" +
                ln_menu + lns_start_papers_overview + "3) Awesome Paper - 4.0\n" +
                ln_menu + "\n" +
                "Paper 3 - Average Score = ****\n" +
                "\n" +
                "Review 1:\n" +
                "Score = ****\n" +
                " Review: It's awesome.\n\n" +
                ln_menu + ln_exit
        );
    }

    @Test
    public void add_paper__add_review_3__overview__detail__exit()
    {
        assertIOEquals(
            "P\nAwesome Paper\n" +
                "R\n3\n4\nIt's awesome.\n" +
                "R\n3\n1\nIt's just awesome.\n" +
                "R\n3\n5\nIt's very awesome.\n" +
                "O\n3\nX\n",
            ln_menu + lns_add_paper_success +
                ln_menu + lns_add_review_prompts + "[Review added to Paper 3]\n" +
                ln_menu + lns_add_review_prompts + "[Review added to Paper 3]\n" +
                ln_menu + lns_add_review_prompts + "[Review added to Paper 3]\n" +
                ln_menu + lns_start_papers_overview + "3) Awesome Paper - 3.3333333\n" +
                ln_menu + "\n" +
                "Paper 3 - Average Score = ***\n" +
                "\n" +
                "Review 1:\n" +
                "Score = ****\n" +
                " Review: It's awesome.\n\n" +
                "Review 2:\n" +
                "Score = *\n" +
                " Review: It's just awesome.\n" +
                "\n" +
                "Review 3:\n" +
                "Score = *****\n" +
                " Review: It's very awesome.\n" +
                "\n" +
                ln_menu + ln_exit
        );
    }

    @Test
    public void add_review_extra_review__exit()
    {
        assertIOEquals(
            "R\n1\n4\nIt's awesome.\nX\n",
            ln_menu + lns_add_review_prompts + str_error_begin + "java.lang.Exception: 3 reviews already!\n\n" +
                ln_menu + ln_exit
        );
    }

    private void assertIOEquals(String test_in, String expected_out)
    {
        // build a InputStream from the assumed user input string, to feed stdin.
        InputStream stream_in = new ByteArrayInputStream(test_in.getBytes());
        OutputStream stream_out = new ByteArrayOutputStream();
        PrintStream print_out = new PrintStream(stream_out);

        // redirect test user input to stdin
        System.setIn(stream_in);
        // redirect stdout to our PrintStream
        System.setOut(print_out);

        // run the main method just as usual
        WorkshopReviewSystem.main(new String[0]);

        // convert the output to a string and compare it to expected output
        String actual_out = stream_out.toString();
        assertEquals(expected_out, actual_out);
    }
}