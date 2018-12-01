package br.eti.gregori.ccih.dao;

import br.eti.gregori.ccih.model.Antibiogram;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AntibiogramDao extends BaseDao<Antibiogram> {

    private AntibiogramDao() {
        super(
                "anti_biogram",
                new String[] { "id_bacteria", "id_location", "id_material" },
                new String[] { "INT", "INT", "INT" },
                "FOREIGN KEY (id_bacteria) REFERENCES bacteria(id)" +
                "FOREIGN KEY (id_location) REFERENCES location(id)" +
                "FOREIGN KEY (id_material) REFERENCES material(id)"
        );
    }

    public static AntibiogramDao getInstance() { return new AntibiogramDao(); }

    @Override
    public Antibiogram getObjFromRs(ResultSet rs) throws SQLException {
        Antibiogram antiBiogram = new Antibiogram();
        antiBiogram.setId(rs.getInt("id"));
        antiBiogram.setBacteria(BacteriaDao.getInstance().getById(rs.getInt("id_bacteria")));
        antiBiogram.setLocation(LocationDao.getInstance().getById(rs.getInt("id_location")));
        antiBiogram.setMaterial(MaterialDao.getInstance().getById(rs.getInt("id_material")));

        return antiBiogram;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Antibiogram obj) throws SQLException {
        if(obj.getBacteria() != null) pstmt.setInt(1, obj.getBacteria().getId());
        else pstmt.setNull(1, Types.INTEGER);
        if(obj.getLocation() != null) pstmt.setInt(2, obj.getLocation().getId());
        else pstmt.setNull(2, Types.INTEGER);
        if(obj.getMaterial() != null) pstmt.setInt(3, obj.getMaterial().getId());
        else pstmt.setNull(3, Types.INTEGER);
    }
}
