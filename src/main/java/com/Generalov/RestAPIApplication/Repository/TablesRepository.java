package com.Generalov.RestAPIApplication.Repository;

import com.Generalov.RestAPIApplication.Entity.TablesEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class TablesRepository implements IRestRepository<TablesEntity> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"tables_pk\", \"number\", \"isfree\" " +
            "FROM \"tables\" " +
            "ORDER BY \"tables_pk\"";

    private static String selectByIdQuery = "SELECT \"tables_pk\", \"number\", \"isfree\" " +
            "FROM \"tables\" " +
            "WHERE \"tables_pk\" = ?";

    private static String insertQuery = "INSERT INTO \"tables\"(\"number\", \"isfree\") " +
            "VALUES (?, ?) " +
            "RETURNING \"tables_pk\", \"number\", \"isfree\"";


    private static String updateQuery = "UPDATE \"tables\" " +
            "SET \"number\" = ?, \"isfree\" = ? " +
            "WHERE \"tables_pk\" = ? " +
            "RETURNING \"tables_pk\", \"number\", \"isfree\"";

    private static String deleteQuery = "DELETE FROM \"tables\" " +
            "WHERE \"tables_pk\" = ? " +
            "RETURNING \"tables_pk\", \"number\", \"isfree\"";

    TablesRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public TablesEntity[] select() {
        ArrayList<TablesEntity> values = new ArrayList<TablesEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new TablesEntity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getBoolean(3)
            ));
        }
        TablesEntity[] result = new TablesEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public TablesEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TablesEntity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getBoolean(3)
        );
    }

    @Override
    public TablesEntity insert(TablesEntity entity) {
        Object[] params = new Object[] { entity.getTable(), entity.getFree() };
        int[] types = new int[] { Types.INTEGER, Types.BOOLEAN };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TablesEntity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getBoolean(3)
        );
    }

    @Override
    public TablesEntity update(Integer id, TablesEntity entity) {
        Object[] params = new Object[] { entity.getTable(), entity.getFree(), id };
        int[] types = new int[] { Types.INTEGER, Types.BOOLEAN, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TablesEntity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getBoolean(3)
        );
    }

    @Override
    public TablesEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TablesEntity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getBoolean(3)
        );
    }
}
