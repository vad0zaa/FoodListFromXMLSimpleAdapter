package ee.sinchukov.foodlistfromxmlsimpleadapter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class InfoActivity extends ActionBarActivity {

    Bundle extras;
    TextView textView;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        textView = (TextView)findViewById(R.id.textView);

        // get extras from Main Screen
        extras = getIntent().getExtras();
        id = extras.getString(MainActivity.EXTRA_FOOD_ID);

        // parse food xml by id and show food info in textView
        FoodXmlParser foodXmlParser = new FoodXmlParser();
        XmlPullParser foodsParser = getResources().getXml(R.xml.foods);
        try {
            String info;
            info = foodXmlParser.getFoodInfoById(foodsParser,id);
            textView.setText(info);
        }
        catch (Throwable t) {
                Toast.makeText(this, "Error XML-file loading: " + t.toString(), Toast.LENGTH_LONG)
                        .show();
        }
    }

    public void buttonClick(View view){
        Toast.makeText(getApplicationContext(), "вы заказали товар нр." + id ,
                Toast.LENGTH_SHORT).show();
    }


















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
