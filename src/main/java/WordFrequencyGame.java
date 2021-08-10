import java.text.MessageFormat;
import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACE = "\\s+";
    public static final int DEFAULT_WORD_COUNT = 1;
    public static final String ERROR = "Calculate Error";
    public static final String NEW_LINE = "\n";


    public String getResult(String wholeSentence) {

        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(wholeSentence);
            return consolidateWords(wordInfoList);

        } catch (Exception exception) {


            return ERROR;
        }

    }

    private List<WordInfo> calculateWordFrequency(String wholeSentence) {
        List<String> words = Arrays.asList(wholeSentence.split(WHITE_SPACE));
        List<WordInfo> wordInfo = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int wordCount = Collections.frequency(words, word);
            wordInfo.add(new WordInfo(word, wordCount));
        }
        wordInfo.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
        return wordInfo;
    }

    private String consolidateWords(List<WordInfo> wordInfoList) {

        StringJoiner joiner = new StringJoiner(NEW_LINE);
        for (WordInfo word : wordInfoList) {
            String output = word.getWord() + " " + word.getWordCount();
            joiner.add(output);
        }
        return joiner.toString();
    }


}
