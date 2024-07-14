package com.agustin.literalura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private List<Author> authors = new ArrayList<>();

    private List<String> languages;

    private Integer downloadCount;

    // Constructors, getters, setters, and other methods

    public Book() {
    }

    public Book(DataBook dataBook) {
        this.title = dataBook.title();
        this.languages = dataBook.languages();
        this.downloadCount = dataBook.downloadCount();
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
        author.addBook(this);

    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        StringBuilder authorsString = new StringBuilder();
        for (Author author : authors) {
            authorsString.append(author.getName()).append(", ");
        }
        if (authorsString.length() > 0) {
            authorsString.setLength(authorsString.length() - 2); // Elimina la última coma y espacio
        }

        return "\n\n----------- LIBRO -----------" +
                "\nTitle='" + title + '\'' +
                "\nAuthors=" + authorsString.toString() + // Mostrar solo nombres de autores
                "\nLanguages=" + languages +
                "\nDownloads=" + downloadCount +
                "\n-----------------------------";
    }

    public String showAtAuthor() {
        return getTitle();
    }
}
