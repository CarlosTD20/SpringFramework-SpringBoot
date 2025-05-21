package com.carlos.curso.springboot.jpa.springboot_jpa;

import com.carlos.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.carlos.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.carlos.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		list();
//		findOne();
//		create();
//		update();
//		delete();
//		delete2();
		//personalizedQuery();
//		personalizedQuery2();
//		personalizedQueriesDistinct();
//		personalizedQueriesConcatUpperCaseLowerCase();
//		personalizedQueryBetween();
//		queriesFuncionesAgregacion();
//		subQueries();
		whereIn();
	}

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("======= Consulta where in =======");
//		List<Person> persons = this.personRepository.getPersonByIds();
		List<Person> persons = this.personRepository.getPersonByIds(Arrays.asList(1L, 3L , 5L));
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("======= Consulta con el nombre mas corto y su largo =======");
		List<Object[]> registers = (List<Object[]>) this.personRepository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name: " + name + ", length: " + length);
		});

		System.out.println("======= Consulta para obtener el último registro de Persona =======");
		Optional<Person> lastPerson = this.personRepository.getLastRegistration();
		lastPerson.ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void queriesFuncionesAgregacion() {
		System.out.println("======= Consulta con el nombre mas corto =======");
		Integer smallName = this.personRepository.getMinLengthName();
		System.out.println(smallName);

		System.out.println("======= Consulta con el nombre mas largo =======");
		Integer bigName = this.personRepository.getMaxLengthName();
		System.out.println(bigName);

		System.out.println("======= Consulta el total re registros de la tabla Persona =======");
		Long count = this.personRepository.getTotalPerson();
		System.out.println(count);

		System.out.println("======= Consulta el valor mínimo del ID =======");
		Long min = this.personRepository.getMinId();
		System.out.println(min);

		System.out.println("======= Consulta el valor máximo del ID =======");
		Long max = this.personRepository.getMaxId();
		System.out.println(max);

		System.out.println("======= Consulta la longitud del campo nombre =======");
		List<Object[]> regs = this.personRepository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name= " + name + ", length= " + length);
		});

		System.out.println("======= Consultas resumen de funciones de agregación min, max, sum, avg, conut =======");
		Object[] resum = (Object[]) this.personRepository.getResumeAggregationFunction();
		System.out.println("min: " + resum[0] + ", max: " + resum[1] + ", sum: " + resum[2] + ", avg: " + resum[3] + ", count: " + resum[4]);
    }

	@Transactional(readOnly = true)
	public void personalizedQueryBetween() {
		System.out.println("======= Consulta personas entre los IDs 2 y 5 =======");
//		List<Person> personList = this.personRepository.findPersonBetween(2L, 5L);
//		List<Person> personList = this.personRepository.findByIdBetween(2L, 5L);
		List<Person> personList = this.personRepository.findByIdBetweenOrderByIdDesc(2L, 5L);
		personList.forEach(System.out::println);

		System.out.println("======= Consulta nombre personas entre la J y la P =======");
//		List<Person> personListNames = this.personRepository.findPersonBetweenName("J", "P");
//		List<Person> personListNames = this.personRepository.findByNameBetween("J", "P");
//		List<Person> personListNames = this.personRepository.findByNameBetweenOrderByNameDesc("J", "P");
		List<Person> personListNames = this.personRepository.findByNameBetweenOrderByNameDescLastnameAsc("J", "P");
		personListNames.forEach(System.out::println);

//		List<Person> getPersons = this.personRepository.getAllOrderer();
//		List<Person> getPersons = this.personRepository.findAllByOrderByNameDesc();
		List<Person> getPersons = this.personRepository.findAllByOrderByNameAscLastnameDesc();
		getPersons.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperCaseLowerCase() {
		System.out.println("======= Consulta nombres y apellidos de personas =======");
		List<String> fullnames = this.personRepository.findAllFullNameConcat();
		fullnames.forEach(System.out::println);

		System.out.println("======= Consulta nombres y apellidos de personas en mayusculas =======");
		List<String> fullnamesUpper = this.personRepository.findAllFullNameConcatUpper();
		fullnamesUpper.forEach(System.out::println);

		System.out.println("======= Consulta nombres y apellidos de personas en minusculas =======");
		List<String> fullnamesLower = this.personRepository.findAllFullNameConcatLower();
		fullnamesLower.forEach(System.out::println);

		System.out.println("======= Consulta Personas alternando mayusculas y minusculas =======");
		List<Object[]> fullnamesMixCase = this.personRepository.findAllPersonDataListCase();
		fullnamesMixCase.forEach(personReg -> System.out.println("id: " + personReg[0] + " , nombre: " + personReg[1] + " ,apellido: " + personReg[2] + " , lenguage de programación: " + personReg[3]));

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("======= Consulta con nombres de personas =======");
		List<String> names = this.personRepository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("======= Consulta con nombres únicos de personas Distinct =======");
		List<String> namesDisc = this.personRepository.findAllNamesDistinct();
		namesDisc.forEach(System.out::println);

		System.out.println("======= Consulta con lenguage de programación únicos Distinct =======");
		List<String> programmingLanguage = this.personRepository.findAllProgrammingLanguageDistinct();
		programmingLanguage.forEach(System.out::println);

		System.out.println("======= Consulta del total de lenguages de programación únicos Distinct =======");
		Long plSize = this.personRepository.findAllProgrammingLanguageDistinctCount();
		System.out.println("Total of programming language: " + plSize);

	}

	@Transactional(readOnly = true)
	public void personalizedQuery2() {
		System.out.println("======= Consulta Objeto Persona y Lenguage de Programación =======");
		List<Object[]> personRegs = this.personRepository.findAllMixtPerson();
		personRegs.forEach( reg -> System.out.println("programminglanguage: " + reg[1] + " ,person: " + reg[0].toString()) );

		System.out.println("======= Consulta que puebla y devuelve objeto Person de una instancia personalizada =======");
		List<Person> personList = this.personRepository.findAllObjectPersonalizedPerson();
		personList.forEach(System.out::println);

		System.out.println("======= Consulta que puebla y devuelve objeto PersonDto de una calse personalizada =======");
		List<PersonDto> personDtoList = this.personRepository.findAllPersonDto();
		personDtoList.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQuery() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona: ");
		Long id = sc.nextLong();

		System.out.println("======= Monstrando solo el Nombre =======");
		String name = this.personRepository.getNameById(id);
		System.out.println(name);

		System.out.println("======= Monstrando solo el id =======");
		Long idDB = this.personRepository.getIdById(id);
		System.out.println(idDB);

		System.out.println("======= Monstrando el Nombre y Apellido con concat =======");
		String fullname = this.personRepository.getFullNameById(id);
		System.out.println(fullname);

		System.out.println("======= consulta por campos personalizados por el id =======");
		Optional<Object> personOpt = this.personRepository.obtenerPersonDataById(id);
		if (personOpt.isPresent()) {
			Object[] personReg = (Object[]) personOpt.orElseThrow();
			System.out.println("id: " + personReg[0] + " , nombre: " + personReg[1] + " ,apellido: " + personReg[2] + " , lenguage de programación: " + personReg[3]);
		}
		System.out.println("======= consulta por campos personalizados por el id =======");
		List<Object[]> personlist = this.personRepository.obtenerPersonDataList();
		personlist.forEach( person -> System.out.println("nombre: " + person[0] + " ,lenguage de programación: " + person[1] ));

		sc.close();
	}

	@Transactional
	public void delete2() {
		this.personRepository.findAll().forEach(System.out::println);

		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona: ");
		Long id = sc.nextLong();

		Optional<Person> personInDB = this.personRepository.findById(id);
//		personInDB.ifPresent(person -> this.personRepository.delete(person));
		personInDB.ifPresentOrElse(
				personRepository::delete,
				() -> System.out.println("No se ha encontrado la persona con el id: " + id )
		);

		this.personRepository.findAll().forEach(System.out::println);
		sc.close();
	}

	@Transactional
	public void delete() {
		this.personRepository.findAll().forEach(System.out::println);

		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona: ");
		Long id = sc.nextLong();
		this.personRepository.deleteById(id);

		this.personRepository.findAll().forEach(System.out::println);

		sc.close();
	}

	@Transactional
	public void update() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese el ID de la persona: ");
		Long id = sc.nextLong();

		Optional<Person> findPerson = this.personRepository.findById(id);

//		findPerson.ifPresent(person -> {
		if (findPerson.isPresent()) {
			Person person = findPerson.orElseThrow();
			System.out.println(person);
			System.out.println("Ingrese el Lenguage de Programación: ");
			String programmingLanguage = sc.next();
			person.setProgrammingLanguage(programmingLanguage);

			Person persondDB = this.personRepository.save(person);
			System.out.println(persondDB);
		} else {
			System.out.println("El usuario con el id: " + id + " no existe!!!");
		}
//		});
		sc.close();
	}

	@Transactional
	public void create() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre: ");
		String name = sc.next();
		System.out.println("Apellido: ");
		String lastname = sc.next();
		System.out.println("Lenguage de Programación: ");
		String programmingLanguage = sc.next();
		sc.close();

		Person person = new Person(null, name, lastname, programmingLanguage);
		Person personNew = this.personRepository.save(person);
		System.out.println(personNew);

		this.personRepository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne() {
//		Person person = null;
//		Optional<Person> optionalPerson = this.personRepository.findById(1L);
//		if (!optionalPerson.isEmpty()) {
//		if (optionalPerson.isPresent()) {
//			person = optionalPerson.get();
//		}
//		System.out.println(person);

//		this.personRepository.findById(1L).ifPresent(person -> System.out.println(person));
//		this.personRepository.findById(1L).ifPresent(System.out::println);
//		this.personRepository.findOne(1L).ifPresent(System.out::println);
//		this.personRepository.findOneNmae("Pepe").ifPresent(System.out::println);
//		this.personRepository.findOneLikeNmae("ria").ifPresent(System.out::println);
//		this.personRepository.findByName("Maria").ifPresent(System.out::println);
		this.personRepository.findByNameContaining("ria").ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void list() {
		//		List<Person> personList = (List<Person>) this.personRepository.findAll();
//		List<Person> personList = (List<Person>) this.personRepository.findByProgrammingLanguage("Java");
//		List<Person> personList = (List<Person>) this.personRepository.buscarByProgrammingLanguage("Java", "Josefa");
		List<Person> personList = (List<Person>) this.personRepository.findByProgrammingLanguageAndName("Java", "Josefa");

		personList.forEach(System.out::println);

//		List<Object[]> personsValues = this.personRepository.obtenerPersonData();
		List<Object[]> personsValues = this.personRepository.obtenerPersonData( "Pepe");
		personsValues.forEach(person -> {
			System.out.println(person[0] + " es experot en " + person[1]);
		});
	}
}
