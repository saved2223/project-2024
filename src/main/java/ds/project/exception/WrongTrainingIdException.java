package ds.project.exception;

import org.springframework.http.HttpStatus;

public class WrongTrainingIdException extends CustomException{
    public WrongTrainingIdException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Такой стажировки не существует";
    }
}
