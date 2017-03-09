package org.labsis.grupodesarrollo.iolaboratorio.entidades;

import java.util.Date;

/**
 * Created by ignacio on 31/07/15.
 */
public class Registro {
    private Usuario usuario;
    private String fecha_ingreso;
    private String fecha_egreso;


    public Registro() {
    }

    public Registro(Usuario usuario, String fecha_ingreso, String fecha_egreso) {
        this.usuario = usuario;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_egreso = fecha_egreso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFecha_egreso() {
        return fecha_egreso;
    }

    public void setFecha_egreso(String fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
}
