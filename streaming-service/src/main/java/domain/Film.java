package domain;

public class Film {

    private Long id;
    private String title;
    private Genre genre;
    private int length;

    public Film(final Long id, final String title, final Genre genre, final int length) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
    }

    public int getLength() {
        return this.length;
    }
}
