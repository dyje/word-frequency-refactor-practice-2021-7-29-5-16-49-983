import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACE = "\\s+";
    public static final int DEFAULT_WORD_COUNT = 1;
    public static final String ERROR = "Calculate Error";
    public static final String NEW_LINE = "\n";
    
    public String getResult(String wholeSentence) {

        try {
            List<String> words = splitByWords(wholeSentence);
            List<WordInfo> wordInfoList = calculateWordFrequency(wholeSentence);
            wordInfoList = sortWords(wordInfoList);
            return consolidateWords(wordInfoList);

        } catch (Exception exception) {
            return ERROR;
        }
    }

    private List<WordInfo> calculateWordFrequency(String wholeSentence) {
        List<String> words = splitByWords(wholeSentence);
        List<WordInfo> wordInfo = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int wordCount = Collections.frequency(words, word);
            wordInfo.add(new WordInfo(word, wordCount));
        }
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

    private List<String> splitByWords(String wholeSentence) {
        return Arrays.asList(wholeSentence.split(WHITE_SPACE));
    }

    private List<WordInfo> sortWords(List<WordInfo> wordInfoList) {
        return wordInfoList
                .stream()
                .sorted((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount())
                .collect(Collectors.toList());
    }
}
