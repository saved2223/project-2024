package ds.project.service;

import ds.project.dto.MessageDto;
import ds.project.mapper.MessageMapper;
import ds.project.model.Message;
import ds.project.model.person.Person;
import ds.project.repository.MessageRepository;
import ds.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private PersonRepository personRepository;

    public List<MessageDto> getAllMessagesToPerson(Person person) {
        List<MessageDto> list = new ArrayList<>();
        messageRepository.findByPersonTo_Username(person.getUsername()).forEach(message ->
                list.add(messageMapper.toDto(message)));
        return list;
    }

    public void sendMessageToGroup(String text, List<Person> group){
        for (Person person: group){
            Message message = new Message();
            message.setText(text);
            message.setPersonTo(person);
            message.setTimestamp(Timestamp.from(Instant.now()));
            messageRepository.save(message);
        }
    }

    public void sendMessageToPerson(String text, Person personFrom, Person personTo){
        Message message = new Message();
        message.setText(text);
        message.setPersonFrom(personFrom);
        message.setPersonTo(personTo);
        message.setTimestamp(Timestamp.from(Instant.now()));
        messageRepository.save(message);
    }

}
