package ds.project.exception;

import org.springframework.http.HttpStatus;

public class AlreadyInTrainingException extends CustomException{
    public AlreadyInTrainingException() {
        status = HttpStatus.BAD_REQUEST;
        message = "Вы уже находитесь в этой стажировке";
    }
}
