package taskhelloback.db_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskhelloback.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
