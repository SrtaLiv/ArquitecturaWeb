package Integrador.dao;

import Integrador.entities.Cliente;
import Integrador.entities.Factura;
import Integrador.entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaDAO {
    Connection conn;
    public FacturaDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertFactura(Factura factura) {
        String query = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, factura.getIdFactura()); // IdFactura
            ps.setInt(2, factura.getIdCliente()); // IdCliente
            ps.executeUpdate();
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


    public Factura find(Integer pk) {
        String query = "SELECT idFactura, idCliente " +
                        "FROM Factura " +
                        "WHERE idFactura = ?";
        Factura factura = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk);
            rs = ps.executeQuery();
            if (rs.next()) {
                Integer idCliente = rs.getInt("idCliente");
                factura = new Factura(pk,  idCliente);
            }
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
        return factura;
    }


    public boolean update(Factura dao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}