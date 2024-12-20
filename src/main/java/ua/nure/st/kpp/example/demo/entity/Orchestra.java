package ua.nure.st.kpp.example.demo.entity;

public class Orchestra extends Instumental {
    private String conductor;

    public Orchestra(String title, String composer, int duration, String type, String instrument, String conductor) {
        super(title, composer, duration, instrument, type);
        this.conductor = conductor;
    }

    public String getConductor() {
        return conductor;
    }

    public String toString() {
        return super.toString() + ", Conductor: " + conductor;
    }
}
