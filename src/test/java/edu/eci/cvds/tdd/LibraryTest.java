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
import java.time.temporal.ChronoUnit;


public class LibraryTest {
    private Library library;
    private Book book;
    private User user;
    private Loan loan;

    @BeforeEach
    public void setUp() {
        library = new Library();
        user = new User();
        user.setId("100");
        user.setName("juan");
        book = new Book("java", "libECI","1212");
        loan = new Loan();
    }

    //Test de el metodo addBook

    @Test
    public void testAddBookWhenNotExist() {
        assertTrue(library.addBook(book));
    }

    @Test
    public void testAddIncreaseBookWhenNotExist() {
        library.addBook(book);
        assertEquals(1, library.getBooks().get(book));
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
        assertEquals(2, library.getBooks().get(book));
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
        assertEquals(1, library.getBooks().get(book));
        assertEquals(1, library.getBooks().get(otherBook));
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
            assertEquals(user, initial.getUser());
            assertEquals(book, initial.getBook());
            assertEquals(initial.getLoanDate(),LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
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
            assertEquals("User not found", e.getMessage());
        }
    }

    @Test
    public void testNotLoanABookWhenBookNotExist() {
        library.addUser(user);
        try {
            library.loanABook(user.getId(), book.getIsbn());
            fail("Should have thrown exception");
        }catch (IllegalStateException e) {
            assertEquals("Book not found", e.getMessage());
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
            assertEquals("User already has this book", e.getMessage());
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
            assertEquals("Book is not available", e.getMessage());
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
            assertEquals("User or Book is null", e.getMessage());
        }
    }

    //test del metodo returnBook

    @Test
    public void testNotReturnABookWhenLoanIsNull(){
        library.addLoan(loan);
        try{
            library.returnLoan(null);
            fail("Should have thrown exception");
        }catch (IllegalStateException e){
            assertEquals("loan is null", e.getMessage());
        }
    }

    @Test
    public void testReturnALoan(){
        loan.setBook(book);
        loan.setUser(user);
        loan.setStatus(LoanStatus.ACTIVE);
        library.addLoan(loan);
        try{
            library.returnLoan(loan);
            assertEquals(LoanStatus.RETURNED, loan.getStatus());
            assertFalse(library.getLoans().contains(loan));
            assertEquals(loan.getReturnDate(),LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));
            assertFalse(library.getLoans().contains(loan));
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testNotReturnALoanAlreadyReturned(){
        loan.setBook(book);
        loan.setUser(user);
        loan.setStatus(LoanStatus.ACTIVE);
        library.addLoan(loan);
        loan.setBook(book);
        try{
            library.returnLoan(loan);
            library.returnLoan(loan);
            fail("Should have thrown exception");
        }catch (IllegalStateException e){
            assertEquals("loan is already returned", e.getMessage());
        }
    }

    @Test
    public void testAddIncreaseBooksWhenLoan(){
        loan.setBook(book);
        loan.setUser(user);
        loan.setStatus(LoanStatus.ACTIVE);
        Loan tLoan = new Loan();
        User uTest = new User();
        uTest.setId("101");
        uTest.setName("santiago");
        tLoan.setBook(book);
        tLoan.setUser(uTest);
        tLoan.setStatus(LoanStatus.ACTIVE);
        library.addLoan(loan);
        library.addLoan(tLoan);
        library.returnLoan(loan);
        library.returnLoan(tLoan);
        assertEquals(2, library.getBooks().get(book));
    }
}
