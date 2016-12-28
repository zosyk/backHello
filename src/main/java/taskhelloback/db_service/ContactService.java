package taskhelloback.db_service;


import taskhelloback.models.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAll();

    List<Contact> getContactByOffset(int offset, int limit);


}
