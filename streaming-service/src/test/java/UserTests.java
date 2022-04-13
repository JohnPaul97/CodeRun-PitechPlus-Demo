import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import domain.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests extends UnitTest {

    @Test
    public void givenNewFilm_whenWatchFilm_thenValueAddedToMap() {
        //Given
        final User user1 = new User("Sorin", 19, UserType.STANDARD);
        final Film film1 = new Film(1L, "Title 1", Genre.COMEDY, 150);

        //When
        user1.watchFilm(film1, 133);

        //Then
        final Map<Film, Integer> filmsWatched = user1.getFilmsWatched();
        assertThat(filmsWatched).containsKey(film1);
        assertThat(filmsWatched.get(film1)).isEqualTo(133);
    }

    @Test
    public void givenExistingFilm_whenWatchFilm_thenValueOverwritten() {
        //Given
        final User user1 = new User("Sorin", 19, UserType.STANDARD);
        final Film film1 = new Film(1L, "Title 1", Genre.COMEDY, 150);
        final Map<Film, Integer> existingFilmsList = new HashMap<>();
        existingFilmsList.put(film1, 133);
        user1.setFilmsWatched(existingFilmsList);

        //When
        user1.watchFilm(film1, 45);

        //Then
        final Map<Film, Integer> filmsWatched = user1.getFilmsWatched();
        assertThat(filmsWatched).containsKey(film1);
        assertThat(filmsWatched.get(film1)).isEqualTo(45);
    }

    @Test
    public void givenNegativeTimeSpent_whenWatchFilm_thenValueIsNotSet() {
        //Given
        final User user1 = new User("Sorin", 19, UserType.STANDARD);
        final Film film1 = new Film(1L, "Title 1", Genre.COMEDY, 150);
        final Map<Film, Integer> existingFilmsList = new HashMap<>();
        existingFilmsList.put(film1, 133);
        user1.setFilmsWatched(existingFilmsList);

        //When
        user1.watchFilm(film1, -45);

        //Then
        final Map<Film, Integer> filmsWatched = user1.getFilmsWatched();
        assertThat(filmsWatched).containsKey(film1);
        assertThat(filmsWatched.get(film1)).isEqualTo(133);
    }

    @Test
    public void givenTimeSpentLargerThanFilmsLength_whenWatchFilm_thenValueIsNotSet() {
        //Given
        final User user1 = new User("Sorin", 19, UserType.STANDARD);
        final Film film1 = new Film(1L, "Title 1", Genre.COMEDY, 150);
        final Map<Film, Integer> existingFilmsList = new HashMap<>();
        existingFilmsList.put(film1, 133);
        user1.setFilmsWatched(existingFilmsList);

        //When
        user1.watchFilm(film1, 188);

        //Then
        final Map<Film, Integer> filmsWatched = user1.getFilmsWatched();
        assertThat(filmsWatched).containsKey(film1);
        assertThat(filmsWatched.get(film1)).isEqualTo(133);
    }
}
