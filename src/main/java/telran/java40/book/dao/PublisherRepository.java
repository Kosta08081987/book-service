package telran.java40.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java40.book.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String>{

}
