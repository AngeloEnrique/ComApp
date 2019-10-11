package udec.comapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import udec.comapp.ui.EditProfFragment;
import udec.comapp.ui.ProductFragment;
import udec.comapp.ui.ProfileFragment;

import static udec.comapp.R.menu.ven_prod;

public class vendedor extends AppCompatActivity {
    private TextView tname;
    private TextView tprice;
    private TextView tavailability;
    private EditText inname;
    private EditText inprice;
    private Switch svail;
    private Menu menu;
    private boolean autenticado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);
        BottomNavigationView bottonNav = findViewById(R.id.bottom_ven_navigation);
        bottonNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.ven_fragment_container,
                new ProductFragment()).commit();

        autenticado = getIntent().getExtras().getBoolean("autenticado");
/*
        tname = (TextView) findViewById(R.id.prod_name);
        tprice = (TextView) findViewById(R.id.prod_price);
        tavailability = (TextView) findViewById(R.id.prod_availability);
        inname = (EditText) findViewById(R.id.in_name);
        inprice = (EditText) findViewById(R.id.in_des);
        svail = (Switch) findViewById(R.id.sw_avail);

        inprice.setVisibility(View.INVISIBLE);
        inprice.setVisibility(View.INVISIBLE);
        svail.setVisibility(View.INVISIBLE);
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(ven_prod, menu);
        menu.findItem(R.id.appbar_aceptar).setVisible(false);
        menu.findItem(R.id.appbar_cancelar).setVisible(false);
        menu.findItem(R.id.appbar_edit).setVisible(false);
        menu.findItem(R.id.appbar_oferta).setVisible(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Fragment selectedFragment;
        switch (item.getItemId()) {
            case R.id.appbar_edit:
                menu.findItem(R.id.appbar_edit).setVisible(false);
                menu.findItem(R.id.appbar_oferta).setVisible(false);
                menu.findItem(R.id.appbar_aceptar).setVisible(true);
                menu.findItem(R.id.appbar_cancelar).setVisible(true);
                selectedFragment = new EditProfFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.ven_fragment_container,
                        selectedFragment).commit();

                return true;
            case R.id.appbar_aceptar:
            case R.id.appbar_cancelar:
                menu.findItem(R.id.appbar_edit).setVisible(true);
                menu.findItem(R.id.appbar_aceptar).setVisible(false);
                menu.findItem(R.id.appbar_cancelar).setVisible(false);
                selectedFragment = new ProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.ven_fragment_container,
                        selectedFragment).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    boolean back = false;
                    switch (menuItem.getItemId()){
                        case R.id.nav_ven_products:
                            menu.findItem(R.id.appbar_aceptar).setVisible(false);
                            menu.findItem(R.id.appbar_cancelar).setVisible(false);
                            menu.findItem(R.id.appbar_edit).setVisible(false);
                            selectedFragment = new ProductFragment();
                            break;
                        case R.id.nav_ven_profile:
                            menu.findItem(R.id.appbar_aceptar).setVisible(false);
                            menu.findItem(R.id.appbar_cancelar).setVisible(false);
                            menu.findItem(R.id.appbar_edit).setVisible(autenticado);
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.nav_ven_exit:
                            menu.findItem(R.id.appbar_aceptar).setVisible(false);
                            menu.findItem(R.id.appbar_cancelar).setVisible(false);
                            menu.findItem(R.id.appbar_edit).setVisible(false);
                            back = true;
                            break;
                    }
                    if(back){
                        finish();
                    }else{
                        if(selectedFragment!=null){
                            getSupportFragmentManager().beginTransaction().replace(R.id.ven_fragment_container,
                                    selectedFragment).commit();
                        }
                    }
                    return !back;
                }
            };


    public boolean getAutenticado(){
        return autenticado;
    }
}































