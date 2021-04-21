package booksearch;

import io.cucumber.java.ParameterType;
import io.cucumber.java.bs.I;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;

import java.time.LocalDate;
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
    public LocalDateTime getDate(String day, String month, String year){
        int m = Integer.getInteger(month);

        return LocalDateTime.of(Integer.parseInt(year), Month.AUGUST, Integer.parseInt(day), 0, 0);
    }

    @Given("a book with the title {string}, written by {string}, published in {getDate}")
    public void addNewBook(String title, String author, LocalDateTime published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @And("another book with the title {string}, written by {string}, published in {int}{int}{int}")
    public void anotherBookWithTheTitleSomeOtherBookWrittenByTimTomsonPublishedIn(int arg0, int arg1, int arg2) {
    }

    @When("the customer searches for books published between (\\d+) and (\\d+)$")
    public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
        result = library.findBooks(from, to);
    }

    @Then("(\\d+) books should have been found$")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertThat(result.size(), equalTo(booksFound));
    }

    @Then("Book (\\d+) should have the title '(.+)'$")
    public void verifyBookAtPosition(final int position, final String title) {
        assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }

}
