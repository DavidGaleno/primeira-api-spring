package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.exceptions.ValidacaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity validacaoExceptionHandler(ValidacaoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity invalidJsonRequestHandler(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity teste(DataIntegrityViolationException e) {
        Pattern pattern = Pattern.compile("Key \\(([^)]+)\\)=\\(([^)]+)\\) ([^)]+)]");
        Matcher matcher = pattern.matcher(e.getMessage());
        List<String> errors = new ArrayList<>();
        while (matcher.find()) {
            String field = matcher.group(1);
            String value = matcher.group(2);
            String problem = matcher.group(3);
            errors.add(field + " " + value + " " + problem);
        }
        return ResponseEntity.badRequest().body(errors);
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
