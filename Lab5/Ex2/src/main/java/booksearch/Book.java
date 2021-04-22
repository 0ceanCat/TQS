package booksearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @author wy
 * @date 2021/4/20 9:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private String category;
    private String author;
    private LocalDateTime published;
}
