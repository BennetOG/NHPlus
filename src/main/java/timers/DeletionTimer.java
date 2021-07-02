package timers;

import datastorage.ConnectionBuilder;
import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import model.Patient;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeletionTimer implements Runnable {

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(6000);

                TreatmentDAO treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
                PatientDAO patientDAO = DAOFactory.getDAOFactory().createPatientDAO();

                for(Patient patient : patientDAO.readAll()) {
                    if(LocalDateTime.now().isAfter(patient.getCreationTime().plusSeconds(10))) {
                        treatmentDAO.deleteByPid(patient.getPid());
                        patientDAO.deleteById(patient.getPid());
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //Thread.currentThread().stop();
    }


}
