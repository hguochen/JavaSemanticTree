package concepts.functional;

import java.util.function.Function;

/**
 * Here we override the functional interface by giving the only abstract method 'apply' a concrete implementation.
 */
public class FunctionalImplDemo implements Function<Long, Long> {
    @Override
    public Long apply(Long aLong) {
        return aLong + 3;
    }
}
