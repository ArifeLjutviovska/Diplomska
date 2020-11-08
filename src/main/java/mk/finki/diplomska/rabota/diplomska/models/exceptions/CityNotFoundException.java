package mk.finki.diplomska.rabota.diplomska.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException() {
        super(CityNotFoundException.class.getSimpleName());
    }
}
