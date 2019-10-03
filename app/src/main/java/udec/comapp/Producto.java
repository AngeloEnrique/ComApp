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
import android.widget.TextView;

import static udec.comapp.R.menu.ven_prod;

public class Producto extends AppCompatActivity {
    private String name;
    private String price;
    private TextView tname;
    private TextView tprice;
    private Menu menu;
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
                menu.findItem(R.id.appbar_oferta).setVisible(false);
                menu.findItem(R.id.appbar_edit).setVisible(false);
                menu.findItem(R.id.appbar_aceptar).setVisible(true);
                menu.findItem(R.id.appbar_cancelar).setVisible(true);
                return true;
            case R.id.appbar_oferta:
                menu.findItem(R.id.appbar_oferta).setVisible(false);
                menu.findItem(R.id.appbar_edit).setVisible(false);
                menu.findItem(R.id.appbar_aceptar).setVisible(true);
                menu.findItem(R.id.appbar_cancelar).setVisible(true);
                return true;
            case R.id.appbar_aceptar:
                menu.findItem(R.id.appbar_oferta).setVisible(true);
                menu.findItem(R.id.appbar_edit).setVisible(true);
                menu.findItem(R.id.appbar_aceptar).setVisible(false);
                menu.findItem(R.id.appbar_cancelar).setVisible(false);
                return true;
            case R.id.appbar_cancelar:
                menu.findItem(R.id.appbar_oferta).setVisible(true);
                menu.findItem(R.id.appbar_edit).setVisible(true);
                menu.findItem(R.id.appbar_aceptar).setVisible(false);
                menu.findItem(R.id.appbar_cancelar).setVisible(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void showDialog(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.text_confirm_new_prod_name));
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_view, null);
        final EditText edit_dialog = (EditText) view.findViewById(R.id.edit_dialog);
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




















