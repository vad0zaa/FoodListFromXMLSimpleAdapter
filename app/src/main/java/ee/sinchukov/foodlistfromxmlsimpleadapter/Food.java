package ee.sinchukov.foodlistfromxmlsimpleadapter;

/**
 * Created by user_39 on 14.05.2015.
 */

import java.util.HashMap;

public class Food extends HashMap<String, String> {

    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String CALORIES = "calories";


    public Food(String name, String price, String calories) {
        super();
        super.put(NAME, name);
        super.put(PRICE, price);
        super.put(CALORIES, calories);
    }
}