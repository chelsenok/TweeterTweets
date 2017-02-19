package gmap.geocoding;

public enum AreaLevel {
    AREA_LEVEL_1 {
        @Override
        String value() {
            return "&result_type=administrative_area_level_1";
        }
    },

    AREA_LEVEL_2 {
        @Override
        String value() {
            return "&result_type=administrative_area_level_2";
        }
    },

    AREA_LEVEL_3 {
        @Override
        String value() {
            return "&result_type=administrative_area_level_2";
        }
    },

    COUNTRY {
        @Override
        String value() {
            return "&result_type=country";
        }
    },

    STREET {
        @Override
        String value() {
            return "&result_type=street_address";
        }
    };


    abstract String value();
}
