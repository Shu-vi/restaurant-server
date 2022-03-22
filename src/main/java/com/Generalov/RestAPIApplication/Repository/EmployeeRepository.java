package com.Generalov.RestAPIApplication.Repository;

import com.Generalov.RestAPIApplication.Entity.EmployeeEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class EmployeeRepository implements IRestRepository<EmployeeEntity>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"employee_pk\", \"firstname\", \"lastname\", \"passportseriesandnumber\", \"postid\" , \"salary\", \"age\" " +
            "FROM \"employee\" " +
            "ORDER BY \"employee_pk\"";

    private static String selectByIdQuery = "SELECT \"employee_pk\", \"firstname\", \"lastname\", \"passportseriesandnumber\", \"postid\" , \"salary\", \"age\" " +
            "FROM \"employee\" " +
            "WHERE \"employee_pk\" = ?";

    private static String insertQuery = "INSERT INTO \"employee\"(\"firstname\", \"lastname\", \"passportseriesandnumber\", \"postid\" , \"salary\", \"age\") " +
            "VALUES (?, ?, ?, ?, ?, ?) " +
            "RETURNING \"employee_pk\", \"firstname\", \"lastname\", \"passportseriesandnumber\", \"postid\" , \"salary\", \"age\"";


    private static String selectByPostIdQuery = "SELECT \"employee_pk\", \"firstname\", \"lastname\", \"passportseriesandnumber\", \"postid\" , \"salary\", \"age\" " +
            "FROM \"employee\" " +
            "WHERE \"postid\" = ?";


    private static String updateQuery = "UPDATE \"employee\" " +
            "SET \"firstname\" = ?, \"lastname\" = ?, \"passportseriesandnumber\" = ?, \"postid\" = ?, \"salary\" = ?,\"age\" = ? " +
            "WHERE \"employee_pk\" = ? " +
            "RETURNING \"employee_pk\", \"firstname\", \"lastname\", \"passportseriesandnumber\", \"postid\" , \"salary\", \"age\"";

    private static String deleteQuery = "DELETE FROM \"employee\" " +
            "WHERE \"employee_pk\" = ? " +
            "RETURNING \"employee_pk\", \"firstname\", \"lastname\", \"passportseriesandnumber\", \"postid\" , \"salary\", \"age\"";

    public EmployeeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public EmployeeEntity[] select() {
        ArrayList<EmployeeEntity> values = new ArrayList<EmployeeEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new EmployeeEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getInt(5),
                    rowSet.getInt(6),
                    rowSet.getInt(7)
            ));
        }
        EmployeeEntity[] result = new EmployeeEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public EmployeeEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new EmployeeEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5),
                rowSet.getInt(6),
                rowSet.getInt(7)
        );
    }

    public EmployeeEntity[] selectByPostId(Integer postId) {
        ArrayList<EmployeeEntity> values = new ArrayList<EmployeeEntity>();
        Object[] params = new Object[] { postId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByPostIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new EmployeeEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getInt(5),
                    rowSet.getInt(6),
                    rowSet.getInt(7)
            ));
        }
        EmployeeEntity[] result = new EmployeeEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public EmployeeEntity insert(EmployeeEntity entity) {
        Object[] params = new Object[] { entity.getName(), entity.getLastname(), entity.getPassportSeAndNu(), entity.getPost(), entity.getSalary(), entity.getAge() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.SMALLINT, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new EmployeeEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5),
                rowSet.getInt(6),
                rowSet.getInt(7)
        );
    }

    @Override
    public EmployeeEntity update(Integer id, EmployeeEntity entity) {
        Object[] params = new Object[] { entity.getName(), entity.getLastname(), entity.getPassportSeAndNu(), entity.getPost(), entity.getSalary(), entity.getAge(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.SMALLINT, Types.INTEGER, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new EmployeeEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5),
                rowSet.getInt(6),
                rowSet.getInt(7)
        );
    }

    @Override
    public EmployeeEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new EmployeeEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getInt(5),
                rowSet.getInt(6),
                rowSet.getInt(7)
        );
    }
}
