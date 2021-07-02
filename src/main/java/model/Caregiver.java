package model;


public class Caregiver extends Person {

    private long cid;
    private String teleNr;

    public Caregiver(String firstName, String surname, String teleNr) {
        super(firstName, surname);
        this.teleNr = teleNr;
    }

    public Caregiver(long cid, String firstName, String surname, String teleNr) {
        super(firstName, surname);
        this.cid = cid;
        this.teleNr = teleNr;
    }

    public long getCid() {
        return this.cid;
    }

    public String getTeleNr() {
        return (teleNr) ;
    }
    public void setTeleNr(String teleNr){
       this.teleNr = teleNr ;
    }

    public String toString() {
        return "Pflegekraft" + "\nMNID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelefonnummer: " + this.getTeleNr() +
                "\n";
    }
}