package ing.contest.validation;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = ValidationExceptionHandler.class)
public class ValidationExceptionControllerAdvice {

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    ResponseEntity<?> handleValidationExceptions(Exception e) {
        return ResponseEntity.badRequest().body(new ValidationExceptionResponse(e.getMessage()));
    }
}
