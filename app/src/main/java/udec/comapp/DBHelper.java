package udec.comapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table vendedores(username text primary key, contraseña text, nombre text, operando int, localizacion text)");
        db.execSQL("create table clientes(username text primary key, contraseña text)");
        db.execSQL("create table productos(id integer primary key, nombre text, precio int, disponible int, vendedor text, foreign key(vendedor) references vendedores(username))");
        db.execSQL("create table ofertas(id integer primary key, descuento int, duracion int, vendedor text, id_prod int, foreign key(vendedor) references vendedores(username), foreign key(id_prod) references productos(id))");

        db.execSQL("insert into vendedores (username, contraseña, nombre, operando, localizacion) values('ccastro', '1234', 'Cristian Castro', 1, 'Central')");
        db.execSQL("insert into vendedores (username, contraseña, nombre, operando, localizacion) values('vivimechada', '1234', 'Viviana', 0, 'Foro')");
        db.execSQL("insert into clientes (username, contraseña)values('grojas', '1234')");
        db.execSQL("insert into productos (nombre, precio, disponible, vendedor) values('Hamburguesa Simple', '1500', 1, 'ccastro')");
        db.execSQL("insert into productos (nombre, precio, disponible, vendedor) values('Hamburguesa Soya', '1500', 1, 'ccastro')");
        db.execSQL("insert into productos (nombre, precio, disponible, vendedor) values('Sandwich Mechada Palta', '1300', 0, 'vivimechada')");
        db.execSQL("insert into productos (nombre, precio, disponible, vendedor) values('Sandwich Mechada Queso', '1300', 0, 'vivimechada')");
        db.execSQL("insert into ofertas (descuento, duracion, vendedor, id_prod)values('500', '30', 'ccastro', 2)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertarProducto(SQLiteDatabase db, String nombre, int precio, int disponible, String vendedor){

        db.execSQL("insert into productos (nombre, precio, disponible, vendedor) values("+nombre+", "+precio+", "+disponible+", "+vendedor+")");

    }
}
