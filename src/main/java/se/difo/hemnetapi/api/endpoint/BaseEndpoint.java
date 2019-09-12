package se.difo.hemnetapi.api.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.difo.hemnetapi.core.exception.ApiNotFoundException;
import se.difo.hemnetapi.core.exception.ApiValidationException;
import se.difo.hemnetapi.core.repo.specification.SearchCriteria;
import se.difo.hemnetapi.core.service.BrService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public abstract class BaseEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrService.class);

    @ExceptionHandler(Exception.class)
    void handleException(Exception e, HttpServletResponse response) throws IOException {
        LOGGER.error("Internal error", e);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(ApiValidationException.class)
    void handleApiValidationException(ApiValidationException e, HttpServletResponse response) throws IOException {
        LOGGER.warn("Validation exception: {}", e.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(ApiNotFoundException.class)
    void handleApiNotFoundException(ApiNotFoundException e, HttpServletResponse response) throws IOException {
        LOGGER.warn("Resource not found: {}", e.getMessage());
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    protected List<SearchCriteria> getSearchCriterium(String searchQuery) {
        Pattern pattern = Pattern.compile(";*(.+?)(:|<|>)(.+?);");
        Matcher matcher = pattern.matcher(searchQuery + ";");

        List<SearchCriteria> criterium = new ArrayList<>();
        while (matcher.find()) {
            criterium.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }
        return criterium;
    }

}
