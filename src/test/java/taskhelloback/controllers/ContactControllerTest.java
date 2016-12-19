package taskhelloback.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import taskhelloback.db_service.ContactService;
import taskhelloback.models.Contact;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alex on 19.12.16.
 */
public class ContactControllerTest {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactService contactService;

    private MockMvc mockMvc;
    private ArrayList<Contact> testContacts;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();

        final Contact testContact1 = new Contact("Olga");
        final Contact testContact2 = new Contact("Anna");

        testContacts = new ArrayList<Contact>();

        testContacts.add(testContact1);
        testContacts.add(testContact2);
    }

    @Test
    public void testContactFound() throws Exception {

        when(contactService.getAll()).thenReturn(testContacts);

        mockMvc.perform(get("/hello/contacts?nameFilter=^A.*$"))
                .andExpect(status().isOk());

        assertEquals(testContacts.size(), 1);
        assertEquals(testContacts.get(0).getName(), "Olga");

    }

    @Test
    public void testContactNotFound() throws Exception {

        when(contactService.getAll()).thenReturn(new ArrayList<Contact>());

        mockMvc.perform(get("/hello/contacts?nameFilter=^A.*$"))
                .andExpect(status().isNotFound());
    }
}