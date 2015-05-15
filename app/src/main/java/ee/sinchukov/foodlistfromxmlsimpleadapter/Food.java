package ee.sinchukov.foodlistfromxmlsimpleadapter;

/**
 * Created by user_39 on 14.05.2015.
 */

import java.util.HashMap;

public class Food extends HashMap<String, String> {

    public static final String NAME = "name";
    public static final String FOOD_ID = "id";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String CALORIES = "calories";



    public Food(String name, String id, String price, String description,  String calories) {
        super();
        super.put(NAME, name);
        super.put(FOOD_ID, id);
        super.put(PRICE, price);
        super.put(DESCRIPTION, description);
        super.put(CALORIES, calories);
    }

    public String getId(){
        return super.get(FOOD_ID);
    }
    public String getName(){
        return super.get(NAME);
    }
}