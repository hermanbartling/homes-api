package se.difo.hemnetapi.api.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.difo.logs.loggingfilter.HttpMessageLoggingFilter;
import se.difo.logs.loggingfilter.RequestWrapper;
import se.difo.logs.loggingfilter.ResponseWrapper;

import java.util.Arrays;
import java.util.List;

public class ApiMessageLoggingFilter extends HttpMessageLoggingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMessageLoggingFilter.class);
    private static final List<String> debugLoggingMethods = Arrays.asList("GET", "PUT");

    @Override
    public void logRequest(final RequestWrapper request) {
        StringBuilder sb = new StringBuilder();

        sb.append("> ").append(request.getMethod()).append(" ").append(request.getRequestURI());
        if (request.getQueryString() != null && request.getQueryString().length() > 0) {
            sb.append(" ").append(request.getQueryString());
        }
        String requestBody = getRequestBody(request);
        if (requestBody != null && requestBody.length() > 0) {
            sb.append(" ").append(requestBody);
        }

        if (debugLoggingMethods.contains(request.getMethod().toUpperCase())) {
            LOGGER.debug(sb.toString());
        } else {
            LOGGER.info(sb.toString());
        }
    }

    @Override
    public void logResponse(final ResponseWrapper response, final RequestWrapper request, final long duration) {
        StringBuilder sb = new StringBuilder();

        sb.append("< HTTP ").append(response.getStatus()).append(" ").append(duration).append(" ms");

        if (debugLoggingMethods.contains(request.getMethod().toUpperCase())) {
            LOGGER.debug(sb.toString());
        } else {
            LOGGER.info(sb.toString());
        }
    }

}
