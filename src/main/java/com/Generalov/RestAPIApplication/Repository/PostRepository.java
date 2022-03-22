package com.Generalov.RestAPIApplication.Repository;


import com.Generalov.RestAPIApplication.Entity.PostEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class PostRepository implements IRestRepository<PostEntity> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"post_pk\", \"title\" " +
            "FROM \"post\" " +
            "ORDER BY \"post_pk\"";

    private static String selectByIdQuery = "SELECT \"post_pk\", \"title\" " +
            "FROM \"post\" " +
            "WHERE \"post_pk\" = ?";

    private static String insertQuery = "INSERT INTO \"post\"(\"title\") " +
            "VALUES (?) " +
            "RETURNING \"post_pk\", \"title\"";


    private static String updateQuery = "UPDATE \"post\" " +
            "SET \"title\" = ? " +
            "WHERE \"post_pk\" = ? " +
            "RETURNING \"post_pk\", \"title\"";

    private static String deleteQuery = "DELETE FROM \"post\" " +
            "WHERE \"post_pk\" = ? " +
            "RETURNING \"post_pk\", \"title\"";


    public PostRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public PostEntity[] select() {
        ArrayList<PostEntity> values = new ArrayList<PostEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new PostEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        PostEntity[] result = new PostEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public PostEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new PostEntity(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public PostEntity insert(PostEntity entity) {
        Object[] params = new Object[] { entity.getTitle() };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new PostEntity(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public PostEntity update(Integer id, PostEntity entity) {
        Object[] params = new Object[] { entity.getTitle(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new PostEntity(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public PostEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new PostEntity(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}
