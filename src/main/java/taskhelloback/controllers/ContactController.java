package taskhelloback.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import taskhelloback.db_service.ContactService;
import taskhelloback.models.Contact;
import taskhelloback.models.Error;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex on 18.12.16.
 */
@RestController
public class ContactController {

    @Autowired
    ContactService contactService;

    public static final int LIMIT = 10;
    public static final int FILTER_CONTACTS_LIMIT = 10;

    @RequestMapping(value = "/hello/contacts", params = {"nameFilter"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Model> getAllCards(@RequestParam(value = "nameFilter", required = true, defaultValue = "") String nameFilter, final Model model, HttpServletRequest req) {

        int page;
        try {
            final String pageFromReq = req.getParameter("page");

            if (pageFromReq != null)
                page = Integer.valueOf(pageFromReq);
            else
                page = -1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            page = -1;
        }
        List<Contact> contacts = getFilterContacts(nameFilter, page);

        if (contacts == null || contacts.size() == 0) {
            final Error error = new Error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());

            model.addAttribute(error);

            return new ResponseEntity<Model>(model, HttpStatus.NOT_FOUND);
        }

        model.addAttribute("contacts", contacts);

        return new ResponseEntity<Model>(model, HttpStatus.OK);
    }

    /**
     * @param nameFilter
     * @param page - if you want all contact from db, you must don't use page parameter in get request
     * @return
     */
    private List<Contact> getFilterContacts(String nameFilter, int page) {
        List<Contact> contacts;
        int offset = page * LIMIT;

        if(page == -1) {
            contacts = contactService.getAll();
        } else {

            contacts = contactService.getContactByOffset(offset, LIMIT);
        }

        final Pattern pattern = Pattern.compile(nameFilter);

        for (Iterator<Contact> it = contacts.iterator(); it.hasNext(); ) {

            final Contact contact = it.next();

            final Matcher matcher = pattern.matcher(contact.getName());

            if (matcher.matches()) {

                it.remove();
            }
        }

        return contacts;
    }
}
