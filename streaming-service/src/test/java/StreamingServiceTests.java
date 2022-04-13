import java.util.*;

import org.junit.jupiter.api.Test;

import domain.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamingServiceTests extends UnitTest {

    @Test
    public void givenNoPlatformUsers_whenGetPlatformRevenue_thenZeroIsReturned() {
        //Given
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), new ArrayList<>());

        //When
        final double revenue = streamingService.getPlatformRevenue();

        //Then
        assertThat(revenue).isEqualTo(0);
    }

    @Test
    public void givenPlatformUsers_whenGetPlatformRevenue_thenValueIsReturned() {
        //Given
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), users);

        //When
        final double revenue = streamingService.getPlatformRevenue();

        //Then
        assertThat(revenue).isEqualTo(25);
    }

    @Test
    public void givenNoPlatformUsers_whenGetDiscountImpact_thenZeroIsReturned() {
        //Given
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), new ArrayList<>());

        //When
        final double discount = streamingService.getDiscountImpact(15, UserType.PREMIUM);

        //Then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    public void givenPlatformUsers_whenGetDiscountImpactWithZeroPercentage_thenZeroIsReturned() {
        //Given
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), users);

        //When
        final double discount = streamingService.getDiscountImpact(0, UserType.STANDARD);

        //Then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    public void givenPlatformUsers_whenGetDiscountImpactWithNegativePercentage_thenZeroIsReturned() {
        //Given
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), users);

        //When
        final double discount = streamingService.getDiscountImpact(-135, UserType.FAMILY);

        //Then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    public void givenPlatformUsers_whenGetDiscountImpactWith100Percentage_thenFullValueIsReturned() {
        //Given
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), users);

        //When
        final double discount = streamingService.getDiscountImpact(100, UserType.STANDARD);

        //Then
        assertThat(discount).isEqualTo(5);
    }

    @Test
    public void givenPlatformUsers_whenGetDiscountImpactWithMoreThan100Percentage_thenOnlyFullValueIsReturned() {
        //Given
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), users);

        //When
        final double discount = streamingService.getDiscountImpact(177, UserType.PREMIUM);

        //Then
        assertThat(discount).isEqualTo(8);
    }

    @Test
    public void givenPlatformUsers_whenGetDiscountImpactForStandard_thenValueIsReturned() {
        //Given
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Daniel", 22, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), users);

        //When
        final double discount = streamingService.getDiscountImpact(15, UserType.STANDARD);

        //Then
        assertThat(discount).isEqualTo(1.5);
    }

    @Test
    public void givenPlatformUsers_whenGetDiscountImpactForPremium_thenValueIsReturned() {
        //Given
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), users);

        //When
        final double discount = streamingService.getDiscountImpact(20, UserType.PREMIUM);

        //Then
        assertThat(discount).isEqualTo(1.6);
    }

    @Test
    public void givenPlatformUsers_whenGetDiscountImpactForFamily_thenValueIsReturned() {
        //Given
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), users);

        //When
        final double discount = streamingService.getDiscountImpact(30, UserType.FAMILY);

        //Then
        assertThat(discount).isEqualTo(3.6);
    }

    @Test
    public void givenNoPlatformUsers_whenGetFullyWatchedFilms_thenEmptyMapReturned() {
        //Given
        final List<Film> films = new ArrayList<>();
        films.add(new Film(1L, "Title 1", Genre.COMEDY, 150));
        films.add(new Film(2L, "Title 2", Genre.DRAMA, 186));
        final StreamingService streamingService = new StreamingService(films, new ArrayList<>());

        //When
        final Map<User, List<Film>> result = streamingService.getFullyWatchedFilms();

        //Then
        assertThat(result).isEmpty();
    }

    @Test
    public void givenPlatformUsersWhoDidNotWatchAnything_whenGetFullyWatchedFilms_thenEntryValuesAreEmptyLists() {
        //Given
        final List<Film> films = new ArrayList<>();
        films.add(new Film(1L, "Title 1", Genre.COMEDY, 150));
        films.add(new Film(2L, "Title 2", Genre.DRAMA, 186));
        final List<User> users = new ArrayList<>();
        users.add(new User("Sorin", 19, UserType.STANDARD));
        users.add(new User("Angelica", 28, UserType.PREMIUM));
        users.add(new User("Ionela", 39, UserType.FAMILY));
        final StreamingService streamingService = new StreamingService(films, users);

        //When
        final Map<User, List<Film>> map = streamingService.getFullyWatchedFilms();

        //Then
        assertThat(map).isNotNull();
        assertThat(map.keySet()).hasSize(3);
        map.forEach((key, value) -> {
            assertThat(key).isNotNull();
            assertThat(value).isEmpty();
        });
    }

    @Test
    public void givenPlatformUsersWhoWatchedFilms_whenGetFullyWatchedFilms_thenCorrectResultsRetrieved() {
        //Given
        final List<Film> films = new ArrayList<>();
        final Film film1 = new Film(1L, "Title 1", Genre.COMEDY, 150);
        final Film film2 = new Film(2L, "Title 2", Genre.DRAMA, 186);
        films.add(film1);
        films.add(film2);

        final List<User> users = new ArrayList<>();
        final User user1 = new User("Sorin", 19, UserType.STANDARD);
        final User user2 = new User("Angelica", 28, UserType.PREMIUM);
        final User user3 = new User("Ionela", 39, UserType.FAMILY);

        user1.watchFilm(film1, 150);
        user1.watchFilm(film2, 186);

        user2.watchFilm(film1, 150);
        user2.watchFilm(film2, 37);

        user3.watchFilm(film1, 53);
        user3.watchFilm(film2, 77);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        final StreamingService streamingService = new StreamingService(films, users);

        //When
        final Map<User, List<Film>> map = streamingService.getFullyWatchedFilms();

        //Then
        assertThat(map).isNotNull();
        assertThat(map.keySet()).hasSize(3);
        map.forEach((key, value) -> {
            assertThat(key).isNotNull();
        });

        assertThat(map).containsKey(user1);
        final List<Film> user1Films = map.get(user1);
        assertThat(user1Films).isNotEmpty();
        assertThat(user1Films).hasSize(2);
        assertThat(user1Films).containsExactlyInAnyOrder(film1, film2);

        assertThat(map).containsKey(user2);
        final List<Film> user2Films = map.get(user2);
        assertThat(user2Films).isNotEmpty();
        assertThat(user2Films).hasSize(1);
        assertThat(user2Films).containsExactly(film1);

        assertThat(map).containsKey(user3);
        assertThat(map.get(user3)).isEmpty();
    }
}
