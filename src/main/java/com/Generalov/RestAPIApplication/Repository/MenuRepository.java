package com.Generalov.RestAPIApplication.Repository;

import com.Generalov.RestAPIApplication.Entity.MenuEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class MenuRepository implements IRestRepository<MenuEntity>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"menu_pk\", \"title\" " +
            "FROM \"menu\" " +
            "ORDER BY \"menu_pk\"";

    private static String selectByIdQuery = "SELECT \"menu_pk\", \"title\" " +
            "FROM \"menu\" " +
            "WHERE \"menu_pk\" = ?";

    private static String insertQuery = "INSERT INTO \"menu\"(\"title\") " +
            "VALUES (?) " +
            "RETURNING \"menu_pk\", \"title\"";

    private static String updateQuery = "UPDATE \"menu\" " +
            "SET \"title\" = ? " +
            "WHERE \"menu_pk\" = ? " +
            "RETURNING \"menu_pk\", \"title\"";

    private static String deleteQuery = "DELETE FROM \"menu\" " +
            "WHERE \"menu_pk\" = ? " +
            "RETURNING \"menu_pk\", \"title\"";

    public MenuRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public MenuEntity[] select() {
        ArrayList<MenuEntity> values = new ArrayList<MenuEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new MenuEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        MenuEntity[] result = new MenuEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public MenuEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new MenuEntity(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public MenuEntity insert(MenuEntity entity) {
        Object[] params = new Object[] { entity.getTitle() };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new MenuEntity(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public MenuEntity update(Integer id, MenuEntity entity) {
        Object[] params = new Object[] { entity.getTitle(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new MenuEntity(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public MenuEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new MenuEntity(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
