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
    public void overview_exit()
    {
        assertIOEquals(
            "O\nX\n",
            ln_menu
                + "1) Paper 1 is great - 3.0\n"
                + "2) Paper 2 is my best work - 1.3333334\n"
            + ln_menu + ln_exit
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