package br.eti.gregori.ccih.dao;

import br.eti.gregori.ccih.model.Bacteria;
import br.eti.gregori.ccih.model.Gram;
import br.eti.gregori.ccih.model.Material;
import br.eti.gregori.ccih.util.EnumUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class BacteriaDao extends BaseDao<Bacteria> {

    private BacteriaDao() {
        super(
                "bacteria",
                new String[] { "name", "gram" },
                new String[] { "VARCHAR(255)", "ENUM(" + EnumUtil.getStrings(Gram.class) + ")" },
                ""
        );
    }

    public static BacteriaDao getInstance() { return new BacteriaDao(); }

    @Override
    public Bacteria getObjFromRs(ResultSet rs) throws SQLException {
        Bacteria obj = new Bacteria();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));

        try {
            obj.setGram(Gram.valueOf(rs.getString("gram")));
        } catch (Exception e) {
            throw new RuntimeException("Error trying to set gram to bacteria. " + e.getMessage());
        }
        return obj;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Bacteria obj) throws SQLException {
        pstmt.setString(1, obj.getName());
        if(obj.getGram() != null) pstmt.setString(2, obj.getGram().toString());
        else pstmt.setNull(2, Types.VARCHAR);
    }
}
