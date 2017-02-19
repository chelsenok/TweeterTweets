package gmap.geocoding;

public class JSONResponse {

    private String mStateLongName;
    private String mStateShortName;
    private StatusCodes mStatusCodes;

    public JSONResponse(String stateLongName, String stateShortName, StatusCodes statusCodes) {
        mStateLongName = stateLongName;
        mStateShortName = stateShortName;
        mStatusCodes = statusCodes;
    }

    public JSONResponse(StatusCodes statusCodes) {
        mStatusCodes = statusCodes;
    }

    public String getStateLongName() {
        return mStateLongName;
    }

    public String getStateShortName() {
        return mStateShortName;
    }

    public StatusCodes getStatusCodes() {
        return mStatusCodes;
    }
}
