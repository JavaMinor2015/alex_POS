package javaminor.util;

import lombok.Getter;

/**
 * Created by alex on 9/10/15.
 */
public class PreferenceUtil {
    // TODO fix properly

    @Getter
    private static final String[] PRICING_CATEGORIES = new String[]{"ECO","Meal"};

    @Getter
    private static final String CATEGORY_KEY_NAME = "type";
    public static final String BILL_SALE_DESCRIPTION = "========Bill of Sale=======";

    public final static String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    public final static String USER_ID = "alex";

    // ooooh, a password
    public final static String PASSWORD = "oracle";
}
