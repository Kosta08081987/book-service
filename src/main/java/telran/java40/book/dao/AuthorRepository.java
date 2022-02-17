package telran.java40.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java40.book.model.Author;

public interface AuthorRepository extends JpaRepository<Author, String>{

}
