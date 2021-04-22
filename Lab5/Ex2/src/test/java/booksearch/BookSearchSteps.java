package booksearch;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author wy
 * @date 2021/4/20 10:30
 */
public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime getDate(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
    }

    @ParameterType("([0-9]{4})")
    public LocalDateTime getDateYYYY(String year){
        return LocalDateTime.of(Integer.parseInt(year), 1, 1, 0, 0);
    }

    @Given("(a/another) book with the title {string} and the category {string}, written by {string}, published in {getDate}")
    public void addNewBook(String title, String category, String author, LocalDateTime published) {
        Book book = new Book(title, category, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books published between {getDateYYYY} and {getDateYYYY}")
    public void setSearchParameters(LocalDateTime from, LocalDateTime to) {
        to = to.withMonth(12);
        to = to.withDayOfMonth(Month.DECEMBER.maxLength());
        result = library.findBooks(from, to);
    }

    @When("the customer searches for books by category {string}")
    public void setSearchParameters(String category) {
        result = library.findBooks(category);
    }

    @When("the customer searches for books by title {string}")
    public void searchByTitle(String title) {
        result = library.findBooksByTitle(title);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertThat(result.size(), equalTo(booksFound));
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(final int position, final String title) {
        assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }

    @When("the customer searches for books with the category {string}")
    public void theCustomerSearchesForBooksWithTheCategoryFantasy() {
    }
}
