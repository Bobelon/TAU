package pl.tau.restdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tau.restdemo.domain.Car;
import pl.tau.restdemo.service.CarManager;

import javax.print.attribute.standard.Media;
import javax.servlet.annotation.WebServlet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple web api demo -- it only shows how to get some sample data
 *
 * tryout: ()
 *
 * Created by tp on 24.04.17.
 */
@RestController
public class CarApi {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    CarManager carManager;


    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }

    @RequestMapping(
            value = "/car",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Car getCar(@RequestParam(value="name", defaultValue="Nissan") String name) {
        Car p = new Car();
        p.setId(counter.incrementAndGet());
        p.setName(name);
        p.setYear(1912);
        return p;
    }

}
