package br.com.navita.patrimonio.exception;

import br.com.navita.patrimonio.dominio.dto.DetalheErroDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger LOGGER = Logger.getLogger("ResponseExceptionHandler");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetalheErroDTO> handleUserNotFoundException(Exception ex, WebRequest request){
        DetalheErroDTO detalheErro = new DetalheErroDTO(LocalDateTime.now(), request.getDescription(false));
        LOGGER.severe(ex.getMessage());
        return new ResponseEntity(detalheErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NenhumResultadoEncontrado.class)
    public ResponseEntity<DetalheErroDTO> handleUserNotFoundException(NenhumResultadoEncontrado ex, WebRequest request){
        DetalheErroDTO detalheErro = new DetalheErroDTO(LocalDateTime.now(), ex.getMessage());
        LOGGER.severe(ex.getMessage());
        return new ResponseEntity(detalheErro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjetoDublicadoException.class)
    public ResponseEntity<DetalheErroDTO> handleUserNotFoundException(ObjetoDublicadoException  ex, WebRequest request){
        DetalheErroDTO detalheErro = new DetalheErroDTO(LocalDateTime.now(), ex.getMessage());
        LOGGER.severe(ex.getMessage());
        return new ResponseEntity(detalheErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegistroNaoPodeSerApagadoException.class)
    public ResponseEntity<DetalheErroDTO> handleUserNotFoundException(RegistroNaoPodeSerApagadoException  ex, WebRequest request){
        DetalheErroDTO detalheErro = new DetalheErroDTO(LocalDateTime.now(), ex.getMessage());
        LOGGER.severe(ex.getMessage());
        return new ResponseEntity(detalheErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CamposInvalidosException.class)
    public ResponseEntity<DetalheErroDTO> handleUserNotFoundException(CamposInvalidosException  ex, WebRequest request){
        DetalheErroDTO detalheErro = new DetalheErroDTO(LocalDateTime.now(), ex.getErros());
        LOGGER.severe(ex.getMessage());
        return new ResponseEntity(detalheErro, HttpStatus.BAD_REQUEST);
    }





}
