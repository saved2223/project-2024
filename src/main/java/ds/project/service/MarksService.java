package ds.project.service;

import ds.project.model.mark.Mark;
import ds.project.model.training.Training;
import ds.project.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarksService {
    @Autowired
    private MarkRepository markRepository;

    public void getMarkList(Training training){
        Mark mark = new Mark();


    }
}
