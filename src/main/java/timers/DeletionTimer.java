package timers;

import datastorage.ConnectionBuilder;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import model.Patient;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DeletionTimer implements Runnable {

    @Override
    public void run() {

        while (true) {
            try {
                if (!ConnectionBuilder.getConnection().isClosed()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {

                PatientDAO dao = DAOFactory.getDAOFactory().createPatientDAO();

                for(Patient patient : dao.readAll()) {
                    if(patient.getCreationTime().plusSeconds(60).isAfter(LocalTime.now())) {
                        dao.deleteById(patient.getPid());
                    }
                }

                Thread.sleep(60000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        Thread.currentThread().stop();
    }


}
