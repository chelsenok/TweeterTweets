package stategrademanager;

class AverageGrade {

    private float mAverage;
    private int mCount;

    public AverageGrade() {
        mCount = 0;
        mAverage = 0;
    }

    public float refresh(float grade) {
        float amount = mAverage * mCount + grade;
        mCount++;
        return mAverage = amount / mCount;
    }

}
