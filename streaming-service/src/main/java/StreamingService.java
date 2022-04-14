import java.util.List;
import java.util.Map;

import domain.*;

public class StreamingService {

    private List<Film> filmLibrary;
    private List<User> users;

    public StreamingService(List<Film> filmLibrary, List<User> users) {
        this.filmLibrary = filmLibrary;
        this.users = users;
    }

    public double getPlatformRevenue() {
        return this.users.stream()
                .map(User::getPaymentPlanValue)
                .reduce(0.0, Double::sum);
    }

    public double getDiscountImpact(final int discountPercentage, final UserType userType) {
        //TODO: calculate the amount that could be discounted based on the percentage given and the userType it is applied for
        return 0;
    }

    public Map<User, List<Film>> getFullyWatchedFilms() {
        // TODO: get a statistic of all the films that were fully watched by the users
        return null;
    }

    private double getPlatformRevenue(final UserType userType) {
        //TODO: retrieve the total value that the company earns for this userType
        return 0;
    }
}
