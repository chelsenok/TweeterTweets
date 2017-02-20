package search.API;

import twitter4j.GeoLocation;

enum UnitedStatesZones {
    Center {
        @Override
        GeoLocation geoLocation() {
            return new GeoLocation(40.23, -97.44);
        }

        @Override
        double radius() {
            return 2800;
        }
    },
    Alaska {
        @Override
        GeoLocation geoLocation() {
            return new GeoLocation(64.88, -154.19);
        }

        @Override
        double radius() {
            return 1700;
        }
    },
    Hawaii {
        @Override
        GeoLocation geoLocation() {
            return new GeoLocation(20.96, -157.13);
        }

        @Override
        double radius() {
            return 350;
        }
    };

    abstract GeoLocation geoLocation();

    abstract double radius();
}
