package Integrador.entities;

public class Producto {
    private Integer idProducto;
    private String nombre;
    private Float valor;
    public Producto() {}
    public Producto(Integer idProducto, String nombre, Float valor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
