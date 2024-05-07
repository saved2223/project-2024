package ds.project.service;

import ds.project.dto.StudentMarkDto;
import ds.project.dto.TaskForMarkDTO;
import ds.project.exception.WrongMarkException;
import ds.project.model.Task;
import ds.project.model.mark.Mark;
import ds.project.model.mark.MarkResult;
import ds.project.model.mark.MarkResultEnum;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import ds.project.model.training.Training;
import ds.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarksService {
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MarkResultRepository markResultRepository;
    @Autowired
    private MessageService messageService;

    public List<StudentMarkDto> getStudentsMarksByPerson(Person person, UUID training_id){
        List<StudentMarkDto> studentMarkDtos = new ArrayList<>();
        List<Mark> marks = markRepository.findByStudent_Trainings_IdAndStudent_Person(training_id, person);
        for (Mark mark : marks) {
            studentMarkDtos.add(new StudentMarkDto(mark.getTask().getName(), mark.getResult().getMarkResult().name()));
        }
        return studentMarkDtos;
    }

    public void markStudentsTask(TaskForMarkDTO taskForMarkDTO, Person teacher){
        try {
            Mark mark = new Mark();
            Task task = taskRepository.getReferenceById(taskForMarkDTO.getTaskId());
            mark.setTask(task);
            mark.setComment(taskForMarkDTO.getComment());
            mark.setTeacher(teacher);
            Student student = studentRepository.getReferenceById(taskForMarkDTO.getStudentID());
            mark.setStudent(student);
            MarkResultEnum markResult = MarkResultEnum.valueOf(taskForMarkDTO.getMark());
            Optional<MarkResult> markResultOptional = markResultRepository.findByMarkResult(markResult);
            if(markResultOptional.isPresent()){
                mark.setResult(markResultOptional.get());
            }
            else {
                MarkResult mr = new MarkResult();
                mr.setMarkResult(markResult);
                markResultRepository.save(mr);
                mark.setResult(mr);
            }
            mark.setMarkedAt(Timestamp.from(Instant.now()));
            markRepository.save(mark);
            messageService.sendMessageToPerson(
                    "Ваша задача " + task.getName() + " была оценена " + markResult.name(),
                    teacher,
                    student.getPerson());
        }
        catch (IllegalArgumentException e){
            throw new WrongMarkException();
        }
    }
}
