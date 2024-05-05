package ds.project.exception;

import org.springframework.http.HttpStatus;

import java.sql.Date;

public class ToLateTrainingEnrollingException extends CustomException{
    public ToLateTrainingEnrollingException(Date deadline) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Вы не успели записаться на данную стажировку, срок был до " + deadline.toString();
    }
}
