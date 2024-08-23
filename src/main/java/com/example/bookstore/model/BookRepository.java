package com.example.bookstore.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
  public List<Book> findAll();

  @Query("SELECT book FROM Book book WHERE book.isbn = :isbn")
  Book findByIsbn(@Param("isbn") String isbn);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(
      "UPDATE Book book SET book.title = :title, book.author = :author, book.isbn = :isbn, book.price = :price, book.description = :description, book.inventory = :inventory WHERE book.id = :id")
  int updateBook(
      @Param("title") String title,
      @Param("author") String author,
      @Param("isbn") String isbn,
      @Param("price") BigDecimal price,
      @Param("description") String description,
      @Param("inventory") int inventory,
      @Param("id") int id);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query("DELETE FROM Book book WHERE book.isbn = :isbn")
  void deleteByIsbn(@Param("isbn") String isbn);

  @Query("SELECT book FROM Book book WHERE UPPER(book.title) LIKE UPPER(CONCAT(:title,'%'))")
  List<Book> findByTitle(@Param("title") String title);

  @Query("SELECT book FROM Book book WHERE UPPER(book.author) LIKE UPPER(CONCAT('%', :author,'%'))")
  List<Book> findByAuthor(@Param("author") String author);
}
