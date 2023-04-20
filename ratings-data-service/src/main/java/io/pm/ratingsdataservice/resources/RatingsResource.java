package io.pm.ratingsdataservice.resources;

import io.pm.ratingsdataservice.model.Rating;
import io.pm.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
      // testing timeout functionality implemented in movie-catalog-service
       try {
           Thread.sleep(6000);
       }
       catch (Exception e)
       {

       }

        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;

    }

}
