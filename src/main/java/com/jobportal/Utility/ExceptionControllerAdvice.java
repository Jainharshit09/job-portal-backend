    package com.jobportal.Utility;

    import com.jobportal.Expection.JobPortalExpection;
    import jakarta.validation.ConstraintViolation;
    import jakarta.validation.ConstraintViolationException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.env.Environment;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.ObjectError;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    import java.time.LocalDateTime;
    import java.util.stream.Collectors;

    @RestControllerAdvice
    public class ExceptionControllerAdvice {
        @Autowired
        private Environment environment;
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorInfo> genralException(Exception e){
            ErrorInfo errorInfo=new ErrorInfo(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return  new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        @ExceptionHandler(JobPortalExpection.class)
        public ResponseEntity<ErrorInfo> genralException(JobPortalExpection e){
            String message=environment.getProperty(e.getMessage(), e.getMessage());
            ErrorInfo errorInfo=new ErrorInfo(message, HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
            return  new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
        public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception e){
            String message="";
            if(e instanceof MethodArgumentNotValidException manvExpection){
                message=manvExpection.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
            }
            else{
                ConstraintViolationException cvExpection=(ConstraintViolationException) e;
                message=cvExpection.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            }
            ErrorInfo errorInfo=new ErrorInfo(message, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
            return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);
        }
    }
