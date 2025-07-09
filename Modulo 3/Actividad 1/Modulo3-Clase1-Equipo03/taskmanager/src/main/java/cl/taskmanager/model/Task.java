package cl.taskmanager.model;

public class Task {
    private final int id;
    private String header;
    private String paragraph;

    public Task (int id, String header, String paragraph) {
        this.id = id;
        this.header = header;
        this.paragraph = paragraph;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setHeader(String newHeader) {
        this.header = newHeader;
    }

    public void setParagraph(String newParagraph) {
        this.paragraph = newParagraph;
    }

}