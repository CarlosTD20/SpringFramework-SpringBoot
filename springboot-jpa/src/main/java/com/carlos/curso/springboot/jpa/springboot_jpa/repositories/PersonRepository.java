package com.carlos.curso.springboot.jpa.springboot_jpa.repositories;

import com.carlos.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.carlos.curso.springboot.jpa.springboot_jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

//    @Query("select p from Person p where p.id in ?1")
    @Query("select p from Person p where p.id not in ?1")
    List<Person> getPersonByIds(List<Long> ids);

    @Query("select p from Person p where p.id in (1, 2, 5)")
    List<Person> getPersonByIds();

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    Optional<Person> getLastRegistration();

    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    List<Object[]> getShorterName();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    Object getResumeAggregationFunction();

    @Query("select max(length(p.name)) from Person p")
    Integer getMaxLengthName();

    @Query("select min(length(p.name)) from Person p")
    Integer getMinLengthName();

    @Query("select p.name, length(p.name) from Person p")
    List<Object[]> getPersonNameLength();

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select count(p) from Person p")
    Long getTotalPerson();

    List<Person> findAllByOrderByNameAscLastnameDesc();

    List<Person> findAllByOrderByNameDesc();

    @Query("select p from Person p order by p.name, p.lastname desc")
    List<Person> getAllOrderer();

    List<Person>findByIdBetween(Long id1, Long id2);
    List<Person>findByNameBetween(String  name1, String  name2);

    List<Person>findByIdBetweenOrderByIdDesc(Long id1, Long id2);
    List<Person>findByNameBetweenOrderByNameDesc(String  name1, String  name2);

    List<Person>findByNameBetweenOrderByNameDescLastnameAsc(String  name1, String  name2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc")
    List<Person> findPersonBetweenName(String c1, String c2);
    @Query("select p from Person p where p.name between 'J' and 'P'")
    List<Person> findPersonBetweenName();

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name, p.lastname desc")
    List<Person> findPersonBetween(Long id1, Long id2);
    @Query("select p from Person p where p.id between 2 and 5")
    List<Person> findPersonBetween();

    @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
    List<Object[]> findAllPersonDataListCase();

    @Query("select lower(concat(p.name, ' ', p.lastname)) from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select upper(p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullNameConcat();

//    @Query("select concat(p.name, ' ', p.lastname) from Person p")
//    List<String> findAllFullNameConcat();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select new com.carlos.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonalizedPerson();

    @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneNmae(String name);

    @Query("select p from Person p where p.name like %?1% ")
    Optional<Person> findOneLikeNmae(String name);

    Optional<Person> findByName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixtPerson();

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Optional<Object> obtenerPersonDataById(long id);

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);
}
