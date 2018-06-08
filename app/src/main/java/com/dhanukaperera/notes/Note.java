package com.dhanukaperera.notes;

/**
 * Created by dhanukaperera on 6/8/18.
 */

public class Note {
    private Integer _id;
    private String title;
    private  String description;

    public Note() {
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Integer get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
}
