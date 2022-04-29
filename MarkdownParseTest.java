import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.*;
public class MarkdownParseTest {

    public static MarkdownParse m = new MarkdownParse();

    Object[][] allArrays = {{"testFileArray"}, {"testFile2Array"}, 
        {"testFile3Array"}, {"testFile4Array"}, {"testFile5Array"}, 
        {"testFile6Array"}, {"testFile7Array"}, {"testFile8Array"}};

    String[] fileNames = {"test-file.md", "test-file2.md", "test-file3.md",
        "test-file4.md", "test-file5.md", "test-file6.md", "test-file7.md",
        "test-file8.md"};
    
    @Before 
    public void setUp() throws Exception {
        for (int i = 0; i < fileNames.length; i++) {
            Path fileName = Path.of(fileNames[i]);
            String content = Files.readString(fileName);
            ArrayList<String> realContent = m.getLinks(content);
            allArrays[i] = realContent.toArray();
        }
    }

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void getLinksTest() throws Exception {
        String[] expected = {"https://something.com", "some-thing.html"};

        assertArrayEquals(expected, allArrays[0]);
    }

    @Test
    public void getLinksTest2() throws Exception {
        String[] expected = {"https://something.com", "some-page.html"};

        assertArrayEquals(expected, allArrays[1]);
    }

    @Test
    public void getLinksTest3() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[2]);
    }

    @Test
    public void getLinksTest4() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[3]);
    }

    @Test
    public void getLinksTest5() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[4]);
    }

    @Test
    public void getLinksTest6() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[5]);
    }

    @Test
    public void getLinksTest7() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[6]);
    }

    @Test
    public void getLinksTest8() throws Exception {
        String[] expected = {};

        assertArrayEquals(expected, allArrays[7]);
    }
    
}