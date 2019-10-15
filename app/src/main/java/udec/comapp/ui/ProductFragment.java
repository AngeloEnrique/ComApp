package udec.comapp.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import udec.comapp.Producto;
import udec.comapp.R;
import udec.comapp.vendedor;

public class ProductFragment extends Fragment {


    String[] productos = {"Completo","Churrasco","Carnecita","Morrones","Lechuga"};

    int[] imagenes = {R.mipmap.ic_l,R.mipmap.ic_churrasco};

    String[] precios = {"$ 1 000","$ 1 500","$ 2 000","$ 2 000","$ 500"};
    Boolean[] disponible= {true, false, true, false, true};
    Boolean[] endescuento={false, false, true, false, false};
    String[] descuentos={"","","$ 1 000","",""};
    private boolean autenticado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.ven_productos, container, false);


        ListView listview = (ListView) view.findViewById(R.id.listview_prod);

        CustomAdapter customAdapter = new CustomAdapter();

        vendedor act = (vendedor) getActivity();
        autenticado = act.getAutenticado();

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                productos
        );

        //listview.setAdapter(listViewAdapter);
        listview.setAdapter(customAdapter);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                    Intent myIntent = new Intent(view.getContext(), Producto.class);

                    myIntent.putExtra("name",productos[position]);
                    myIntent.putExtra("price",precios[position]);
                    myIntent.putExtra("availability",disponible[position]);
                    myIntent.putExtra("discounted",endescuento[position]);
                    myIntent.putExtra("discounted",endescuento[position]);
                    myIntent.putExtra("discount",descuentos[position]);
                    myIntent.putExtra("autenticado", autenticado);
                    startActivityForResult(myIntent, 0);
            }
        });


        return view;
    }
    class CustomAdapter extends BaseAdapter{

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
            convertView = getLayoutInflater().inflate(R.layout.ven_customlayout,null);
            ImageView imageView = convertView.findViewById(R.id.ven_item_image);
            TextView textView = convertView.findViewById(R.id.ven_item_name);
            TextView textView1 = convertView.findViewById(R.id.ven_item_price);
            TextView descontado =  convertView.findViewById(R.id.ven_item_price_discount);

            imageView.setImageResource(imagenes[0]);
            textView.setText(productos[position]);
            if(!disponible[position]){
                String aux = (String) textView.getText();
                textView.setText(aux +" "+getResources().getString(R.string.nodisponible));
                textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                convertView.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
            }
            textView1.setText(precios[position]);
            if(endescuento[position]){
                textView1.setPaintFlags(textView1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                descontado.setText(descuentos[position]);
                descontado.setTextColor(getResources().getColor(R.color.colorAccent));

            }
            //ACA ALGO SOBRE LA DISPONIBILIDAD
            return convertView;
        }
    }
}












