package Integrador.dao;

import Integrador.entities.Factura_Producto;

import java.sql.Connection;

public class Factura_ProductoDAO {
    private Connection conn;
    public Factura_ProductoDAO(Connection conn) {
        this.conn = conn;
    }
    public int insert(Factura_Producto dao) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }


    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    public Factura_Producto find(Integer pk) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }


    public boolean update(Factura_Producto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
