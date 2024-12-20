package ua.nure.st.kpp.example.demo.entity;


public class Instumental extends Music {
    private String instrument;

    public Instumental(String title, String composer, int duration, String instrument, String type) {
        super(title, composer, duration, type);
        this.instrument = instrument;
    }

    public String getInstrument() {
        return instrument;
    }

    public String toString() {
        return super.toString() + ", Instrument: " + instrument;
    }
}
