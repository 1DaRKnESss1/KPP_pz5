package ua.nure.st.kpp.example.demo.entity;

public class Song extends Music {
    private String lyrics;

    public Song(String title, String composer, int duration, String lyrics, String type) {
        super(title, composer, duration, type);
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

    public String toString() {
        return super.toString() + ", Lyrics: " + lyrics;
    }
}
