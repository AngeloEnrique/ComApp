package udec.comapp.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import udec.comapp.DBHelper;
import udec.comapp.Listas;
import udec.comapp.MainActivity;
import udec.comapp.Producto;
import udec.comapp.R;
import udec.comapp.vendedor;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private int sector;
    private LayoutInflater inflater;
    private ViewGroup container;
    private ViewGroup flContent;

    /*
    private String[] vendForo = {"Viviana","Marcelo","Random", "Random2"};
    private String[] vendCentral = {"Tio de la Burger","Tio de las Pizzas"};
    private String[] vendEduca = {"Tia de las palomitas","Tio de la Comida Vegana"};

    private String[] descForo = {"Vende sus wenos sanguches","Vende sus wenas trufas","Random", "Random2"};
    private String[] descCentral = {"Las mejores hamburguesas de Shile","No hay mejores piczas"};
    private String[] descEduca = {"Mejores palomitas","Comida libre de animales"};
*/

    private int[] imagenes = {R.drawable.perfil_logo};
    private List<String> vendForo = new ArrayList<String>();
    private List<String> vendCentral = new ArrayList<String>();
    private List<String> vendEduca =new ArrayList<String>();

    private List<String> descForo = new ArrayList<String>();
    private List<String> descCentral = new ArrayList<String>();
    private List<String> descEduca = new ArrayList<String>();

    private List<Boolean> disponible = new ArrayList<Boolean>();

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

        DBHelper admin = new DBHelper(getActivity(),"vendedores",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor vend = admin.getAllVend(db);
        if(vend.moveToFirst()){
            do{
                if(vend.getString(4).equals("Educa")){
                    vendEduca.add(vend.getString(2));
                    descEduca.add(vend.getString(5));
                }
                else if(vend.getString(4).equals("Foro")){
                    vendForo.add(vend.getString(2));
                    descForo.add(vend.getString(5));
                }
                else if(vend.getString(4).equals("Central")){
                    vendCentral.add(vend.getString(2));
                    descCentral.add(vend.getString(5));
                }
                if(vend.getInt(3)==1) disponible.add(true);
                else disponible.add(false);
            }while(vend.moveToNext());
        }

        Button foro = (Button) root.findViewById(R.id.foroButton);
        Button central = root.findViewById(R.id.centralButton);
        Button educa = root.findViewById(R.id.educaButton);

        flContent = root.findViewById(R.id.ll_content);
        foro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("boton foro", "ME APRETAAAN");
                sector = 0;
                setLayout();

            }
        });
        central.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("boton central", "ME APRETAAAN");
                sector = 1;
                setLayout();

            }
        });
        educa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("boton educa", "ME APRETAAAN");
                sector = 2;
                setLayout();

            }
        });
        db.close();
        return root;
    }

    private void setLayout() {
        flContent.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.fragment_listas, flContent, false);
        ListView listview = (ListView) view.findViewById(R.id.listview_vend);
        CustomAdapter customAdapter = new CustomAdapter();
        ArrayAdapter<String> listViewAdapter;
        switch (sector){
            case 0:
                listViewAdapter = new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        vendForo
                );
                break;
            case 1:
                listViewAdapter = new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        vendCentral
                );
                break;
            default:
                listViewAdapter = new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        vendEduca
                );
                break;

        }
        listview.setAdapter(customAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent myIntent = new Intent(view.getContext(), vendedor.class);
                myIntent.putExtra("autenticado", Boolean.FALSE);
                switch(sector){
                    case 0:
                        myIntent.putExtra("name",vendForo.get(position));
                        break;
                    case 1:
                        myIntent.putExtra("name",vendCentral.get(position) );
                        break;
                    default:
                        myIntent.putExtra("name",vendEduca.get(position) );
                        break;
                }
                startActivityForResult(myIntent, 0);
            }
        });
        flContent.addView(view);
    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(sector == 0) {
                return vendForo.size();
            }else if(sector == 1) {
                return vendCentral.size();
            }else{
                return vendEduca.size();
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.list_vendedor,null);
            ImageView imageView=(ImageView) convertView.findViewById(R.id.ven_item_image);
            TextView textView=(TextView) convertView.findViewById(R.id.ven_item_name);
            TextView textView1=(TextView) convertView.findViewById(R.id.ven_item_price);

            imageView.setImageResource(imagenes[0]);
            switch(sector){
                case 0:
                    textView.setText(vendForo.get(position));
                    textView1.setText(descForo.get(position));

                    break;
                case 1:
                    textView.setText(vendCentral.get(position));
                    textView1.setText(descCentral.get(position));
                    break;
                default:
                    textView.setText(vendEduca.get(position));
                    textView1.setText(descEduca.get(position));
                    break;

            }
            if(!disponible.get(position)) {
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                convertView.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
            }
            return convertView;
        }
    }

}