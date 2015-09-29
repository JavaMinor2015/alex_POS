package javaminor.data.access;

import javaminor.util.PreferenceUtil;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

/**
 * Created by alex on 9/29/15.
 */
public class DataHandler {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public DataHandler() {
    }


    public void getDBConnection() throws SQLException {
        OracleDataSource ds;
        ds = new OracleDataSource();
        ds.setURL(PreferenceUtil.JDBC_URL);
        conn=ds.getConnection(PreferenceUtil.USER_ID,PreferenceUtil.PASSWORD);
    }

    public ResultSet getAllScanItems() throws SQLException {
        getDBConnection();
        stmt = conn.createStatement();
        String query = "SELECT * FROM SCANITEM";
        return stmt.executeQuery(query);
    }

    public ResultSet getScanCodesForItem(int id) throws SQLException {
        getDBConnection();
        //SELECT DISTINCT CODE.CODE, CODE.CODETYPE FROM CODE, SCANITEM, SCANITEM_CODE WHERE SCANITEM_CODE.SCANITEM_ID = 1 AND SCANITEM_CODE.CODE_ID = CODE.ID;
        String sql = "SELECT DISTINCT CODE.CODE, CODE.CODETYPE " +
                     "FROM CODE, SCANITEM, SCANITEM_CODE " +
                     "WHERE SCANITEM_CODE.SCANITEM_ID = ? " +
                            "AND SCANITEM_CODE.CODE_ID = CODE.ID";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        return preparedStatement.executeQuery();
    }

    public void closeAll(){
        try {
            conn.close();
            stmt.close();
            preparedStatement.close();
        } catch (SQLException | NullPointerException e) {
            conn = null;
            stmt = null;
            preparedStatement = null;
        }
    }
}

