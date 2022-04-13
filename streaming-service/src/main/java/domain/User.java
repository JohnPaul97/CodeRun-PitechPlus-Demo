package domain;

import java.util.*;
import java.util.stream.Collectors;

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

    public UserType getUserType() {
        return userType;
    }

    public void watchFilm(final Film film, final Integer timeSpent) {
        if (timeSpent < 0 || timeSpent > film.getLength()) {
            return;
        }
        this.filmsWatched.put(film, timeSpent);
    }

    public List<Film> getListOfFullyWatchedFilms() {
        return this.filmsWatched.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getLength() == entry.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
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
