package udec.comapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import udec.comapp.Listas;
import udec.comapp.MainActivity;
import udec.comapp.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    LayoutInflater inflater;
    ViewGroup container;
    ViewGroup flContent;


    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel =
        //        ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        Button foro = (Button) root.findViewById(R.id.foroButton);
        flContent = root.findViewById(R.id.ll_content);
        foro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("boton foro", "ME APRETAAAN");
                setLayout();

            }
        });
        return root;
    }

    public void swaptToForo(){
        ListasFragment newListfragment = new ListasFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ll_content, newListfragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void setLayout() {
        flContent.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.fragment_listas, flContent, false);
        flContent.addView(view);
    }

}