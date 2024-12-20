package ua.nure.st.kpp.example.demo.entity;

public class Music {
    private int id;
    private String title;
    private String composer;
    private int duration;
    private String type;

    public Music(String title, String composer, int duration, String type) {
        this.title = title;
        this.composer = composer;
        this.duration = duration;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() { return type; }

    public void setType(String type){this.type = type;}


    public String toString() {
        return "Title: " + title + ", Composer: " + composer + ", Duration: " + duration + " seconds";
    }

    public int compareTo(Music other) {
        return Integer.compare(this.duration, other.duration);
    }


}
