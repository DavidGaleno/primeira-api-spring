package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404() {
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<SumarioErro>> tratarErro400(MethodArgumentNotValidException e) {
        List<FieldError> erros = e.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(SumarioErro::new).toList());

    }

    public record SumarioErro(String campo, String message) {
        public SumarioErro(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
