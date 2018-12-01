package br.eti.gregori.ccih.dao;

import br.eti.gregori.ccih.model.Antibiotic;
import br.eti.gregori.ccih.model.Gram;
import br.eti.gregori.ccih.util.EnumUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AntibioticDao extends BaseDao<Antibiotic> {

    private AntibioticDao() {
        super(
                "antibiotic",
                new String[] { "name", "acronym", "gram" },
                new String[] { "VARCHAR(255)", "VARCHAR(10)", "ENUM(" + EnumUtil.getStrings(Gram.class) + ")" },
                ""
        );
    }

    public static AntibioticDao getInstance() { return new AntibioticDao(); }

    @Override
    public Antibiotic getObjFromRs(ResultSet rs) throws SQLException {
        Antibiotic obj = new Antibiotic();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setAcronym(rs.getString("acronym"));

        try {
            obj.setGram(Gram.valueOf(rs.getString("gram")));
        } catch (Exception e) {
            throw new RuntimeException("Error trying to set gram to bacteria. " + e.getMessage());
        }
        return obj;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Antibiotic obj) throws SQLException {
        pstmt.setString(1, obj.getName());
        pstmt.setString(2, obj.getAcronym());
        if(obj.getGram() != null) pstmt.setString(3, obj.getGram().toString());
        else pstmt.setNull(3, Types.VARCHAR);
    }
}
