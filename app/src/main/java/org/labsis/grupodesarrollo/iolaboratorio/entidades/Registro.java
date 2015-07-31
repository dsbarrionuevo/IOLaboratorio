package org.labsis.grupodesarrollo.iolaboratorio.entidades;

import java.util.Date;

/**
 * Created by ignacio on 31/07/15.
 */
public class Registro {
    private Usuario usuario;
    private Date fecha_ingreso;
    private Date fecha_egreso;


    public Registro() {
    }

    public Registro(Usuario usuario, Date fecha_ingreso, Date fecha_egreso) {
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

    public Date getFecha_egreso() {
        return fecha_egreso;
    }

    public void setFecha_egreso(Date fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
}
