package com.store.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.store.exception.BusinessException;
import com.store.exception.EntityInUseException;
import com.store.exception.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	public static final String MSG_SYSTEM_ERRO_FINAL_USER = "An unexpected system error has occurred. Try again and if the problem persists, contact your system administrator.";
	
	
	@Autowired
	private MessageSource messageSource;
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		BindingResult bindingResult = ex.getBindingResult();
		
		
		List<Problem.Field> problemField = bindingResult.getFieldErrors().stream()
				.map(fieldError ->{
					
					String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());//tentar fazer ler mais de um locale
					
					return Problem.Field.builder()
						.name(fieldError.getField())
						.userMessage(message)
						.build();})
				.collect(Collectors.toList());
		
		ProblemType problemType = ProblemType.INVALID_DATA;
		String detail = "One or more fields are invalid. Fill in correctly and try again.";
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.fields(problemField)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = String.format("The resource %s, that you tried access, is nonexistent ", ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException)ex, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			
		ProblemType problemType = ProblemType.INVALID_PARAMETER;
		String detail = String.format("The parameter of URL %s, received the value %s, witch is an invalid value."
				+ " Correct and enter a value compatible with %s.", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_SYSTEM_ERRO_FINAL_USER)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	
}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		System.out.println("Testando" + ex.getCause());
		
		if (rootCause instanceof InvalidFormatException ) {
			return handleInvalidFormat((InvalidFormatException)rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException ) {
			return handlePropertyBinding((PropertyBindingException)rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.UNREADABLE_REQUEST_BODY;
		String detail = "The request body is invalid. Check syntax error";

		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_SYSTEM_ERRO_FINAL_USER)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
			ProblemType problemType = ProblemType.UNREADABLE_REQUEST_BODY;
			String detail = String.format("The property %s, not part of the class %s."
					+ " Correct or remove this property and try again.", path, ex.getReferringClass().getSimpleName());
			
			Problem problem = createProblemBuilder(status, problemType, detail)
					.userMessage(MSG_SYSTEM_ERRO_FINAL_USER)
					.build();
			
			return handleExceptionInternal(ex, problem, headers, status, request);

	}

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.UNREADABLE_REQUEST_BODY;
		String detail = String.format("The property '%s' received the value '%s' which is of an invalid type."
				+ " Correct and enter a value compatible with type %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_SYSTEM_ERRO_FINAL_USER)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request){
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.SYSTEM_ERROR;
	
		String detail = MSG_SYSTEM_ERRO_FINAL_USER;
		
		ex.printStackTrace();
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = ex.getMessage();

		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusiness(BusinessException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.BUSINESS_ERROR;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
	
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	
	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> handleEntityInUse(EntityInUseException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTITY_IN_USE;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
	
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problem.builder()
					.status(status.value())
					.title(status.getReasonPhrase())
					.userMessage(MSG_SYSTEM_ERRO_FINAL_USER)
					.timestamp(LocalDateTime.now())
					.build();
		}else if(body instanceof String) {
			body = Problem.builder()
					.status(status.value())
					.title((String) body)
					.userMessage(MSG_SYSTEM_ERRO_FINAL_USER)
					.timestamp(LocalDateTime.now())
					.build();
		}
		
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	private Problem.ProblemBuilder createProblemBuilder (HttpStatus status, 
			ProblemType problemType, String detail) {
		
		return Problem.builder()
				 .status(status.value())
				 .type(problemType.getUri())
				 .title(problemType.getTitle())
				 .detail(detail)
				 .timestamp(LocalDateTime.now());
	}

	private String joinPath(List<Reference> references) {
		return references.stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
	}

	
	
	
	
	
}
