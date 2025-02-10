package edu.eci.cvds.tdd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.Library;

import java.time.LocalDateTime;

public class LibraryTest {
    private Library library;
    private Book book;
    private User user;

    @BeforeEach
    public void setUp() {
        library = new Library();
        user = new User();
        user.setId("100");
        user.setName("juan");
        book = new Book("java", "libECI","1212");
    }

    //Test de el metodo addBook

    @Test
    public void testAddBookWhenNotExist() {
        assertTrue(library.addBook(book));
    }

    @Test
    public void testAddIncreaseBookWhenNotExist() {
        library.addBook(book);
        assertEquals(library.getBooks().get(book), 1);
    }

    @Test
    public void testAddBookWhenAlreadyExists() {
        library.addBook(book);
        assertTrue(library.addBook(book));
    }

    @Test
    public void testAddIncreaseBook() {
        library.addBook(book);
        library.addBook(book);
        assertEquals(library.getBooks().get(book), 2);
    }

    @Test
    public void testNotAddBookWhenItIsNull() {
        assertFalse(library.addBook(null));
    }

    @Test
    public void testNotAddIncreaseBook() {
        library.addBook(book);
        Book otherBook = new Book("python", "libECI","1213");
        library.addBook(otherBook);
        assertEquals(library.getBooks().get(book), 1);
        assertEquals(library.getBooks().get(otherBook), 1);
    }

    @Test
    public void testNotAddDifferentIsbnBook() {
        library.addBook(book);
        Book otherBook = new Book("java", "libECI","1213");
        assertFalse(library.addBook(otherBook));
    }

    @Test
    public void testNotAddNullIsbnBook() {
        Book otherBook1 = new Book("java", "libECI","");
        assertFalse(library.addBook(otherBook1));
        Book otherBook2 = new Book("java", "libECI",null);
        assertFalse(library.addBook(otherBook2));
    }

    @Test
    public void testNotAddBookWhenRepeatIsbn() {
        library.addBook(book);
        Book otherBook = new Book("python", "libECI","1212");
        assertFalse(library.addBook(otherBook));
    }

    //Test de el metodo loanABook

    @Test
    public void testLoanABook(){
        try {
            library.addBook(book);
            library.addUser(user);
            Loan initial = library.loanABook(user.getId(), book.getIsbn());
            assertEquals(LoanStatus.ACTIVE, initial.getStatus());
            assertEquals(LocalDateTime.now(), initial.getLoanDate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testNotLoanABookWhenUserNotExist() {
        library.addBook(book);
        try {
            library.loanABook(user.getId(), book.getIsbn());
            fail("Should have thrown exception");
        }catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "User not found");
        }
    }

    @Test
    public void testNotLoanABookWhenBookNotExist() {
        library.addUser(user);
        try {
            library.loanABook(user.getId(), book.getIsbn());
            fail("Should have thrown exception");
        }catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "Book not found");
        }
    }

    @Test
    public void testNotLoanABookWhenUserHasIt() {
        library.addBook(book);
        library.addBook(book);
        library.addUser(user);
        try {
            library.loanABook(user.getId(), book.getIsbn());
            library.loanABook(user.getId(), book.getIsbn());
            fail("Should have thrown exception");
        }catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "User already has this book");
        }
    }

    @Test
    public void testNotLoanABookWhenBookIsNotAvailable() {
        User uTest = new User();
        uTest.setId("101");
        uTest.setName("santiago");
        library.addBook(book);
        library.addUser(user);
        library.addUser(uTest);
        try {
            library.loanABook(user.getId(), book.getIsbn());
            library.loanABook(uTest.getId(), book.getIsbn());
            fail("Should have thrown exception");
        }catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "Book is not available");
        }
    }

    @Test
    public void testNotLoanABookWhenUserOrBookIsNull() {
        library.addBook(book);
        library.addUser(user);
        try{
            library.loanABook(null, null);
            fail("Should have thrown exception");
        }catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "User or Book is null");
        }
    }

    //test del metodo returnBook
}
