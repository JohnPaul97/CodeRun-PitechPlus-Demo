import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.User;
import domain.UserType;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamingServiceTests extends UnitTest {

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
    public void givenNoPlatformUsers_whenGetPlatformRevenue_thenZeroIsReturned() {
        //Given
        final StreamingService streamingService = new StreamingService(new ArrayList<>(), new ArrayList<>());

        //When
        final double revenue = streamingService.getPlatformRevenue();

        //Then
        assertThat(revenue).isEqualTo(0);
    }
}
