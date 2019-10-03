package udec.comapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Product extends AppCompatActivity {
    String name;
    String price;

    TextView tname;
    TextView tprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name=getIntent().getExtras().getString("name");
        price=getIntent().getExtras().getString("price");

        tname=(TextView) findViewById(R.id.prod_name);
        tprice=(TextView) findViewById(R.id.prod_price);

        tname.setText(name);
        tprice.setText(price);
    }

}




















