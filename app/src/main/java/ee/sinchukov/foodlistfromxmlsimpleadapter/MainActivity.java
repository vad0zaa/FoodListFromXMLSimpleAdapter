package ee.sinchukov.foodlistfromxmlsimpleadapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;



public class MainActivity extends ListActivity  {

    ArrayList<Food> foodsList = new ArrayList<Food>();

    String name;
    String price;
    String calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        try {
            XmlPullParser foodsParser = getResources().getXml(R.xml.foods);
            int eventType = foodsParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType==XmlPullParser.START_TAG) {
                    if (foodsParser.getName().equals("name")) {
                        foodsParser.next();
                        name=foodsParser.getText();
                    }
                    else if (foodsParser.getName().equals("price")) {
                        foodsParser.next();
                        price=foodsParser.getText();
                    }
                    else if (foodsParser.getName().equals("calories")) {
                        foodsParser.next();
                        calories=foodsParser.getText();
                    }
                }
                if (eventType==XmlPullParser.END_TAG && foodsParser.getName().equals("food")) {
                    foodsList.add(new Food(name,price,calories));
                }

                eventType = foodsParser.next();
            }
        }
        catch (Throwable t) {
            Toast.makeText(this, "Error XML-file loading: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }


        String[] from=new String[] { Food.NAME, Food.PRICE, Food.CALORIES };
        int[] to=new int[] {R.id.nameView, R.id.priceView, R.id.caloriesView };

        ListAdapter adapter = new SimpleAdapter(this, foodsList, R.layout.activity_main,from,to);
        setListAdapter(adapter);

    }

}