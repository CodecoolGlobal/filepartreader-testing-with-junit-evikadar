import java.io.IOException;
import java.util.*;

public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List<String> getWordsOrderedAlphabetically (String filePath, Integer fromLine, Integer toLine) throws IOException {
        String wordPool = filePartReader.readLines(filePath, fromLine, toLine);
        ArrayList<String> alphabetical = new ArrayList<>(Arrays.asList(wordPool.split(" ")));
        alphabetical.sort(String::compareToIgnoreCase);
        return alphabetical;
    }

    public List getWordsContainingSubstring (String filePath, Integer fromLine, Integer toLine, String subString) throws IOException {
        String wordPool = filePartReader.readLines(filePath, fromLine, toLine);
        ArrayList<String> subStringers = new ArrayList<>();
        for (String word: wordPool.split(" ")) {
            if (word.contains(subString)) {
                subStringers.add(word);
            }
        }
        return subStringers;
    }

    public List getStringsWhichPalindromes (String filePath, Integer fromLine, Integer toLine) throws IOException {
        String wordPool = filePartReader.readLines(filePath, fromLine, toLine);
        ArrayList<String> palindromes = new ArrayList<>();
        for (String word: wordPool.split(" ")) {
            word = word.replaceAll("[.]|[,]|[?]|[!]|[(]|[)]","");
            String reverse = "";
            for (int i = word.length()-1; i >=0; i--) {
                reverse = reverse + word.charAt(i);
            }
            if (word.equals(reverse)) {
                palindromes.add(word);
            }
        }
        return palindromes;

    }
}
