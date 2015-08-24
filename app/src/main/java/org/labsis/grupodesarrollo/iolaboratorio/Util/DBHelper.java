package org.labsis.grupodesarrollo.iolaboratorio.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.labsis.grupodesarrollo.iolaboratorio.entidades.Usuario;

/**
 * Created by Diego on 31/07/2015.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String DB_NOMBRE = "io_laboratorio";
    private static final int VERSION = 1;

    private final String TABLA_USUARIOS = "CREATE TABLE usuario ( "
            + "id INT NOT NULL, " + "nombre VARCHAR(30) NOT NULL, "
            + "clave VARCHAR(30) NOT NULL, " + "PRIMARY KEY (id)" + ")";

    private final String DB_CREACION = TABLA_USUARIOS;

    public DBHelper(Context context) {
        super(context, DB_NOMBRE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREACION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL(DB_CREACION);
    }

    public boolean insertarUsuario(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(usuario.getId()!= -1) {
            cv.put("id", usuario.getId());
        }
        cv.put("nombre", usuario.getNombre());
        cv.put("clave", usuario.getClave());
        return db.insert("usuario", null, cv) != -1;
    }

    public Usuario consultarUsuario() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query("usuario",
                new String[] { "id", "nombre", "clave" }, null, null, null,
                null, null, null);
        Usuario usuario = null;
        if (c.moveToFirst()) {
            int indiceId = c.getColumnIndex("id");
            int indiceNombre = c.getColumnIndex("nombre");
            int indiceClave = c.getColumnIndex("clave");
            usuario = new Usuario(c.getInt(indiceId),
                    c.getString(indiceNombre), c.getString(indiceClave));
        }
        return usuario;
    }

}
