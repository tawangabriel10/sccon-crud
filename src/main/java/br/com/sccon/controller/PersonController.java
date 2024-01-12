package br.com.sccon.controller;

import br.com.sccon.entity.Person;
import br.com.sccon.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Api(value = "person", protocols = "https")
@RestController
@RequestMapping(path = "person")
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation(value="Find all Person", notes="Responsible for get all person")
    @GetMapping()
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = this.personService.findAll();
        return ResponseEntity.ok().body(persons);
    }

    @ApiOperation(value="Insert Person", notes="Responsible insert a person from map")
    @PostMapping()
    public ResponseEntity<Person> insert(@RequestBody Person person) throws URISyntaxException {
        Person personSaved = this.personService.insert(person);
        return ResponseEntity.created(new URI("/person/" + person.getId())).body(personSaved);
    }

    @ApiOperation(value="Delete person by id", notes="Responsible for delete a person by id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.personService.delete(id);
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation(value="Update Person", notes="Responsible for update a person by id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Person> update(@PathVariable("id") Long id, @RequestBody Person person) throws URISyntaxException {
        Person personSaved = this.personService.update(id, person);
        return ResponseEntity.created(new URI("/person/" + person.getId())).body(person);
    }

    @ApiOperation(value="Change attribute Person", notes="Responsible for change attribute of person by id")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Person> change(@PathVariable("id") Long id, @RequestBody Map<String, Objects> fields) throws URISyntaxException {
        Person person = this.personService.change(id, fields);
        return ResponseEntity.created(new URI("/person/" + person.getId())).body(person);
    }

    @ApiOperation(value="Find Person By Id", notes="Responsible for get a person by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {
        Person person = this.personService.findById(id);
        return ResponseEntity.ok().body(person);
    }

    @ApiOperation(value="Find Age of Person by Id", notes="Responsible for get the age of person by id")
    @GetMapping(value = "/{id}/age")
    public ResponseEntity<Long> findAgeById(@PathVariable("id") Long id, @RequestParam(value = "output") String output) {
        Long period = this.personService.findAgeById(id, output);
        return ResponseEntity.ok().body(period);
    }

    @ApiOperation(value="Find Salary of Person by Id", notes="Responsible for get all person")
    @GetMapping(value = "/{id}/salary")
    public ResponseEntity<Double> findSalaryById(@PathVariable("id") Long id, @RequestParam(value = "output") String output) {
        double salary = this.personService.findSalaryById(id, output);
        return ResponseEntity.ok().body(salary);
    }
}
