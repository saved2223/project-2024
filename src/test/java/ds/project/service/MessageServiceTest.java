package ds.project.service;

import ds.project.dto.MessageDto;
import ds.project.mapper.MessageMapper;
import ds.project.model.Message;
import ds.project.model.person.Person;
import ds.project.repository.MessageRepository;
import ds.project.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class MessageServiceTest {
    @Mock
    MessageRepository messageRepository;
    @Mock
    MessageMapper messageMapper;
    @Mock
    PersonRepository personRepository;
    @InjectMocks
    MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMessagesToPerson() {
        when(messageRepository.findByPersonTo_Username(anyString())).thenReturn(Arrays.<Message>asList(new Message()));
        when(messageMapper.toDto(any(Message.class))).thenReturn(new MessageDto("personFromUsername", "personToUsername", new Timestamp(0, 0, 0, 0, 0, 0, 0), "text"));

        List<MessageDto> result = messageService.getAllMessagesToPerson(new Person());
        Assertions.assertEquals(Arrays.<MessageDto>asList(new MessageDto("personFromUsername", "personToUsername", new Timestamp(0, 0, 0, 0, 0, 0, 0), "text")), result);
    }

    @Test
    void testSendMessageToGroup() {
        when(messageRepository.save(any(Message.class))).thenReturn(new Message());
        when(personRepository.save(any(Person.class))).thenReturn(new Person());

        messageService.sendMessageToGroup("text", Arrays.<Person>asList(new Person()));
    }

    @Test
    void testSendMessageToPerson() {
        when(messageRepository.save(any(Message.class))).thenReturn(new Message());
        when(personRepository.save(any(Person.class))).thenReturn(new Person());

        messageService.sendMessageToPerson("text", new Person(), new Person());
    }
}
