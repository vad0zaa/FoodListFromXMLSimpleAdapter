package ee.sinchukov.foodlistfromxmlsimpleadapter;

import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by vsinchuk on 5/15/2015.
 */
public class FoodXmlParser {

    String name;
    String id;
    String price;
    String description;
    String calories;

    String newLine = System.getProperty("line.separator");


    public String getFoodInfoById(XmlPullParser foodsParser, String foodId) throws XmlPullParserException, IOException {

        String currentFoodId;
        StringBuilder foodInformation = new StringBuilder();

        // parse,   find food by id
        int eventType = foodsParser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType==XmlPullParser.START_TAG && foodsParser.getName().equals("name") ) {
                currentFoodId = foodsParser.getAttributeValue(0);
                if (currentFoodId.equals(foodId)) {
                    foodsParser.next();
                    foodInformation.append(foodsParser.getText() +newLine);
                    foodsParser.next();
                    foodsParser.next();
                    foodsParser.next();
                    foodInformation.append("Price: " + foodsParser.getText() +newLine +newLine);
                    foodsParser.next();
                    foodsParser.next();
                    foodsParser.next();
                    foodInformation.append( foodsParser.getText() +newLine);
                    foodsParser.next();
                    foodsParser.next();
                    foodsParser.next();
                    foodInformation.append("Calories: " + foodsParser.getText());
                }
            }
            eventType = foodsParser.next();
        }
        return foodInformation.toString();
    }

    public ArrayList<Food> getFoodArray(XmlPullParser foodsParser) throws XmlPullParserException, IOException {
        ArrayList<Food> foodsList = new ArrayList<Food>();
        int eventType = foodsParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType==XmlPullParser.START_TAG) {
                    if (foodsParser.getName().equals("name")) {
                        id=foodsParser.getAttributeValue(0);
                        foodsParser.next();
                        name=foodsParser.getText();
                    }
                    else if (foodsParser.getName().equals("price")) {
                        foodsParser.next();
                        price=foodsParser.getText();
                    }
                    else if (foodsParser.getName().equals("description")) {
                        foodsParser.next();
                        description=foodsParser.getText();
                    }
                    else if (foodsParser.getName().equals("calories")) {
                        foodsParser.next();
                        calories=foodsParser.getText();
                    }
                }
                if (eventType==XmlPullParser.END_TAG && foodsParser.getName().equals("food")) {
                    foodsList.add(new Food(name,id,price,description,calories));
                }

                eventType = foodsParser.next();
            }

        return foodsList;
    }
}
