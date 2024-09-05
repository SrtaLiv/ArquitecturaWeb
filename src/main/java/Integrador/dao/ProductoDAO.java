package Integrador.dao;

import Integrador.dto.ProductoDTO;
import Integrador.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection conn;
    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }
    public void insert(Producto producto) throws Exception {
        String query = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, producto.getIdProducto()); // id
            ps.setString(2, producto.getNombre()); // nombre
            ps.setInt(3, producto.getValor()); // valor
            ps.executeUpdate();
            //System.out.println("producto insertado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


    public Producto find(Integer pk) {
        String select = "SELECT * " +
                "FROM Producto p " +
                "WHERE idProducto = ? ";
        PreparedStatement ps = null;
        Producto producto = null;
        try{
            ps = conn.prepareStatement(select);
            ps.setInt(1, pk);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Integer id = rs.getInt(1);
                String nombre = rs.getString(2);
                int valor = rs.getInt(3);
                producto = new Producto(id, nombre, valor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }


    public boolean update(Producto dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public ProductoDTO getProductoMayorRecaudacion(){
        String select = "SELECT p.idProducto, (sum(cantidad) * p.valor) " +
                        "FROM Producto p " +
                        "JOIN Factura_Producto fp ON p.idProducto = fp.idProducto " +
                        "GROUP BY p.idProducto " +
                        "ORDER BY 2 desc " +
                        "LIMIT 1 ";
        PreparedStatement ps = null;
        Producto p = null;
        ProductoDTO pDTO = null;
        try{
            ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                p = find(rs.getInt(1));
                pDTO = new ProductoDTO(p, rs.getInt(2));
                System.out.println(rs.getInt(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pDTO;
    }
}
