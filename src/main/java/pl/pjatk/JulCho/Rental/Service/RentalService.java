package pl.pjatk.JulCho.Rental.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.pjatk.JulCho.Rental.Model.Movies;
import pl.pjatk.JulCho.Rental.Rest.RestTemplateConfig;

import java.net.ConnectException;

@Service
public class RentalService
{
    private String url = "http://localhost:8080/movies/";
    private final RestTemplateConfig restTemplateConfig;

    public RentalService(RestTemplateConfig restTemplateConfig) {
        this.restTemplateConfig = restTemplateConfig;
    }

    public ResponseEntity<Movies> GetMovie(Integer id)
    {
        return statusCodeHandlerForMovies(url + id);
    }

    public ResponseEntity<Void> ReturnMovie(Integer id)
    {
        return statusCodeHandlerForVoid(url + "/returnMovie/" + id);
    }

    public ResponseEntity<Void> RentMovie(Integer id)
    {
        return statusCodeHandlerForVoid(url + "/rentMovie/" + id);
    }

    private ResponseEntity<Movies> statusCodeHandlerForMovies(String url)
    {
        ResponseEntity<Movies> response = restTemplateConfig.restTemplate().getForEntity(url, Movies.class);
        var responseStatusCode = response.getStatusCode();

        if (responseStatusCode == HttpStatus.NOT_FOUND)
            return ResponseEntity.notFound().build();

        if (responseStatusCode == HttpStatus.BAD_REQUEST)
            return ResponseEntity.badRequest().build();

        if (responseStatusCode == HttpStatus.INTERNAL_SERVER_ERROR)
            return ResponseEntity.internalServerError().build();

        return response;
    }

    private ResponseEntity<Void> statusCodeHandlerForVoid(String url)
    {
        ResponseEntity<Void> response = restTemplateConfig.restTemplate().getForEntity(url, Void.class);
        var responseStatusCode = response.getStatusCode();

        if (responseStatusCode == HttpStatus.NOT_FOUND)
            return ResponseEntity.notFound().build();

        if (responseStatusCode == HttpStatus.BAD_REQUEST)
            return ResponseEntity.badRequest().build();

        if (responseStatusCode == HttpStatus.INTERNAL_SERVER_ERROR)
            return ResponseEntity.internalServerError().build();

        return response;
    }
}
