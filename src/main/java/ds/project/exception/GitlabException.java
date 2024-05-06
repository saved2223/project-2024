package ds.project.exception;

import org.springframework.http.HttpStatus;

public class GitlabException extends CustomException{
    public GitlabException(String message) {
        this.status = HttpStatus.CONFLICT;
        this.message = message;
    }
}
