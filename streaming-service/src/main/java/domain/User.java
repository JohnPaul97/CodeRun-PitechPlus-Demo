package domain;

import java.util.Map;

public class User {

    private String username;
    private int age;
    private UserType userType;
    private Map<Film, Integer> filmsWatched;

    public void watchFilm(final Film film, final Integer timeSpent) {
        //TODO: keep track of the user's time spent on a certain film
    }

    public boolean hasFullyWatchFilm(final Film film) {
        //TODO: check if the user has watched the whole film
        return false;
    }

    public double getPaymentPlanValue() {
        switch (this.userType) {
            case STANDARD:
                return 5;
            case PREMIUM:
                return 8;
            case FAMILY:
                return 12;
            default:
                // should not happen
                return -1;
        }
    }
}
