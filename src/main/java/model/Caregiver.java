package model;


public class Caregiver extends Person {
    private long cid;
    private long teleNr;

    public Caregiver(String firstName, String surname, long teleNr) {
        super(firstName, surname);
        this.teleNr = teleNr;
        }

    public long getCid() {
        return this.cid;
    }

    public String getTeleNr() {
        return Long.toString(teleNr) ;
    }
    public void setTeleNr(String teleNr){
       this.teleNr = Long.parseLong(teleNr);
    }

    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
     * @param m Treatment
     * @return true if the treatment was not already part of the list. otherwise false
     */

    /**
     *
     * @return string-representation of the patient
     */
    public String toString() {
        return "Pflegekraft" + "\nMNID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelefonnummer: " + this.getTeleNr() +
                "\n";
    }
}