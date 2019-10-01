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
        db.execSQL("create table vendedores(username text primary key, contraseña text, nombre text, operando boolean, localizacion text)");
        db.execSQL("create table clientes(username text primary key, contraseña text)");
        db.execSQL("create table productos(id int primary key, nombre text, precio real, disponible boolean, vendedor text, foreign key(vendedor) references vendedores(username))");
        db.execSQL("create table ofertas(id int primary key, descuento real, duracion int, vendedor text, id_prod int, foreign key(vendedor) references vendedores(username), foreign key(id_prod) references productos(id))");

        db.execSQL("insert into vendedores values('ccastro', '1234', 'Cristian Castro', True, 'Central')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
