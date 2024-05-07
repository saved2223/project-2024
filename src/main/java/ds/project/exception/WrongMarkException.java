package ds.project.exception;

import org.springframework.http.HttpStatus;

public class WrongMarkException extends CustomException{
    public WrongMarkException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Неправильно введена оценка, выберите из PASS или FAIL";
    }
}
