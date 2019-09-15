package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

public class DocumentCreatorServlet implements HttpRequestHandler{

    private static final Logger LOG = LoggerFactory.getLogger(TestServlet.class);

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = httpServletRequest.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String text = buffer.toString().replaceAll("\"", "");

//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode parent = mapper.readTree(json);

//        String zakaz = parent.path("zakaz").asText();
//        String podr = parent.path("podr").asText();
        String zakaz = text.split(",")[0];
        String podr = text.split(",")[1];
//        String zakaz = "zakaz";
//        String podr = "podr";
        LOG.info("document: " + zakaz + " : " + podr);

        Document doc = new Document(zakaz, podr);
        httpServletResponse.getOutputStream().write(doc.getDocument());
    }
}
