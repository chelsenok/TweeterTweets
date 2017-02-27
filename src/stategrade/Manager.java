package stategrade;

import java.util.HashMap;
import java.util.Map;

public class Manager {

    //// TODO: 25.02.2017 getExtra

    private static Manager sInstance;
    private HashMap<String, AverageGrade> mStateGradeHashMap;

    private Manager() {
        mStateGradeHashMap = new HashMap<>();
    }

    public static Manager getInstance() {
        if (sInstance == null) {
            sInstance = new Manager();
        }
        return sInstance;
    }

    public HashMap<String, String> getStateAverage() {
        HashMap<String, String> map = new HashMap<>();

        for (Map.Entry<String, AverageGrade> entry :
                mStateGradeHashMap.entrySet()) {
            map.put(entry.getKey(), String.valueOf(entry.getValue().getAverage()));
        }

        return map;
    }

    public synchronized float refresh(String state, float grade) {
        if (!mStateGradeHashMap.containsKey(state)) {
            mStateGradeHashMap.put(state, new AverageGrade());
        }
        return mStateGradeHashMap.get(state).refresh(grade);
    }

    public float getMin() {
        float value = Float.MAX_VALUE;

        for (Map.Entry<String, AverageGrade> entry :
                mStateGradeHashMap.entrySet()) {
            float current = entry.getValue().getAverage();
            if (current < value) {
                value = current;
            }
        }

        if (value == Float.MAX_VALUE) {
            return 0;
        }
        return value;
    }

    public float getMax() {
        float value = Float.MIN_VALUE;

        for (Map.Entry<String, AverageGrade> entry :
                mStateGradeHashMap.entrySet()) {
            float current = entry.getValue().getAverage();
            if (current > value) {
                value = current;
            }
        }

        if (value == Float.MIN_VALUE) {
            return 0;
        }
        return value;
    }
}
