package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Library responsible for manage the loans and the users.
 */
public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Adds a new {@link edu.eci.cvds.tdd.library.book.Book} into the system, the book is store in a Map that contains
     * the {@link edu.eci.cvds.tdd.library.book.Book} and the amount of books available, if the book already exist the
     * amount should increase by 1 and if the book is new the amount should be 1, this method returns true if the
     * operation is successful false otherwise.
     *
     * @param book The book to store in the map.
     *
     * @return true if the book was stored false otherwise.
     */
    public boolean addBook(Book book) {
        if (book == null) { return false; }
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) { return false; }
        for (Book bk : books.keySet()) {
            if (bk.getTittle().equals(book.getTittle())
                    && bk.getAuthor().equals(book.getAuthor())
                    && !bk.getIsbn().equals(book.getIsbn())) {
                return false;
            } else if ((!bk.getTittle().equals(book.getTittle())
                    || !bk.getAuthor().equals(book.getAuthor()))
                    && bk.getIsbn().equals(book.getIsbn())) {
                return false;
            } else if (bk.equals(book)) {
                books.replace(bk, books.get(bk) + 1);
                return true;
            }
        }
        books.put(book, 1);
        return true;
    }

    /**
     * This method creates a new loan with for the User identify by the userId and the book identify by the isbn,
     * the loan should be store in the list of loans, to successfully create a loan is required to validate that the
     * book is available, that the user exist and the same user could not have a loan for the same book
     * {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE}, once these requirements are meet the amount of books is
     * decreased and the loan should be created with {@link edu.eci.cvds.tdd.library.loan.LoanStatus#ACTIVE} status and
     * the loan date should be the current date.
     *
     * @param userId id of the user.
     * @param isbn book identification.
     *
     * @return The new created loan.
     */
    public Loan loanABook(String userId, String isbn) {
        if (userId == null || isbn == null) { throw new IllegalStateException("User or Book is null"); }
        boolean userFound = false;
        boolean bookFound = false;
        User actualUser = null;
        Book actualBook = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                actualUser = user;
                userFound = true;
                break;
            }
        }
        for (Book book : books.keySet()){
            if(book.getIsbn().equals(isbn) && books.get(book) > 0){
                actualBook = book;
                bookFound = true;
                break;
            }
        }
        if(!userFound) {
            throw new IllegalStateException("User not found");
        }
        if (!bookFound){
            throw new IllegalStateException("Book not found");
        }
        for (Loan loan: loans){
            if (loan.getStatus().equals(LoanStatus.ACTIVE) && loan.getBook().equals(actualBook) && loan.getUser() != actualUser){
                throw new IllegalStateException("Book is not available");
            }
            if (loan.getUser().equals(actualUser) && loan.getBook().equals(actualBook) && loan.getStatus().equals(LoanStatus.ACTIVE)){
                throw new IllegalStateException("User already has this book");
            }
        }
        Loan loanedBook = new Loan();
        loanedBook.setUser(actualUser);
        loanedBook.setBook(actualBook);
        loanedBook.setLoanDate(LocalDateTime.now());
        loanedBook.setStatus(LoanStatus.ACTIVE);
        loans.add(loanedBook);
        return loanedBook;
    }

    /**
     * This method return a loan, meaning that the amount of books should be increased by 1, the status of the Loan
     * in the loan list should be {@link edu.eci.cvds.tdd.library.loan.LoanStatus#RETURNED} and the loan return
     * date should be the current date, validate that the loan exist.
     *
     * @param loan loan to return.
     *
     * @return the loan with the RETURNED status.
     */
    public Loan returnLoan(Loan loan) {
        //TODO Implement the login of loan a book to a user based on the UserId and the isbn.
        return null;
    }
    public boolean addUser(User user) {
        return users.add(user);
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }
}
