import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACE = "\\s+";
    public static final int DEFAULT_WORD_COUNT = 1;

    public String getResult(String wholeSentence) {


        if (isSingleWord(wholeSentence)) {
            return wholeSentence + " 1";
        }
        try {
            List<WordInfo> wordInfoList = calculateWordFrequency(wholeSentence);

            wordInfoList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordInfo w : wordInfoList) {
                String s = w.getWord() + " " + w.getWordCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {


            return "Calculate Error";
        }

    }

    private boolean isSingleWord(String wholeSentence) {
        return wholeSentence.split(WHITE_SPACE).length == DEFAULT_WORD_COUNT;
    }

    private List<WordInfo> calculateWordFrequency (String wholeSentence){
        List<String> words = Arrays.asList(wholeSentence.split(WHITE_SPACE));
        List<WordInfo> wordInfo = new ArrayList<>();
        for (String word: new HashSet<>(words)){
            int count = Collections.frequency(words,word);
            wordInfo.add(new WordInfo(word, count));
        }
        wordInfo.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
        return wordInfo;
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getWord(), arr);
            } else {
                map.get(input.getWord()).add(input);
            }
        }


        return map;
    }


}
