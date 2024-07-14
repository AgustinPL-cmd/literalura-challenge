package com.agustin.literalura.principal;

import com.agustin.literalura.model.*;
import com.agustin.literalura.repository.AuthorRepository;
import com.agustin.literalura.repository.BookRepository;
import com.agustin.literalura.service.ApiConsumption;
import com.agustin.literalura.service.DataConvert;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner keyBoard = new Scanner(System.in);
    private ApiConsumption apiConsumption = new ApiConsumption();
    private final String MAIN_URL = "https://gutendex.com/books/?search=";
    private DataConvert dataConvert = new DataConvert();
    private BookRepository repository;
    private AuthorRepository authorRepository;

    public Principal(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    public void ShowMenu()  {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Search Book 
                    2 - See All the Books
                    3 - See All Authors
                    4 - Search Authors by Year
                    5 - Search for Language
                                  
                    0 - Exit
                    """;
            System.out.println(menu);
            option = keyBoard.nextInt();
            keyBoard.nextLine();

            switch (option) {
                case 1:
                    SearchWebBook();
                    break;
                case 2:
                    seeAllBooks();
                    break;
                case 3:
                    showAuthors();
                    break;
                case 4:
                    searchAuthorsByYear();
                    break;
                case 5:
                    searchByLanguage();
                    break;
                case 0:
                    System.out.println("Closing the application...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public DataBook getDataBook() {
        System.out.println("Write the title of a book: ");
        var titleBook = keyBoard.nextLine();

        try {
            var json = apiConsumption.getData(MAIN_URL + titleBook.replace(" ", "%20"));
            DataResults dataResults = dataConvert.getData(json, DataResults.class);
            List<DataBook> dataBook = dataResults.books().stream()
                    .limit(1)
                    .map(r -> new DataBook(r.title(), r.authors(), r.languages(), r.downloadCount()))
                    .collect(Collectors.toList());
            return dataBook.get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("BOOK DIDN'T FOUND");
        }

        return null;
    }

    private void SearchWebBook() {
        DataBook dataBook = getDataBook();
        Book book = new Book(dataBook);

        List<Author>authorList = book.getAuthors().stream()
                .map(a -> new Author(a.getName(), a.getBirthYear(), a.getDeathYear()))
                .collect(Collectors.toList());

        authorList.forEach(book::addAuthor);

        // Guardar el libro en la base de datos
        repository.save(book);

        System.out.println(dataBook);
    }


    private void seeAllBooks() {
        System.out.println(repository.findAll());
    }

    private void showAuthors() {
        List<Author> authors = authorRepository.findAll();

        if (authors.isEmpty()) {
            System.out.println("No authors found.");
        } else {
            System.out.println("Authors:");
            for (Author author : authors) {
                System.out.println("- Name: " + author.getName());
                System.out.println("  Birth Year: " + author.getBirthYear());
                System.out.println("  Death Year: " + author.getDeathYear());
                System.out.println("  Books:");
                for (Book book : author.getBooks()) {
                    System.out.println("    - Title: " + book.getTitle());
                }
                System.out.println(); // Línea en blanco para separar los autores
            }
        }
    }

    private void searchAuthorsByYear() {
        System.out.println("Enter the first year  of the author:");
        int firstYear = keyBoard.nextInt();
        keyBoard.nextLine(); // Consume the newline character
        System.out.println("Enter the end year  of the author:");
        int endYear = keyBoard.nextInt();
        keyBoard.nextLine(); // Consume the newline character

        List<Author> authors = authorRepository.findByYearsLived(firstYear, endYear);

        if (authors.isEmpty()) {
            System.out.println("No authors found for the given year.");
        } else {
            System.out.println("Authors born or deceased in " + firstYear + ":");
            for (Author author : authors) {
                System.out.println("- Name: " + author.getName());
                System.out.println("  Birth Year: " + author.getBirthYear());
                System.out.println("  Death Year: " + author.getDeathYear());
                System.out.println(); // Línea en blanco para separar los autores
            }
        }
    }

    private void searchByLanguage() {
        System.out.println("Ingresa el idioma del libro (en, es, fr, pt):");
        String language = keyBoard.nextLine().trim();

        // Buscar libros que contengan el idioma especificado
        List<Book> books = repository.findAll().stream()
                .filter(book -> book.getLanguages().contains(language))
                .collect(Collectors.toList());

        if (books.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma especificado.");
        } else {
            System.out.println("Libros en el idioma " + language + ":");
            for (Book book : books) {
                System.out.println("- Título: " + book.getTitle());
                System.out.println("  Autores: " + book.getAuthors().stream()
                        .map(Author::getName)
                        .collect(Collectors.joining(", ")));
                System.out.println("  Idiomas: " + String.join(", ", book.getLanguages()));
                System.out.println(); // Línea en blanco para separar los libros
            }
        }
    }
}
