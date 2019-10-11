package udec.comapp;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import static udec.comapp.R.menu.ven_prod;

public class Producto extends AppCompatActivity {
    private String name;
    private String price;
    private Boolean availability;
    private TextView tname;
    private TextView tprice;
    private TextView tavailability;
    private Menu menu;

    private EditText inname;
    private EditText inprice;
    private Switch svail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = getIntent().getExtras().getString("name");
        price = getIntent().getExtras().getString("price");
        availability = getIntent().getExtras().getBoolean("availability");

        tname = findViewById(R.id.prod_name);
        tprice = findViewById(R.id.prod_price);
        tavailability = findViewById(R.id.prod_availability);
        inname = findViewById(R.id.in_name);
        inprice = findViewById(R.id.in_price);
        svail = findViewById(R.id.sw_avail);

        tname.setText(name);
        tprice.setText(price);
        inname.setHint(name);
        inprice.setHint(price);
        svail.setChecked(availability);

        inname.setVisibility(View.INVISIBLE);
        inprice.setVisibility(View.INVISIBLE);
        svail.setVisibility(View.INVISIBLE);


        if(availability)tavailability.setText(R.string.disponible);
        else tavailability.setText(R.string.no_disponible);



        tname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(tname.getText().toString());

                return true;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(ven_prod, menu);
        menu.findItem(R.id.appbar_aceptar).setVisible(false);
        menu.findItem(R.id.appbar_cancelar).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.appbar_edit:
                inname.setVisibility(View.VISIBLE);
                inprice.setVisibility(View.VISIBLE);
                svail.setVisibility(View.VISIBLE);
                tname.setVisibility(View.INVISIBLE);
                tprice.setVisibility(View.INVISIBLE);
            case R.id.appbar_oferta:
                menu.findItem(R.id.appbar_edit).setVisible(false);
                menu.findItem(R.id.appbar_oferta).setVisible(false);
                menu.findItem(R.id.appbar_aceptar).setVisible(true);
                menu.findItem(R.id.appbar_cancelar).setVisible(true);
                return true;
            case R.id.appbar_aceptar:
            case R.id.appbar_cancelar:
                menu.findItem(R.id.appbar_oferta).setVisible(true);
                menu.findItem(R.id.appbar_edit).setVisible(true);
                menu.findItem(R.id.appbar_aceptar).setVisible(false);
                menu.findItem(R.id.appbar_cancelar).setVisible(false);
                inname.setVisibility(View.INVISIBLE);
                inprice.setVisibility(View.INVISIBLE);
                svail.setVisibility(View.INVISIBLE);
                tname.setVisibility(View.VISIBLE);
                tprice.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void showDialog(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.text_confirm_new_prod_name));
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_view, null);
        final EditText edit_dialog = view.findViewById(R.id.edit_dialog);
        edit_dialog.setText(str);
        builder.setView(view);
        builder.setNegativeButton(getResources().getString(R.string.cancelar),null);
        builder.setPositiveButton(getResources().getString(R.string.confirmar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tname.setText(edit_dialog.getText().toString());
            }
        });
        builder.show();
    }

}




















