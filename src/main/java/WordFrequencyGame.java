import java.text.MessageFormat;
import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACE = "\\s+";
    public static final int DEFAULT_WORD_COUNT = 1;
    public static final String ERROR = "Calculate Error";

    public String getResult(String wholeSentence) {


        if (isSingleWord(wholeSentence)) {
            return MessageFormat.format("{0} {1}",wholeSentence,DEFAULT_WORD_COUNT);
        }
        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(wholeSentence);
            return consolidateWords(wordInfoList);

        } catch (Exception e) {


            return ERROR;
        }

    }

    private boolean isSingleWord(String wholeSentence) {
        return wholeSentence.split(WHITE_SPACE).length == DEFAULT_WORD_COUNT;
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
        wordInfoList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo word : wordInfoList) {
            String output = word.getWord() + " " + word.getWordCount();
            joiner.add(output);
        }
        return joiner.toString();
    }
}
