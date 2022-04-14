import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import domain.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest extends UnitTest {

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
    public void givenTimeSpentLargerThanFilmsLength_whenWatchFilm_thenValueIsNotAdded() {
        //Given
        final User user1 = new User("Sorin", 19, UserType.STANDARD);
        final Film film1 = new Film(1L, "Title 1", Genre.COMEDY, 150);

        //When
        user1.watchFilm(film1, 188);

        //Then
        final Map<Film, Integer> filmsWatched = user1.getFilmsWatched();
        assertThat(filmsWatched).doesNotContainKey(film1);
    }

    @Test
    public void givenNegativeTimeSpent_whenWatchFilm_thenValueIsNotAdded() {
        //Given
        final User user1 = new User("Sorin", 19, UserType.STANDARD);
        final Film film1 = new Film(1L, "Title 1", Genre.COMEDY, 150);

        //When
        user1.watchFilm(film1, -57);

        //Then
        final Map<Film, Integer> filmsWatched = user1.getFilmsWatched();
        assertThat(filmsWatched).doesNotContainKey(film1);
    }

    @Test
    public void givenExistingFilm_whenWatchFilm_thenValueIsUpdated() {
        //Given
        final User user1 = getMeAnUser();
        final Film film1 = new Film(1L, "Title 1", Genre.COMEDY, 150);
        final Map<Film, Integer> films = new HashMap<>();
        films.put(film1, 75);
        user1.setFilmsWatched(films);

        //When
        user1.watchFilm(film1, 37);

        //Then
        final Map<Film, Integer> filmsWatched = user1.getFilmsWatched();
        assertThat(filmsWatched).containsKey(film1);
        assertThat(filmsWatched.get(film1)).isEqualTo(37);
    }

    private User getMeAnUser() {
        return new User("Sorin", 19, UserType.STANDARD);
    }
}
