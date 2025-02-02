package hu.devoli;

import io.micronaut.http.annotation.*;

@Controller("/cardsbackend")
public class CardsbackendController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}