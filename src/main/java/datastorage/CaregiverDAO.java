package datastorage;

import model.Caregiver;
import utils.DateConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CaregiverDAO extends DAOimp<Caregiver> {

    public CaregiverDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO caregiver (cid, firstname, surname, teleNr) VALUES ('%s', '%s', '%s', '%s')",
                caregiver.getCid(), caregiver.getFirstName(), caregiver.getSurname(), caregiver.getTeleNr());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE pid = %d", key);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver c = null;
        c = new Caregiver(Integer.parseInt(result.getString(1)),
                result.getString(2), result.getString(3), result.getString(4));
        return c;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM caregiver";
    }

    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver c = null;
        while (result.next()) {
            c = new Caregiver (result.getInt(1), result.getString(2), result.getString(3),result.getString(4));

            list.add(c);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Caregiver c) {
        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', teleNr = '%s' WHERE cid = %d",
                c.getFirstName(), c.getSurname(), c.getCid());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caregiver WHERE pid = %d", key);
    }
}
