package stategrade;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public synchronized float refresh(String state, float grade) {
        if (!mStateGradeHashMap.containsKey(state)) {
            mStateGradeHashMap.put(state, new AverageGrade());
        }
        return mStateGradeHashMap.get(state).refresh(grade);
    }

    public float getMin() {
        try {
            return getExtra(Float.class.getDeclaredMethod("min"));
        } catch (NoSuchMethodException e) {
            return 0;
        }
    }

    public float getMax() {
        try {
            return getExtra(Float.class.getDeclaredMethod("max"));
        } catch (NoSuchMethodException e) {
            return 0;
        }
    }

    private float getExtra(Method method) {
        float value;
        if (method.getName().equals("min")) {
            value = Float.MAX_VALUE;
        } else if (method.getName().equals("max")) {
            value = Float.MIN_VALUE;
        } else {
            return 0;
        }

        for (Map.Entry<String, AverageGrade> entry :
                mStateGradeHashMap.entrySet()) {
            try {
                value = (float) method.invoke(Float.class, value, entry.getValue().getAverage());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return value;
    }
}
