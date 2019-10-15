package udec.comapp.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;
import java.util.List;

import udec.comapp.DBHelper;
import udec.comapp.Producto;
import udec.comapp.R;
import udec.comapp.vendedor;

public class ProductFragment extends Fragment {


    int[] imagenes = {R.mipmap.ic_l};

    /*String[] productos = {"Completo","Churrasco","Carnecita","Morrones","Lechuga"};
    String[] precios = {"$ 1 000","$ 1 500","$ 2 000","$ 2 000","$ 500"};
    Boolean[] disponible= {true, false, true, false, true};
    Boolean[] endescuento={false, false, true, false, false};
    String[] descuentos={"","","$ 1 000","",""};
    */
    private boolean autenticado;

    List<String> productos = new ArrayList<String>();
    List<String> precios =  new ArrayList<String>();
    List<Boolean> disponible =  new ArrayList<Boolean>();
    List<Boolean> endescuento =  new ArrayList<Boolean>();
    List<String> descuentos = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.ven_productos, container, false);
        DBHelper admin = new DBHelper(getActivity(),"Base",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor prod = admin.getAllProductos(db);

        if(prod.moveToFirst()){
            do{
                productos.add(prod.getString(1));
                precios.add(prod.getString(2));
                if(prod.getInt(3)==1) disponible.add(true);
                else disponible.add(false);
                if(prod.getInt(4)==1) {endescuento.add(true);
                descuentos.add("1000");
                }
                else{ endescuento.add(false);
                descuentos.add("");
                }

            }while(prod.moveToNext());
        }


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

                    myIntent.putExtra("name",productos.get(position));
                    myIntent.putExtra("price",precios.get(position) );
                    myIntent.putExtra("availability",disponible.get(position));
                    myIntent.putExtra("discounted",endescuento.get(position));
                    myIntent.putExtra("discounted",endescuento.get(position));
                    myIntent.putExtra("discount",descuentos.get(position) );
                    myIntent.putExtra("autenticado", autenticado);
                    startActivityForResult(myIntent, 0);
            }
        });

        db.close();
        return view;
    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return productos.size();
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
            textView.setText(productos.get(position));
            if(!disponible.get(position)){
                String aux = (String) textView.getText();
                textView.setText(aux +" "+getResources().getString(R.string.nodisponible));
                textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                convertView.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
            }
            textView1.setText(precios.get(position));
            if(endescuento.get(position)){
                textView1.setPaintFlags(textView1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                descontado.setText(descuentos.get(position));
                descontado.setTextColor(getResources().getColor(R.color.colorAccent));

            }
            //ACA ALGO SOBRE LA DISPONIBILIDAD
            return convertView;
        }
    }
}












