import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.*;

import static java.util.Optional.ofNullable;

public class StreamingService {

    private List<Film> filmLibrary;
    private List<User> users;

    public StreamingService(final List<Film> films, final List<User> users) {
        this.filmLibrary = films;
        this.users = users;
    }

    public double getPlatformRevenue() {
        return ofNullable(users)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(User::getPaymentPlanValue)
                .reduce(0.0, Double::sum);
    }

    public double getDiscountImpact(final int discountPercentage, final UserType userType) {
        if (discountPercentage <= 0) {
            return 0;
        }
        final double fullValue = getPlatformRevenue(userType);
        if (discountPercentage >= 100) {
            return fullValue;
        }
        return discountPercentage * fullValue / 100.0;
    }

    public Map<User, List<Film>> getFullyWatchedFilms() {
        return ofNullable(users).orElseGet(Collections::emptyList)
                .stream()
                .collect(Collectors.toMap(Function.identity(), User::getListOfFullyWatchedFilms));
    }

    private double getPlatformRevenue(final UserType userType) {
        return ofNullable(users)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(user -> user.getUserType().equals(userType))
                .map(User::getPaymentPlanValue)
                .reduce(0.0, Double::sum);
    }
}
