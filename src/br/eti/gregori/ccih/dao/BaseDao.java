package br.eti.gregori.ccih.dao;

import br.eti.gregori.ccih.db.ConnectionManager;
import br.eti.gregori.ccih.model.BaseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T extends BaseModel>{
    protected Connection conn;
    protected String table;

    private String[] attributes;
    private String[] attributeTypes;
    private String createAdditional;

    private PreparedStatement selectAll;
    private PreparedStatement selectOne;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    protected BaseDao(String table, String[] attributes, String[] attributeTypes, String createAdditional) {
        this.table = table;
        this.attributes = attributes;
        this.attributeTypes = attributeTypes;
        this.createAdditional = createAdditional;

        String stringInsert = "";
        String stringInsertValues = "";
        String stringUpdate = "";

        try {
            conn  = ConnectionManager.getInstance().getConnection();

            selectAll = conn.prepareStatement("SELECT * FROM " + this.table);
            selectOne = conn.prepareStatement("SELECT * FROM " + this.table + " WHERE id=?");
            //delete = conn.prepareStatement("UPDATE " + table + " SET status=\"" + Status.ACTIVE + "\" WHERE id=?");
            delete = conn.prepareStatement("DELETE FROM " + this.table + " WHERE id=?");

            for(String att: attributes){
                stringInsert += att + ",";
                stringInsertValues += "?,";
                stringUpdate += att + "=?,";
            }

            stringInsert = stringInsert.substring(0,stringInsert.length() - 1);
            stringInsertValues = stringInsertValues.substring(0,stringInsertValues.length() - 1);
            stringUpdate = stringUpdate.substring(0,stringUpdate.length() - 1);

            insert = conn.prepareStatement("INSERT INTO " + this.table + " (" + stringInsert + ") VALUES (" + stringInsertValues + ")", Statement.RETURN_GENERATED_KEYS);
            update = conn.prepareStatement("UPDATE " + this.table + " SET " + stringUpdate + " WHERE id=?");

            createTable(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void createTable(Boolean dropTable) throws Exception {
        if(attributes.length != attributeTypes.length){
            throw new RuntimeException("Attributes and Types sizes are different");
        }

        Statement stmt = conn.createStatement();

        if(dropTable){
            String SqlDrop = "DROP TABLE IF EXISTS " + table;
            stmt.execute(SqlDrop);
        }

        if(createAdditional.length() > 0 && !createAdditional.substring(0, 1).equals(",")){
            createAdditional = "," + createAdditional;
        }

        String attWithTypesToCreateTable = "";

        for (int i = 0; i < attributes.length; i++) {
            attWithTypesToCreateTable += attributes[i] + " " + attributeTypes[i] + ",";
        }

        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + table
                + " (id INT NOT NULL AUTO_INCREMENT,"
                + attWithTypesToCreateTable
                + "PRIMARY KEY (id)"
                + createAdditional
                + ")";

        stmt.execute(sqlCreate);
    }

    public abstract T getObjFromRs(ResultSet rs) throws SQLException;
    public abstract void setAttributesFromObj(PreparedStatement pstmt, T obj) throws SQLException;

    public List<T> getAll() {
        List<T> resultado = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = selectAll.executeQuery();

            while (rs.next()) {
                resultado.add(getObjFromRs(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return resultado;
    }

    public List<T> getAllWithWhere(String whereParams) {
        List<T> resultado = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = conn.prepareStatement("SELECT * FROM " + table + " WHERE " + whereParams).executeQuery();

            while (rs.next()) {
                resultado.add(getObjFromRs(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return resultado;
    }

    public T getById(int id){
        T resultado = null;
        ResultSet rs = null;

        try {
            selectOne.setInt(1, id);

            rs = selectOne.executeQuery();

            while (rs.next()) {
                resultado = getObjFromRs(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return resultado;
    }

    public int modify(T obj) {
        int resultado = 0;

        try {
            if(getById(obj.getId()) != null){
                setAttributesFromObj(update, obj);
                update.setLong(attributes.length + 1, obj.getId());
                resultado = update.executeUpdate();
            }else{
                setAttributesFromObj(insert, obj);
                resultado = insert.executeUpdate();

                ResultSet generatedKeys = insert.getGeneratedKeys();

                if (generatedKeys.next()) {
                    obj.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating client failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            close();
        }

        return resultado;
    }

    public int deleteById(int id) {
        int resultado = 0;

        try {
            delete.setInt(1, id);
            // deleta e retorna o numero de linhas atualizadas
            resultado = delete.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            close();
        }

        return resultado;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}