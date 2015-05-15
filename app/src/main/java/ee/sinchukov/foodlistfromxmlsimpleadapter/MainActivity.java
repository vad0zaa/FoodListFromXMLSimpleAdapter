package ee.sinchukov.foodlistfromxmlsimpleadapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;



public class MainActivity extends ListActivity  {

    ArrayList<Food> foodsList = new ArrayList<Food>();
    public static final String EXTRA_FOOD_ID = "ee.sinchukov.foodlistfromxmlsimpleadapter.FOOD_ID";
    public static final String TAG = "MainActivity";

    String name;
    String price;
    String calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FoodXmlParser foodXmlParser = new FoodXmlParser();

        try {
            XmlPullParser foodsParser = getResources().getXml(R.xml.foods);
            foodsList = foodXmlParser.getFoodArray(foodsParser);
        }
        catch (Throwable t) {
            Toast.makeText(this, "Error XML-file loading: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }

        String[] from=new String[] { Food.NAME, Food.PRICE, Food.CALORIES };
        int[] to=new int[] {R.id.nameView, R.id.priceView, R.id.caloriesView };

        ListAdapter adapter = new SimpleAdapter(this, foodsList, R.layout.activity_main,from,to);
        setListAdapter(adapter);


        // click
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // получаем выбранный пункт
                Food selectedFood = (Food)parent.getItemAtPosition(position);
                // show info screen
                Log.v(TAG, "show info activity");
                showInfo(selectedFood.getId());
            }
        };
        getListView().setOnItemClickListener(itemListener);

    }

    protected void showInfo(String foodId){
        Intent intent = new Intent(this,InfoActivity.class);

        // pass food id to info screen activity
        intent.putExtra(MainActivity.EXTRA_FOOD_ID, foodId);
        Log.v(TAG, "put EXTRA_FOOD_ID:"+ foodId);

        //show info screen
        startActivity(intent);
    }

}