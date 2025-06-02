package com.carlos.curso.springboot.jpa.springboot_jpa_relationship;

import com.carlos.curso.springboot.jpa.springboot_jpa_relationship.entities.*;
import com.carlos.curso.springboot.jpa.springboot_jpa_relationship.repositories.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		manyToManyRemoveBidireccionalFindById();
	}

	@Transactional
	public void manyToManyRemoveBidireccionalFindById() {
		Optional<Student> studentOpt1 = this.studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOpt2 = this.studentRepository.findOneWithCourses(2L);

		Student student1 = studentOpt1.get();
		Student student2 = studentOpt2.get();

		Course course1 = this.courseRepository.findOneWithStudents(1L).get();
		Course course2 = this.courseRepository.findOneWithStudents(2L).get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		this.studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToManyBidireccionalFindById() {
		Optional<Student> studentOpt1 = this.studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOpt2 = this.studentRepository.findOneWithCourses(2L);

		Student student1 = studentOpt1.get();
		Student student2 = studentOpt2.get();

		Course course1 = this.courseRepository.findOneWithStudents(1L).get();
		Course course2 = this.courseRepository.findOneWithStudents(2L).get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		this.studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDB = this.studentRepository.findOneWithCourses(1L);
		if (studentOptionalDB.isPresent()) {
			Student studentDB = studentOptionalDB.get();
			Optional<Course> courseOptionalDB = this.courseRepository.findOneWithStudents(1L);

			if (courseOptionalDB.isPresent()) {
				Course courseDB = courseOptionalDB.get();
				studentDB.removeCoruse(courseDB);

				this.studentRepository.save(studentDB);
				System.out.println(studentDB);
			}
		}
	}

	@Transactional
	public void manyToManyBidireccionalRemove() {
		Student student1 = new Student("Fede", "Lorente");
		Student student2 = new Student("Marki", "Lokuras");

		Course course1 = new Course("Curso de Java master", "Carlos");
		Course course2 = new Course("Curso de Spring Boot", "Carlos");

//		student1.setCourses(Set.of(course1, course2));
//		student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		this.studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDB = this.studentRepository.findOneWithCourses(3L);
		if (studentOptionalDB.isPresent()) {
			Student studentDB = studentOptionalDB.get();
			Optional<Course> courseOptionalDB = this.courseRepository.findOneWithStudents(3L);

			if (courseOptionalDB.isPresent()) {
				Course courseDB = courseOptionalDB.get();
				studentDB.removeCoruse(courseDB);

				this.studentRepository.save(studentDB);
				System.out.println(studentDB);
			}
		}
	}

	@Transactional
	public void manyToManyBidireccional() {
		Student student1 = new Student("Fede", "Lorente");
		Student student2 = new Student("Marki", "Lokuras");

		Course course1 = new Course("Curso de Java master", "Carlos");
		Course course2 = new Course("Curso de Spring Boot", "Carlos");

//		student1.setCourses(Set.of(course1, course2));
//		student2.setCourses(Set.of(course2));

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		this.studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToManyRemove() {
		Student student1 = new Student("Fede", "Lorente");
		Student student2 = new Student("Marki", "Lokuras");

		Course course1 = new Course("Curso de Java master", "Carlos");
		Course course2 = new Course("Curso de Spring Boot", "Carlos");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		this.studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDB = this.studentRepository.findOneWithCourses(3L);
		if (studentOptionalDB.isPresent()) {
			Student studentDB = studentOptionalDB.get();
			Optional<Course> courseOptionalDB = this.courseRepository.findById(3L);

			if (courseOptionalDB.isPresent()) {
				Course courseDB = courseOptionalDB.get();
				studentDB.getCourses().remove(courseDB);

				this.studentRepository.save(studentDB);
				System.out.println(studentDB);
			}
		}
	}

	@Transactional
	public void manyToManyRemoveFind() {
		Optional<Student> studentOpt1 = this.studentRepository.findById(1L);
		Optional<Student> studentOpt2 = this.studentRepository.findById(2L);

		Student student1 = studentOpt1.get();
		Student student2 = studentOpt2.get();

		Course course1 = this.courseRepository.findById(1L).get();
		Course course2 = this.courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		this.studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDB = this.studentRepository.findOneWithCourses(1L);
		if (studentOptionalDB.isPresent()) {
			Student studentDB = studentOptionalDB.get();
			Optional<Course> courseOptionalDB = this.courseRepository.findById(1L);

			if (courseOptionalDB.isPresent()) {
				Course courseDB = courseOptionalDB.get();
				studentDB.getCourses().remove(courseDB);

				this.studentRepository.save(studentDB);
				System.out.println(studentDB);
			}
		}
	}

	@Transactional
	public void manyToManyFindById() {
		Optional<Student> studentOpt1 = this.studentRepository.findById(1L);
		Optional<Student> studentOpt2 = this.studentRepository.findById(2L);

		Student student1 = studentOpt1.get();
		Student student2 = studentOpt2.get();

		Course course1 = this.courseRepository.findById(1L).get();
		Course course2 = this.courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		this.studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToMany() {
		Student student1 = new Student("Fede", "Lorente");
		Student student2 = new Student("Marki", "Lokuras");

		Course course1 = new Course("Curso de Java master", "Carlos");
		Course course2 = new Course("Curso de Spring Boot", "Carlos");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		this.studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void oneToOneBidireccionalFindById() {
		Optional<Client> clientOpt = this.clientRepository.findOne(2L);

		clientOpt.ifPresent( client -> {
		ClientDetails clientDetails = new ClientDetails(true, 5000);

		client.setClientDetails(clientDetails);
//		clientDetails.setClient(client);
		this.clientRepository.save(client);

		System.out.println(client);
		});
	}

	@Transactional
	public void oneToOneBidireccional() {
		Client client = new Client("Erba", "Pura");

		ClientDetails clientDetails = new ClientDetails(true, 5000);

		client.setClientDetails(clientDetails);
//		clientDetails.setClient(client);
		this.clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void oneToOneFindById() {
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		this.clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOpt = this.clientRepository.findOne(2L);
		clientOpt.ifPresent( client -> {
			client.setClientDetails(clientDetails);
			this.clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void oneToOne() {
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		this.clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		this.clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeInvoiceBidireccional() {
		Client client = new Client("Fran", "Espin");

		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		this.clientRepository.save(client);
		System.out.println(client);


		Optional<Client> optionalClientBd = this.clientRepository.findOne(3L);

		optionalClientBd.ifPresent(clientDb -> {
			Invoice invoice3 = new Invoice("compras de la casa", 5000L);
			invoice3.setId(1L);

//			Optional<Invoice> invoiceOptional = Optional.of(invoice3);
			Optional<Invoice> invoiceOptional = this.invoiceRepository.findById(2L);
			invoiceOptional.ifPresent( invoice -> {
				clientDb.removeInvoice(invoice);

				this.clientRepository.save(clientDb);
				System.out.println(clientDb);
			});
		});
	}

	@Transactional
	public void removeInvoiceBidireccionalFindById() {
		Optional<Client> optionalClient = this.clientRepository.findOne(1L);

		optionalClient.ifPresent( client -> {
			Invoice invoice1 = new Invoice("compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("compras de oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			this.clientRepository.save(client);
			System.out.println(client);
		});

		Optional<Client> optionalClientBd = this.clientRepository.findOne(1L);

		optionalClientBd.ifPresent(client -> {
			Invoice invoice3 = new Invoice("compras de la casa", 5000L);
			invoice3.setId(1L);

//			Optional<Invoice> invoiceOptional = this.invoiceRepository.findById(2L);
			Optional<Invoice> invoiceOptional = Optional.of(invoice3);
			invoiceOptional.ifPresent( invoice -> {
//				client.getInvoices().remove(invoice);
//				invoice.setClient(null);
				client.removeInvoice(invoice);

				this.clientRepository.save(client);
				System.out.println(client);
			});
		});
	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {
		Optional<Client> optionalClient = this.clientRepository.findOne(1L);
		optionalClient.ifPresent( client -> {
			Invoice invoice1 = new Invoice("compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("compras de oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			this.clientRepository.save(client);
			System.out.println(client);
		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {
		Client client = new Client("Fran", "Espin");

		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de oficina", 8000L);

//		client.setInvoices(Arrays.asList(invoice1, invoice2));
		client.addInvoice(invoice1).addInvoice(invoice2);

		this.clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById() {
//		Optional<Client> clientOpt = this.clientRepository.findById(2L);
		Optional<Client> clientOpt = this.clientRepository.findOneWithAddresses(2L);
		clientOpt.ifPresent(client ->  {
			Address address1 = new Address("El Verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addressSet = new HashSet<>();
			addressSet.add(address1);
			addressSet.add(address2);
			client.setAddresses(addressSet);

			this.clientRepository.save(client);
			System.out.println(client);

			Optional<Client> optionalClient = this.clientRepository.findById(2L);
			optionalClient.ifPresent(c -> {
				c.getAddresses().remove(address2);
				this.clientRepository.save(c);
				System.out.println(c);
			});
		});
	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Espin");

		Address address1 = new Address("El Verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		this.clientRepository.save(client);
		System.out.println(client);

		Optional<Client> optionalClient = this.clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			this.clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void OnteToManyFindById() {
		Optional<Client> clientOpt = this.clientRepository.findById(2L);

		clientOpt.ifPresent(client ->  {
			Address address1 = new Address("El Verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addressSet = new HashSet<>();
			addressSet.add(address1);
			addressSet.add(address2);
			client.setAddresses(addressSet);

			this.clientRepository.save(client);
			System.out.println(client);
		});
	}

	@Transactional
	public void OnteToMany() {
		Client client = new Client("Fran", "Espin");
		Address address1 = new Address("El Verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		this.clientRepository.save(client);
		System.out.println(client);
	}


	@Transactional
	public void menyToOneFindByIdClient() {
		Optional<Client> clientOpt = this.clientRepository.findById(2L);

		if (clientOpt.isPresent()) {
			Client client = clientOpt.orElseThrow();

			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = this.invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}
	}

	@Transactional
	public void menyToOne() {
		Client client = new Client("John", "Doe");
		this.clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = this.invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}
}
