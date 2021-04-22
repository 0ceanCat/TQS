package booksearch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wy
 * @date 2021/4/20 10:01
 */
public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(final LocalDateTime from, final LocalDateTime to) {
        return store.stream().filter(book -> from.isBefore(book.getPublished()) && to.isAfter(book.getPublished()))
                .sorted(Comparator.comparing(Book::getPublished).reversed())
                .collect(Collectors.toList());
    }

    public List<Book> findBooks(String category) {
        return store.stream().filter(book -> category.equals(book.getCategory()))
                .sorted(Comparator.comparing(Book::getCategory))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByTitle(String title) {
        return store.stream().filter(book -> title.equals(book.getTitle()))
                .sorted(Comparator.comparing(Book::getCategory))
                .collect(Collectors.toList());
    }
}
