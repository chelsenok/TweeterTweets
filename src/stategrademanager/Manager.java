package stategrademanager;

import java.util.HashMap;

public class Manager {

    private HashMap<String, AverageGrade> mStateGradeHashMap;

    public Manager() {
        mStateGradeHashMap = new HashMap<>();
    }

    public float refresh(String state, float grade) {
        if (!mStateGradeHashMap.containsKey(state)) {
            mStateGradeHashMap.put(state, new AverageGrade());
        }
        return mStateGradeHashMap.get(state).refresh(grade);
    }
}
