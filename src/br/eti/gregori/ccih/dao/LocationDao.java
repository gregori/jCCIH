package br.eti.gregori.ccih.dao;

import br.eti.gregori.ccih.model.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDao extends BaseDao<Location> {

    private LocationDao() {
        super(
                "location",
                new String[] { "name" },
                new String[] { "VARCHAR(255)" },
                ""
        );
    }

    public static LocationDao getInstance() { return new LocationDao(); }

    @Override
    public Location getObjFromRs(ResultSet rs) throws SQLException {
        Location loc = new Location();
        loc.setId(rs.getInt("id"));
        loc.setName(rs.getString("name"));
        return loc;
    }

    @Override
    public void setAttributesFromObj(PreparedStatement pstmt, Location obj) throws SQLException {
        pstmt.setString(1, obj.getName());
    }
}
