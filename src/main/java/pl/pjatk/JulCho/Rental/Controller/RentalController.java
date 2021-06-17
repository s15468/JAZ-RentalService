package pl.pjatk.JulCho.Rental.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.JulCho.Rental.Model.Movies;
import pl.pjatk.JulCho.Rental.Service.RentalService;
import pl.pjatk.JulCho.Utils.Utils;

@RestController
@RequestMapping("/rental")
public class RentalController
{
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movies> GetMovie(@PathVariable String id)
    {
        Integer index = Utils.TryParseInt(id);

        if (index != null)
        {
            return rentalService.GetMovie(index);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/return/{id}")
    public ResponseEntity<Void> ReturnMovie(@PathVariable String id)
    {
        Integer index = Utils.TryParseInt(id);

        if (index != null)
        {
            return rentalService.ReturnMovie(index);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("rent/{id}")
    public ResponseEntity<Void> RentMovie(@PathVariable String id)
    {
        Integer index = Utils.TryParseInt(id);

        if (index != null)
        {
            return rentalService.RentMovie(index);
        }

        return ResponseEntity.badRequest().build();
    }
}
