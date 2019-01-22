import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FilePartReader {
    private StringBuilder searchedPart;

    public FilePartReader() {
        this.searchedPart = new StringBuilder();
    }

    public void setup(Integer fromLine, Integer toLine) {
        if (fromLine < 1) {
            throw new IllegalArgumentException("Starter line argument can't be less than 0");
        } else if (fromLine > toLine) {
            throw new IllegalArgumentException("You need to start first and finish later!");
        }
    }

    public String read(String filePath, Integer fromLine, Integer toLine) throws IOException {
        setup(fromLine, toLine);
        StringBuilder fileResult = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Integer currentLine = 1;
        while ((line = reader.readLine()) != null) {
            fileResult.append(currentLine).append(": ").append(line).append('\n');
            currentLine++;
        }
        reader.close();
        return fileResult.toString();
    }

    public String readLines(String filePath, Integer fromLine, Integer toLine) throws IOException {
        try {
            read(filePath, fromLine, toLine);

        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Integer currentLine = 1;
        this.searchedPart = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            if (fromLine <= currentLine && toLine >= currentLine) {
                this.searchedPart.append(line).append(" ");
            }
            currentLine++;
        }
        reader.close();
        return searchedPart.toString();
    }


    public static void main(String[] args) throws IOException {
        String filePath = "./test.txt";
        int fromLines = 1;
        int toLines = 2;
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(new FilePartReader());
        System.out.println(fileWordAnalyzer.getWordsOrderedAlphabetically(filePath, fromLines, toLines));
        fileWordAnalyzer.getWordsContainingSubstring(filePath, fromLines, toLines, "ea");
        fileWordAnalyzer.getStringsWhichPalindromes(filePath, fromLines, toLines);


    }
}
