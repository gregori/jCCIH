package br.eti.gregori.ccih.dao;

import br.eti.gregori.ccih.model.AntibioticTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AntibioticTestDao extends BaseDao<AntibioticTest> {
    private AntibioticTestDao() {
        super (
                "antibiotic_test",
                new String[] { "id_antibiogram", "id_antibiotic", "resistant" },
                new String[] { "INT", "INT", "BOOLEAN" },
                "FOREIGN KEY (id_antibiogram) REFERENCES antibiogram(id)," +
                "FOREIGN KEY (id_antibiotic) REFERENCES antibiotic(id)"
        );

    }

    public static AntibioticTestDao getInstance() { return new AntibioticTestDao(); }

    @Override
    public AntibioticTest getObjFromRs(ResultSet rs) throws SQLException {
        AntibioticTest atbTest = new AntibioticTest();
        atbTest.setId(rs.getInt("id"));
        atbTest.setResistant(rs.getBoolean("resistant"));
        atbTest.setAntibiogram(AntibiogramDao.getInstance().getById(rs.getInt("id_antibiogram")));
        atbTest.setAntibiotic(AntibioticDao.getInstance().getById(rs.getInt("id_antibiotic")));

        return atbTest;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, AntibioticTest obj) throws SQLException {
        if(obj.getAntibiogram() != null) pstmt.setInt(1, obj.getAntibiogram().getId());
        else pstmt.setNull(1, Types.INTEGER);
        if(obj.getAntibiotic() != null) pstmt.setInt(2, obj.getAntibiotic().getId());
        else pstmt.setNull(2, Types.INTEGER);
    }
}
