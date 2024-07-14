package com.agustin.literalura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agustin.literalura.model.Book;
import com.agustin.literalura.model.Author;
import com.agustin.literalura.repository.BookRepository;
import com.agustin.literalura.repository.AuthorRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void saveBookAndAuthors(Book book) {
        try {
            for (Author author : book.getAuthors()) {
                Author existingAuthor = authorRepository.findByName(author.getName());
                if (existingAuthor != null) {
                    author = existingAuthor;
                } else {
                    author = authorRepository.save(author);
                }
                author.getBooks().add(book);
            }
            bookRepository.save(book);
            System.out.println("Book and Authors saved correctly");
        } catch (Exception e) {
            System.out.println("Error al guardar el libro y los autores: " + e.getMessage());
        }
    }
}
