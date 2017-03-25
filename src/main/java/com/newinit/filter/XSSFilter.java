package com.newinit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author ceasar
 */
public class XSSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        XSSRequestWrapper wrappedRequest = new XSSRequestWrapper(
                (HttpServletRequest) request);

        String body = IOUtils.toString(wrappedRequest.getReader());

        if (!"".equals(body)) {
            /*JSONObject oldJsonObject = new JSONObject(body);
        JSONObject newJsonObject = new JSONObject();

        for(String key : oldJsonObject.keySet())
        {
            newJsonObject.put(key, XSSUtils.stripXSS(oldJsonObject.get(key).toString()));
        }
        wrappedRequest.resetInputStream(newJsonObject.toString().getBytes());*/
            wrappedRequest.resetInputStream(cleanXSS(body).getBytes());

        }

        chain.doFilter(wrappedRequest, response);
    }

    private String cleanXSS(String value) {
        // You'll need to remove the spaces from the html entities below
        //logger.info("In cleanXSS RequestWrapper ..............." + value);
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        value = value.replaceAll("<script>", "");
        value = value.replaceAll("</script>", "");
        //logger.info("Out cleanXSS RequestWrapper ........ value ......." + value);
        return value;
    }
}
