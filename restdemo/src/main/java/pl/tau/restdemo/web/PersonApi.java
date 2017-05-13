package pl.tau.restdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tau.restdemo.domain.Person;
import pl.tau.restdemo.service.PersonManager;

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
public class PersonApi {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonManager personManager;


    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }

    @RequestMapping(
            value = "/person",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Person getPerson(@RequestParam(value="name", defaultValue="Rzedziwoj") String name) {
        Person p = new Person();
        p.setId(counter.incrementAndGet());
        p.setName(name);
        p.setYob(1912);
        return p;
    }

}