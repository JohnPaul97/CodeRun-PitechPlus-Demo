package domain;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String username;
    private int age;
    private UserType userType;
    private Map<Film, Integer> filmsWatched;

    public User(final String username, final int age, final UserType userType) {
        this.username = username;
        this.age = age;
        this.userType = userType;
        this.filmsWatched = new HashMap<>();
    }

    public Map<Film, Integer> getFilmsWatched() {
        return filmsWatched;
    }

    public void setFilmsWatched(Map<Film, Integer> filmsWatched) {
        this.filmsWatched = filmsWatched;
    }

    public void watchFilm(final Film film, final Integer timeSpent) {
        if (timeSpent < 0 || timeSpent > film.getLength()) {
            return;
        }
        this.filmsWatched.put(film, timeSpent);
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
