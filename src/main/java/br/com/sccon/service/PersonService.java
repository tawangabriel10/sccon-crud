package br.com.sccon.service;

import br.com.sccon.entity.Person;
import br.com.sccon.exceptions.ConflitException;
import br.com.sccon.exceptions.ObjectNotFoundException;
import br.com.sccon.exceptions.PreconditionErrorException;
import br.com.sccon.util.Constants;
import br.com.sccon.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private Map<Long, Person> map = new HashMap<>();

    public PersonService() {
        Person person1 = new Person(1L, "José", DateUtil.parseDate("2000-04-06"), DateUtil.parseDate("2020-05-10"));
        Person person2 = new Person(2L, "Maria", DateUtil.parseDate("2002-10-12"), DateUtil.parseDate("2012-01-22"));
        Person person3 = new Person(3L, "Pedro", DateUtil.parseDate("1993-02-02"), DateUtil.parseDate("2002-12-03"));

        map.put(person1.getId(), person1);
        map.put(person2.getId(), person2);
        map.put(person3.getId(), person3);
    }

    /**
     * Find All Person
     * @return
     */
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        for (Long id : map.keySet()) {
            personList.add(map.get(id));
        }
        return personList;
    }

    /**
     * Insert Person
     * @param person
     * @return
     */
    public Person insert(Person person) {
        if (Objects.nonNull(person.getId()) && Objects.nonNull(map.get(person.getId()))) {
            throw new ConflitException("Person already exists by id");
        }

        if (Objects.isNull(person.getId())) {
            person.setId(getMajorId() + 1);
        }

        map.put(person.getId(), person);
        return person;
    }

    /**
     * Delete Person By Id
     * @param id
     */
    public void delete(Long id) {
        valideteId(id);
        map.remove(id);
    }

    /**
     * Update Person By Id
     * @param id
     * @param person
     * @return
     */
    public Person update(Long id, Person person) {
        valideteId(id);
        map.put(id, person);
        person.setId(id);
        return person;
    }

    /**
     * Find Person By Id
     * @param id
     * @return
     */
    public Person findById(Long id) {
        valideteId(id);
        return map.get(id);
    }

    /**
     * Get Id Maijor
     * @return
     */
    private Long getMajorId() {
        List<Long> ids = map.keySet().stream().sorted().collect(Collectors.toList());
        return ids.get(ids.size() - 1);
    }

    /**
     * Find Age of Person By Id
     * @param id
     * @param output
     * @return
     */
    public Long findAgeById(Long id, String output) {
        valideteId(id);
        Person person = map.get(id);
        LocalDate startDate = person.getDataNascimento().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate endDate = LocalDate.now();
        if (output.equals(Constants.DAYS)) {
            return ChronoUnit.DAYS.between(startDate, endDate);
        } else if (output.equals(Constants.MONTHS)) {
            return ChronoUnit.MONTHS.between(startDate, endDate);
        } else if (output.equals(Constants.YEARS)) {
            return ChronoUnit.YEARS.between(startDate, endDate);
        } else {
            throw new PreconditionErrorException("Invalid param output");
        }
    }

    /**
     * Find Salary of Person By Id
     * @param id
     * @param output
     * @return
     */
    public double findSalaryById(Long id, String output) {
        valideteId(id);
        Person person = map.get(id);
        LocalDate startDate = person.getDataAdmissao().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate endDate = LocalDate.now();
        Long diffPeriod = ChronoUnit.YEARS.between(startDate, endDate);
        double result = Constants.INITIAL_SALARY;
        if (diffPeriod >= 1) {
            for (int i = 0; i < diffPeriod; i++) {
                result += (result * 0.08) + 500;
            }
        }
        if (output.equals(Constants.MIN)) {
            result = result / Constants.MIN_SALARY;
            BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
            return bd.doubleValue();
        } else if (output.equals(Constants.FULL)){
            BigDecimal bd = new BigDecimal(result).setScale(2, RoundingMode.HALF_EVEN);
            return bd.doubleValue();
        } else {
            throw new PreconditionErrorException("Invalid param output");
        }
    }

    /**
     * Change attribute Person By Id
     * @param id
     * @param fields
     * @return
     */
    public Person change(Long id, Map<String, Objects> fields) {
        valideteId(id);
        Person person = map.get(id);
        fields.keySet()
            .forEach(k -> {
                Method method = ReflectionUtils.findMethod(Person.class, "set" + StringUtils.capitalize(k));
                if (method != null) {
                    ReflectionUtils.invokeMethod(method, person, fields.get(k));
                }
            });
        map.putIfAbsent(id, person);
        return person;
    }

    /**
     * Velidate If id Exists
     * @param id
     */
    private void valideteId(Long id) {
        if (Objects.isNull(map.get(id))) {
            throw new ObjectNotFoundException("Person not found");
        }
    }
}
