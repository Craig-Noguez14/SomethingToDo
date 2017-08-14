package Helpers;

import java.util.Arrays;

/**
 * Created by Craig on 8/12/2017.
 */

public class Utils {
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }
}
