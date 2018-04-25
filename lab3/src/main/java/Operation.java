import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Operation {
    put,
    withdraw;

    private static final List<Operation> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Operation getOperation()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
