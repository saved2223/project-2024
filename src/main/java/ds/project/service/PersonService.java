package ds.project.service;

import ds.project.dto.MessageDto;
import ds.project.dto.PersonRegisterDto;
import ds.project.mapper.PersonRegisterMapper;
import ds.project.model.Message;
import ds.project.model.person.Person;
import ds.project.model.person.PersonRole;
import ds.project.repository.PersonRepository;
import ds.project.repository.PersonRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService  implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRoleRepository personRoleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PersonRegisterMapper personRegisterMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return person;
    }

    public Person registerPerson(PersonRegisterDto personRegisterDto) {
        Person person = personRegisterMapper.toEntity(personRegisterDto);
        //todo проверка на существующего
        Optional<PersonRole> personRole = personRoleRepository.findByName("ROLE_USER");
        if (personRole.isPresent()){
            person.setRoles(Collections.singleton(personRole.get()));
        }
        else {
            PersonRole personRole1 = new PersonRole(1, "ROLE_USER");
            personRoleRepository.save(personRole1);
            person.setRoles(Collections.singleton(personRole1));
        }
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

}
