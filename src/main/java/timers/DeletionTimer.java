package timers;

import controller.AllPatientController;
import controller.Main;
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

    //Für Testzwecke werden Patienten nach 1 Minute statt 10 Jahren gelöscht
    //und es wird alle 10 Sekunden statt jeden Tag auf Löschung geprüft.

    @Override
    public void run() {

        while (Main.isRunning()) {
            try {
                TreatmentDAO treatmentDAO = DAOFactory.getDAOFactory().createTreatmentDAO();
                PatientDAO patientDAO = DAOFactory.getDAOFactory().createPatientDAO();

                for(Patient patient : patientDAO.readAll()) {
                    if(LocalDateTime.now().isAfter(patient.getCreationTime().plusDays(60))) {
                        treatmentDAO.deleteByPid(patient.getPid());
                        patientDAO.deleteById(patient.getPid());
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Thread.currentThread().stop();
    }


}
