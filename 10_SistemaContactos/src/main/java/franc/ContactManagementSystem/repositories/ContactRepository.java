package franc.ContactManagementSystem.repositories;

import franc.ContactManagementSystem.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository <Contact, Long> {
}
