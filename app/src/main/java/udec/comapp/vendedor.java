package udec.comapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import udec.comapp.ui.ProductFragment;
import udec.comapp.ui.ProfileFragment;
import udec.comapp.ui.home.HomeFragment;

public class vendedor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);
        BottomNavigationView bottonNav = findViewById(R.id.bottom_ven_navigation);
        bottonNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.ven_fragment_container,
                new ProductFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    boolean back = false;
                    switch (menuItem.getItemId()){
                        case R.id.nav_ven_products:
                            selectedFragment = new ProductFragment();
                            break;
                        case R.id.nav_ven_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.nav_ven_exit:
                            back = true;
                            break;
                    }
                    if(back){
                        finish();
                    }else{
                        getSupportFragmentManager().beginTransaction().replace(R.id.ven_fragment_container,
                                selectedFragment).commit();
                    }
                    return !back;
                }
            };
}































