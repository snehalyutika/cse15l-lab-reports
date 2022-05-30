import java.nio.file.*;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Tester Class for the MarkdownParse.java file.
 * 
 * Instance Variables:
 * testPath  - String to be concatenated for location of testfiles
 * allArrays - Multidimensional array storing result of each test
 * fileNames - Name of each test files
 */
public class MarkdownParseTest {

    Object[][] allArrays = {{"testFileArray"}, {"testFile2Array"}, 
        {"testFile3Array"}, {"testFile4Array"}, {"testFile5Array"}, 
        {"testFile6Array"}, {"testFile7Array"}, {"testFile8Array"},
        {"testFile9Array"}, {"snippet1"}, {"snippet2"}, {"snippet3"}};
    String[] fileNames = {"test-file.md", "test-file2.md", "test-file3.md",
        "test-file4.md", "test-file5.md", "test-file6.md", "test-file7.md",
        "test-file8.md", "test-file9.md" , "snippet1.md", "snippet2.md",
        "snippet3.md"};
    
    /**
     * Sets up the storage of results of MarkdownParse for each test file.
     * @throws Exception
     */
    @Before 
    public void setUp() throws Exception {
        for (int i = 0; i < fileNames.length; i++) {
            Path fileName = Path.of("testfiles",fileNames[i]);
            String content = Files.readString(fileName);
            ArrayList<String> realContent = MarkdownParse.getLinks(content);
            allArrays[i] = realContent.toArray();
        }
    }

    /**
     * Simple test where there are two links, no fluff.
     * @throws Exception
     */
    @Test
    public void getLinksTest() throws Exception {
        String[] expected = {"https://something.com", "some-thing.html"};

        assertArrayEquals(expected, allArrays[0]);
    }

    /**
     * Test getLinks for a normal markdown file situation
     * @throws Exception
     */
    @Test
    public void getLinksTest2() throws Exception {
        String[] expected = {"https://something.com", "some-page.html"};

        assertArrayEquals(expected, allArrays[1]);
    }

    /**
     * Test getLinks when there are brackets, no test, and no parenthesis
     * @throws Exception
     */
    @Test
    public void getLinksTest3() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[2]);
    }

    /**
     * Test getLinks when there are brackets with no text, and no parenthesis 
     * but a sentence immediately following
     * @throws Exception
     */
    @Test
    public void getLinksTest4() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[3]);
    }

    /**
     * Test getLinks when there are brackets with text, few lines inbetween 
     * parenthesis with a sentence inbetween as well.
     * @throws Exception
     */
    @Test
    public void getLinksTest5() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[4]);
    }

    /**
     * Test getLinks on an image
     * @throws Exception
     */
    @Test
    public void getLinksTest6() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[5]);
    }

    /**
     * Test getLinks when there are only close parenthesis and open bracket
     * @throws Exception
     */
    @Test
    public void getLinksTest7() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[6]);
    }

    /**
     * Test getLinks when there is an extra bracket following the parenthesis,
     * and no text within the brackets
     * @throws Exception
     */
    @Test
    public void getLinksTest8() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[7]);
    }

    /**
     * Test getLinks when there are purely no brackets
     * @throws Exception
     */
    @Test
    public void getLinksTest9() throws Exception {
        String[] expected = {"There is no parenthesis"};

        assertArrayEquals(expected, allArrays[8]);
    }

    /**
     * Test getLinks when there's conflicting code blocks
     * @throws Exception
     */
    @Test
    public void getLinksTestSnippet1() throws Exception {
        String[] expected = {"'google.com", "google.com", "ucsd.edu"};

        assertArrayEquals(expected, allArrays[9]);
    }

    /**
     * Test getLinks when there are nested links
     * @throws Exception
     */
    @Test
    public void getLinksTestSnippet2() throws Exception {
        String[] expected = {"a.com", "a.com(())", "(example.com)"};

        assertArrayEquals(expected, allArrays[10]);
    }
    
    /**
     * Test getLinks when there's a lot going on
     * @throws Exception
     */
    @Test
    public void getLinksTestSnippet3() throws Exception {
        String[] expected = {"https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule"};

        assertArrayEquals(expected, allArrays[11]);
    }
}