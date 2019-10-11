package udec.comapp;


import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import static udec.comapp.R.menu.ven_prod;

public class Producto extends AppCompatActivity {
    private String name;
    private String price;
    private Boolean availability;
    private Boolean discounted;
    private String discount;
    private TextView tname;
    private TextView tprice;
    private TextView tavailability;
    private Menu menu;
    private TextView disct;
    private EditText inname;
    private EditText inprice;
    private Switch svail;
    private TextInputEditText inoferta;

    private ConstraintLayout promo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = getIntent().getExtras().getString("name");
        price = getIntent().getExtras().getString("price");
        availability = getIntent().getExtras().getBoolean("availability");
        discounted = getIntent().getExtras().getBoolean("discounted");
        discount = getIntent().getExtras().getString("discount");

        tname = findViewById(R.id.prod_name);
        tprice = findViewById(R.id.prod_price);
        tavailability = findViewById(R.id.prod_availability);
        inname = findViewById(R.id.in_name);
        inprice = findViewById(R.id.in_price);
        svail = findViewById(R.id.sw_avail);

        promo = findViewById(R.id.promo);

        tname.setText(name);
        tprice.setText(price);
        inname.setHint(name);
        inprice.setHint(price);
        svail.setChecked(availability);

        inname.setVisibility(View.INVISIBLE);
        inprice.setVisibility(View.INVISIBLE);
        svail.setVisibility(View.INVISIBLE);

        promo.setVisibility(View.INVISIBLE);

        disct = findViewById(R.id.discounted);

        inoferta = findViewById(R.id.in_price_ofert);

        if(availability)tavailability.setText(R.string.disponible);
        else tavailability.setText(R.string.no_disponible);

        if(discounted){
            tprice.setPaintFlags(tprice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            promo.setVisibility(View.VISIBLE);
            findViewById(R.id.in_price_ofert).setVisibility(View.INVISIBLE);
            disct.setVisibility(View.VISIBLE);
            disct.setText(discount);

        }


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
                if(discounted)promo.setVisibility(View.VISIBLE);
                else promo.setVisibility(View.INVISIBLE);
                menu.findItem(R.id.appbar_edit).setVisible(false);
                menu.findItem(R.id.appbar_oferta).setVisible(false);
                menu.findItem(R.id.appbar_aceptar).setVisible(true);
                menu.findItem(R.id.appbar_cancelar).setVisible(true);
                return true;
            case R.id.appbar_oferta:
                String aux = disct.getText().toString();
                menu.findItem(R.id.appbar_edit).setVisible(false);
                menu.findItem(R.id.appbar_oferta).setVisible(false);
                menu.findItem(R.id.appbar_aceptar).setVisible(true);
                menu.findItem(R.id.appbar_cancelar).setVisible(true);
                promo.setVisibility(View.VISIBLE);
                disct.setVisibility(View.INVISIBLE);
                inoferta.setHint(aux);
                inoferta.setVisibility(View.VISIBLE);
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
                inoferta.setVisibility(View.INVISIBLE);
                if(discounted){
                    promo.setVisibility(View.VISIBLE);
                    disct.setVisibility(View.VISIBLE);
                }
                else {
                    promo.setVisibility(View.INVISIBLE);
                    disct.setVisibility(View.INVISIBLE);
                }
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




















