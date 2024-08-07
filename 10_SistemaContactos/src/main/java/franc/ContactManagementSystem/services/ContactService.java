package franc.ContactManagementSystem.services;

import franc.ContactManagementSystem.models.Contact;
import franc.ContactManagementSystem.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> listContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact == null) return;
        contact.setDeleted(true);
        contactRepository.save(contact);
    }
}
