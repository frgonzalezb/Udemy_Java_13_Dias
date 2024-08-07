package franc.ContactManagementSystem.services;

import franc.ContactManagementSystem.models.Contact;

import java.util.List;

public interface IContactService {
    public List<Contact> listContacts();
    public Contact getContactById(Long id);
    public Contact saveContact(Contact contact);
    public void deleteContact(Long id);
}
