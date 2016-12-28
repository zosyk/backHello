package taskhelloback.db_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskhelloback.Utils.CustomPageable;
import taskhelloback.db_service.repository.ContactRepository;
import taskhelloback.models.Contact;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository repository;

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public List<Contact> getContactByOffset(int offset, int limit) {
        return repository.findByOffset(new CustomPageable(offset, limit)).getContent();
    }

}
