package com.example.GeekText.Repository;

        import com.example.GeekText.Model.Book;
        import org.springframework.data.mongodb.repository.MongoRepository;

        import java.util.List;
        import org.springframework.data.mongodb.repository.Query;

public interface BookRepo extends MongoRepository<Book, String> {
        List<Book> findByGenre(String genre);
        List<Book> findTop10ByOrderByCopiesSoldDesc();
        List<Book> findByRatingGreaterThanEqualOrderByRatingAsc(double rating);

        @Query("{ 'publisher': ?0 }")
        List<Book> findAllByPublisher(String publisher);

}
