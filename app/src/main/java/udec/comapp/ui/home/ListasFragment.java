package udec.comapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import udec.comapp.R;
import udec.comapp.ui.dashboard.DashboardViewModel;

public class ListasFragment extends Fragment {


    String[] productos = {"Completo","Churrasco","Carnecita","Morrones","Morrones","Morrones","Morrones"};

    //int[] imagenes = {R.mipmap.ic_l};

    String[] precios = {"$ 1 000","$ 1 500","$ 2 000","$ 2 000","$ 2 000","$ 2 000","$ 2 000"};
    Boolean[] disponible= {true, false, true, false, true ,false ,true};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_listas, container, false);


        ListView listview = (ListView) view.findViewById(R.id.listview_vend);

        CustomAdapter customAdapter = new CustomAdapter();



        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                productos
        );

        //listview.setAdapter(listViewAdapter);
        listview.setAdapter(customAdapter);



        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent myIntent = new Intent(view.getContext(), Producto.class);
                myIntent.putExtra("name",productos[position]);
                myIntent.putExtra("price",precios[position]);
                myIntent.putExtra("availability",disponible[position]);
                startActivityForResult(myIntent, 0);
            }
        });*/


        return view;
    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return productos.length;
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

            //imageView.setImageResource(imagenes[0]);
            textView.setText(productos[position]);
            textView1.setText(precios[position]);
            //ACA ALGO SOBRE LA DISPONIBILIDAD
            return convertView;
        }
    }
}