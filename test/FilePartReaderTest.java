import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.instanceOf;


public class FilePartReaderTest {
    @Test
    public void isFromLineBelow1ThrowsException() {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, () -> filePartReader.readLines(filePath, 0, 10));
    }

    @Test
    public void isFromLineSmallerThanToLineThrowsException() {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IllegalArgumentException.class, () -> filePartReader.readLines(filePath, 60, 10));
    }

    @Test
    public void isFileFoundThrowsException() {
        String filePath = "./test2.txt";
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(IOException.class, () -> filePartReader.readLines(filePath, 10, 20));
    }

    @Test
    public void readReturnsString() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        assertThat(filePartReader.read(filePath, 1, 5), instanceOf(String.class));
    }

    @Test
    public void readLineCanReturnOneLine() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        assertEquals("you're too smart to go down any not-so-good street. ", filePartReader.readLines(filePath, 14, 14));
    }

    @Test
    public void readLineCanReturnFirstLine() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        assertEquals("Congratulations! ", filePartReader.readLines(filePath, 1, 1));
    }

    @Test
    public void readLineReturnsString() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        assertThat(filePartReader.readLines(filePath, 1, 5), instanceOf(String.class));
    }

    @Test
    public void alphabeticOrderingWorks() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Congratulations!");
        expected.add("day.");
        expected.add("is");
        expected.add("Today");
        expected.add("your");
        assertEquals(expected, fileWordAnalyzer.getWordsOrderedAlphabetically(filePath, 1, 2));

    }

    @Test
    public void getWordsOrderedAlphabeticallyReturnsArrayList() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertThat(fileWordAnalyzer.getWordsOrderedAlphabetically(filePath, 1, 5), instanceOf(ArrayList.class));

    }


    @Test
    public void getWordsContainingSubstringReturnsArrayList() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertThat(fileWordAnalyzer.getWordsContainingSubstring(filePath, 1, 5, "f"), instanceOf(ArrayList.class));

    }

    @Test
    public void getWordsContainingSubstringReturnsCorrectWords() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("who'll");
        expected.add("where");
        assertEquals(expected, fileWordAnalyzer.getWordsContainingSubstring(filePath, 10, 15, "wh"));

    }

    @Test
    public void getStringsWhichPalindromesReturnsArrayList() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertThat(fileWordAnalyzer.getStringsWhichPalindromes(filePath, 1, 5), instanceOf(ArrayList.class));

    }

    @Test
    public void getStringsWhichPalindromesReturnsCorrectWords() throws IOException {
        String filePath = "./test.txt";
        FilePartReader filePartReader = new FilePartReader();
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("deleveled");
        expected.add("anna");
        expected.add("a");
        expected.add("a");
        assertEquals(expected, fileWordAnalyzer.getStringsWhichPalindromes(filePath, 30, 50));


    }


}
