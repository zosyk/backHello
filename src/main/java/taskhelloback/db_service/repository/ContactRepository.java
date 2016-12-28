package taskhelloback.db_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import taskhelloback.models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @SuppressWarnings("JpaQlInspection")
    @Query("select c from Contact c")
    Page<Contact> findByOffset(Pageable Pageable);
}
