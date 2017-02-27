package rating;

import java.util.*;

public class TextRatingAssigner {

    private static final String DICTIONARY_NAME = "res/sentiments.csv";

    private static HashMap<Integer, HashMap<String, Float>> mQuantitySentimentsMap;

    private float mValue;
    private int mCount;
    private ArrayList<String> mTweetWords;

    static {
        LinkedHashMap<String, Float> sentiments = new Reader().readSentiments(DICTIONARY_NAME);
        mQuantitySentimentsMap = new HashMap<>();
        for (Map.Entry<String, Float> entry :
                sentiments.entrySet()) {

            String string = entry.getKey();
            int wordsCount = string.split(" ").length;
            if (!mQuantitySentimentsMap.containsKey(wordsCount)) {
                mQuantitySentimentsMap.put(wordsCount, new HashMap<>());
            }
            mQuantitySentimentsMap.get(wordsCount).put(string.toLowerCase(), entry.getValue());
        }
    }

    public TextRatingAssigner() {

    }

    public float get(String text) {
        mValue = 0;
        mCount = 0;

        startCalculationForEach(OnSentenceSeparator.separate(text));
        if (mCount != 0) {
            return mValue / mCount;
        }
        return 0;
    }

    private void calculate(int quantity) {
        boolean isChecked = false;
        for (int i = 0; mTweetWords != null && i + quantity <= mTweetWords.size(); i++) {
            float value = getWordAssign(quantity, i, i + quantity);
            if (value != 0) {
                mValue += value;
                isChecked = true;
            }
        }
        if (mTweetWords != null && quantity > 1) {
            if (isChecked) {
                calculate(mTweetWords.size());
            } else {
                calculate(quantity - 1);
            }
        }
    }

    private void startCalculationForEach(ArrayList<String> sentences) {
        for (String text :
                sentences) {
            String[] words = TextCleaner.clean(text.toLowerCase());
            mTweetWords = new ArrayList<>(Arrays.asList(words));
            calculate(mTweetWords.size());
        }
    }

    private float getWordAssign(int quantity, int from, int to) {
        float value;
        try {
            List<String> subList = mTweetWords.subList(from, to);
            String str = stringFromList(subList);
            value = mQuantitySentimentsMap.get(quantity).get(str);
        } catch (Exception exc) {
            return 0;
        }
        mCount++;

        int[][] arrays = new int[][]{
                new int[]{
                        0, from
                },
                new int[]{
                        to, mTweetWords.size()
                }
        };
        ArrayList<String> twice = new ArrayList<>();
        for (int[] array :
                arrays) {
            try {
                twice.add(stringFromList(mTweetWords.subList(array[0], array[1])));
            } catch (Exception ignored) {
            }
        }

        if (twice.size() != 0) {
            startCalculationForEach(twice);
        } else {
            mTweetWords = null;
        }
        return value;
    }

    private String stringFromList(List<String> list) {
        String str = "";
        for (String s : list
                ) {
            str += s + " ";
        }
        return str.substring(0, str.length() - 1);
    }
}
