package ds.project.service;

import ds.project.dto.PersonRegisterDto;
import ds.project.mapper.PersonRegisterMapper;
import ds.project.model.person.Person;
import ds.project.model.person.PersonRole;
import ds.project.repository.PersonRepository;
import ds.project.repository.PersonRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

import static org.mockito.Mockito.*;

class PersonServiceTest {
    @Mock
    PersonRepository personRepository;
    @Mock
    PersonRoleRepository personRoleRepository;
    @Mock
    PasswordEncoder bCryptPasswordEncoder;
    @Mock
    PersonRegisterMapper personRegisterMapper;
    @InjectMocks
    PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        when(personRepository.findByUsername(anyString())).thenReturn(new Person());

        UserDetails result = personService.loadUserByUsername("username");
        Assertions.assertEquals(new Person(), result);
    }

    @Test
    void testRegisterPerson() {
        when(personRepository.save(any(Person.class))).thenReturn(new Person());
        when(personRoleRepository.findByName(anyString())).thenReturn(null);
        when(personRoleRepository.save(any(PersonRole.class))).thenReturn(new PersonRole());
        when(bCryptPasswordEncoder.encode(any(CharSequence.class))).thenReturn("encodeResponse");
        when(personRegisterMapper.toEntity(any(PersonRegisterDto.class))).thenReturn(new Person());

        Person result = personService.registerPerson(new PersonRegisterDto("username", "name", "email", "phone", "telegram", "addInfo", new Date(0, 0, 0), "city", "password"));
        Assertions.assertEquals(new Person(), result);
    }
}
