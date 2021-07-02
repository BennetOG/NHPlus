package datastorage;

import model.Caregiver;
import utils.DateConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific patient-SQL-queries.
 */
public class CaregiverDAO extends DAOimp<Caregiver> {

    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn
     */
    public CaregiverDAO(Connection conn) {
        super(conn);
    }


    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO caregiver (firstname, surname, cid,  teleNr) VALUES ('%s', '%s', '%s', '%s')",
                caregiver.getFirstName(),  caregiver.getSurname(), caregiver.getCid(), caregiver.getTeleNr());
    }
 //String firstName, String surname, long cid, long teleNr
    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECTis to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM patient WHERE pid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Patient</code>
     * @param result ResultSet with a single row. Columns will be mapped to a patient-object.
     * @return patient with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver c = null;
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(4));
        c = new Caregiver(result.getString(1),
                result.getString(2),  result.getString(3));
        return c;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all patients.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM patient";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Patient-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to patient-object.
     * @return ArrayList with patients from the resultSet.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver c = null;
        while (result.next()) {
            c = new Caregiver (result.getString(1), result.getString(2),result.getString(3));

            list.add(c);
        }
        return list;
    }


    @Override
    protected String getUpdateStatementString(Caregiver c) {
        return String.format("UPDATE patient SET firstname = '%s', surname = '%s','%s' WHERE cid = %d teleNr = '%s', " + c.getFirstName(), c.getSurname(), c.getCid(),
                c.getTeleNr());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM patient WHERE pid=%d", key);
    }
}
