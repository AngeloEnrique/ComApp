package udec.comapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table vendedores(username text primary key, contraseña text, nombre text, operando int, localizacion text, descripcion text)");
        db.execSQL("create table clientes(username text primary key, contraseña text)");
        db.execSQL("create table productos(id integer primary key, nombre text, precio text, disponible int,endescuento int, vendedor text, foreign key(vendedor) references vendedores(username))");
        db.execSQL("create table ofertas(id integer primary key, descuento int, duracion int, vendedor text, id_prod int, foreign key(vendedor) references vendedores(username), foreign key(id_prod) references productos(id))");


        db.execSQL("insert into vendedores (username, contraseña, nombre, operando, localizacion,descripcion) values('ccastro', '1234', 'Cristian Castro', 1, 'Central', 'Las mejores burgers de la Central')");
        db.execSQL("insert into vendedores (username, contraseña, nombre, operando, localizacion,descripcion) values('mammamia', '1234', 'Rodrigo Hernandez', 0, 'Central','Los mejores Pizzas y papas rellenas de la Central')");

        db.execSQL("insert into vendedores (username, contraseña, nombre, operando, localizacion,descripcion) values('vivimechada', '1234', 'Viviana', 0, 'Foro','Los mejores sandwiches de Foro')");
        db.execSQL("insert into vendedores (username, contraseña, nombre, operando, localizacion,descripcion) values('Marcelitrox', '1234', 'Marcelo', 0, 'Foro','Los mejores completos de Foro')");

        db.execSQL("insert into vendedores (username, contraseña, nombre, operando, localizacion,descripcion) values('estebandido', '1234', 'Esteban', 0, 'Educa','Los mejores Quequitos de Educa')");
        db.execSQL("insert into clientes (username, contraseña)values('grojas', '1234')");

        db.execSQL("insert into productos (nombre, precio, disponible,endescuento, vendedor) values('Hamburguesa Simple', '1500', 1,1, 'ccastro')");
        db.execSQL("insert into productos (nombre, precio, disponible,endescuento, vendedor) values('Hamburguesa Soya', '1500', 0,0, 'ccastro')");
        db.execSQL("insert into productos (nombre, precio, disponible,endescuento, vendedor) values('Hamburguesa Doble', '2500', 1,0, 'ccastro')");

        db.execSQL("insert into productos (nombre, precio, disponible,endescuento, vendedor) values('Sandwich Mechada Palta', '1600', 1,0, 'vivimechada')");
        db.execSQL("insert into productos (nombre, precio, disponible,endescuento, vendedor) values('Sandwich Mechada Queso', '1300', 0,0, 'vivimechada')");

        db.execSQL("insert into ofertas (descuento, duracion, vendedor, id_prod)values(500, 40, 'ccastro', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void InsertProducts(SQLiteDatabase db,String nombre, String precio, int disp, String vend){
        db.execSQL("insert into productos (nombre, precio, disponible, vendedor) values(nombre, precio, disp, vend)");
    }
    public void InsertOferta(SQLiteDatabase db, int descu, int duracion, String vend, int id_product){
        db.execSQL("insert into ofertas (descuento, duracion, vendedor, id_prod)values( descu, duracion, vend, id_product)");
        db.execSQL("update productos set endescuento=1 where id=id_product");
    }
   public Cursor getOfertas(SQLiteDatabase db,int id){
        return db.rawQuery("select * from ofertas where id_prod = id",null);
   }
    public Cursor getProductos(SQLiteDatabase db, String vend){
        return db.rawQuery("select * from productos where nombre ="+vend,null);
    }
    public Cursor getAllProductos(SQLiteDatabase db){
        return db.rawQuery("select * from productos" ,null);
    }



    public Cursor getVend(SQLiteDatabase db, String Localizacion){
        return db.rawQuery("select * from vendedores where localizacion = "+ Localizacion,null);
    }
    public Cursor getAllVend(SQLiteDatabase db){
        return db.rawQuery("select * from vendedores",null);
    }
}