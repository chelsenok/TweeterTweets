package stategrade;


class AverageGrade {

    private float mAverage;
    private int mCount;

    public AverageGrade() {
        mCount = 0;
        mAverage = 0;
    }

    public float refresh(float grade) {
        float amount = mAverage * mCount + grade;
        return mAverage = amount / ++mCount;
    }

    public float getAverage() {
        return mAverage;
    }



}
