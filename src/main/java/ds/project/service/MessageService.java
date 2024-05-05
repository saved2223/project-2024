package ds.project.service;

import ds.project.dto.MessageDto;
import ds.project.mapper.MessageMapper;
import ds.project.model.person.Person;
import ds.project.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    public List<MessageDto> getAllMessagesToPerson(Person person) {
        List<MessageDto> list = new ArrayList<>();
        messageRepository.findByPersonTo_Username(person.getUsername()).forEach(message ->
                list.add(messageMapper.toDto(message)));
        return list;
    }

}
