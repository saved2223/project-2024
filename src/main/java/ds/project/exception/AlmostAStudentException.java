package ds.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlmostAStudentException extends CustomException{
    public AlmostAStudentException() {
        this.status = HttpStatus.CONFLICT;
        this.message = "Вы уже являетесь студентом";
    }
}
