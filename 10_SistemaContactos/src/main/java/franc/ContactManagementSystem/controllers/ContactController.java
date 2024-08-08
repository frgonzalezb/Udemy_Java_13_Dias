package franc.ContactManagementSystem.controllers;

import franc.ContactManagementSystem.models.Contact;
import franc.ContactManagementSystem.services.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactService contactService;

    @GetMapping("/")
    public String start(ModelMap model) {
        logger.info("ContactController start()");
        List<Contact> contactList = contactService.listContacts()
                .stream()
                .filter(contact -> !contact.isDeleted())
                .toList();;
        contactList.forEach(contact -> logger.info(contact.toString()));
        model.put("contactList", contactList);
        logger.info("Contacts have been listed successfully.");
        return "index"; // index.html
    }

    @GetMapping("/add")
    public String add() {
        logger.info("ContactController add()");
        return "add"; // add.html
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("contact") Contact contact) {
        logger.info("ContactController save()");
        contactService.saveContact(contact);
        logger.info("Contact has been saved successfully: " + contact);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, ModelMap model) {
        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            logger.warn("ContactController edit() - Contact not found: " + id);
            return "redirect:/";
        }
        logger.info("ContactController edit() - Contact found: " + contact);
        model.put("contact", contact);
        logger.info("ContactController edit()");
        return "edit"; // edit.html
    }

    @PostMapping("/edit")
    public String saveEdit(@ModelAttribute("contact") Contact contact) {
        logger.info("ContactController save()");
        contactService.saveContact(contact);
        logger.info("Contact has been saved successfully: " + contact);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            logger.warn("ContactController delete() - Contact not found: " + id);
            return "redirect:/";
        }
        logger.info("ContactController delete() - Contact found: " + contact);
        contactService.deleteContact(id);
        logger.info("Contact has been deleted successfully: " + contact);
        return "redirect:/";
    }
}
