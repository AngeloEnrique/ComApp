package udec.comapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import udec.comapp.R;

public class ProfileFragment extends Fragment {    private String name;
    private String vname;
    private String vdescr;
    private Boolean vdisp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vname = "Tío Chaparra";
        vdescr = "Ricas chaparritas 0% carne de can";
        vdisp = true;

        View view = inflater.inflate(R.layout.ven_perfil, container, false);
        TextView name = view.findViewById(R.id.prod_name);
        TextView desc = view.findViewById(R.id.prod_price);
        TextView disp = view.findViewById(R.id.prod_availability);
        name.setText(vname);
        desc.setText(vdescr);
        if(vdisp) disp.setText("Disponible");
        else disp.setText("No Disponible");


        return view;
    }

}












