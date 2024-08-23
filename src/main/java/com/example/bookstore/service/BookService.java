package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookQuantity;
import com.example.bookstore.model.BookRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
  @Autowired private BookRepository bookRepository;

  public List<Book> getBooks(){
    List<Book> books = bookRepository.findAll();
    return books;
  }

  public Book findBookByIsbn(String isbn){
    Book book = bookRepository.findByIsbn(isbn);
    return book;
  }

  public void save(Book book){
    bookRepository.save(book);
  }

  @Transactional
  public void updateBook(Book book){
    bookRepository.updateBook(book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPrice(), book.getDescription(), book.getInventory(), book.getId());
  }

  @Transactional
  public void deleteBook(String isbn){
    bookRepository.deleteByIsbn(isbn);
  }

  public List<Book> getBooks(String searchOption, String searchQuery){
    List<Book> books = new ArrayList<>();
    if("title".equals(searchOption)) {
      books = bookRepository.findByTitle(searchQuery);
    } else if ("author".equals(searchOption)) {
      books = bookRepository.findByAuthor(searchQuery);
    } else if ("isbn".equals(searchOption)) {
      Book book = bookRepository.findByIsbn(searchQuery);
      if (book != null) {
        books.add(book);
      }
    } else {
      books = bookRepository.findAll();
    }
    return books;
  }

  @Transactional
  public void updateBookQuantity(List<BookQuantity> list){
    for(BookQuantity item : list) {
      int newInventory = item.getBook().getInventory() - item.getQuantity();
      bookRepository.updateBook(item.getBook().getTitle(), item.getBook().getAuthor(), item.getBook().getIsbn(), item.getBook().getPrice(), item.getBook().getDescription(), newInventory, item.getBook().getId());
    }
  }
}
