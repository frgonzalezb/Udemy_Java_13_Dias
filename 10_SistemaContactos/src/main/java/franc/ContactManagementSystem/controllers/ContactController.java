package franc.ContactManagementSystem.controllers;

import franc.ContactManagementSystem.models.Contact;
import franc.ContactManagementSystem.services.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String add(ModelMap model) {
        logger.info("ContactController add()");
        model.put("contact", new Contact());
        logger.info("Contact has been added successfully: " + model.get("contact"));
        return "add"; // add.html
    }
}
