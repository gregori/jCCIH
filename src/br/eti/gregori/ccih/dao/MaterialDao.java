package br.eti.gregori.ccih.dao;

import br.eti.gregori.ccih.model.Location;
import br.eti.gregori.ccih.model.Material;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialDao extends BaseDao<Material> {

    private MaterialDao() {
        super(
                "material",
                new String[] { "name" },
                new String[] { "VARCHAR(255)" },
                ""
        );
    }

    public static MaterialDao getInstance() { return new MaterialDao(); }

    @Override
    public Material getObjFromRs(ResultSet rs) throws SQLException {
        Material obj = new Material();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        return obj;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Material obj) throws SQLException {
        pstmt.setString(1, obj.getName());
    }
}
