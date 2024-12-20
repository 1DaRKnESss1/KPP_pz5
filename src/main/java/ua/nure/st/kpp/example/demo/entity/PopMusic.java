package ua.nure.st.kpp.example.demo.entity;

public class PopMusic extends Song {
    private String album;

    public PopMusic(String title, String composer, int duration, String type, String lyrics, String album) {
        super(title, composer, duration, lyrics, type);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public String toString() {
        return super.toString() + ", Album: " + album;
    }
}

