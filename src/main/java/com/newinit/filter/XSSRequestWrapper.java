/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newinit.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author ceasar
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {


private byte[] rawData;
private HttpServletRequest request;
private ResettableServletInputStream servletStream;

public XSSRequestWrapper(HttpServletRequest request) {
    super(request);
    this.request = request;
    this.servletStream = new ResettableServletInputStream();
}


public void resetInputStream(byte[] newRawData) {
    servletStream.stream = new ByteArrayInputStream(newRawData);
}

@Override
public ServletInputStream getInputStream() throws IOException {
    if (rawData == null) {
        rawData = IOUtils.toByteArray(this.request.getReader());
        servletStream.stream = new ByteArrayInputStream(rawData);
    }
    return servletStream;
}

@Override
public BufferedReader getReader() throws IOException {
    if (rawData == null) {
        rawData = IOUtils.toByteArray(this.request.getReader());
        servletStream.stream = new ByteArrayInputStream(rawData);
    }
    return new BufferedReader(new InputStreamReader(servletStream));
}

private class ResettableServletInputStream extends ServletInputStream {

    private InputStream stream;

    @Override
    public int read() throws IOException {
        return stream.read();
     }
   }
 }
