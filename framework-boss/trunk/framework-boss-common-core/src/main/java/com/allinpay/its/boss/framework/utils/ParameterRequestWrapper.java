package com.allinpay.its.boss.framework.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * @author YM
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, String[]> params;
	private ServletInputStream servletInputStream;
	private int contentLength;

	public ParameterRequestWrapper(HttpServletRequest request,
			Map<String, String[]> newParams) {
		super(request);
		this.params = newParams;
	}

	public ParameterRequestWrapper(HttpServletRequest request,
			ServletInputStream in, int contentLength) {
		super(request);
		this.params = null;
		this.servletInputStream = in;
		this.contentLength = contentLength;
	}

	public Map<String, String[]> getParameterMap() {
		if (this.params == null)
			return super.getParameterMap();
		return this.params;
	}

	public Enumeration<String> getParameterNames() {
		if (this.params == null)
			return super.getParameterNames();
		Vector l = new Vector(this.params.keySet());
		return l.elements();
	}

	public String[] getParameterValues(String name) {
		if (this.params == null)
			return super.getParameterValues(name);
		Object v = this.params.get(name);
		if (v == null)
			return null;
		if ((v instanceof String[]))
			return (String[]) (String[]) v;
		if ((v instanceof String)) {
			return new String[] { (String) v };
		}
		return new String[] { v.toString() };
	}

	public String getParameter(String name) {
		if (this.params == null)
			return super.getParameter(name);
		Object v = this.params.get(name);
		if (v == null)
			return null;
		if ((v instanceof String[])) {
			String[] strArr = (String[]) (String[]) v;
			if (strArr.length > 0) {
				return strArr[0];
			}
			return null;
		}
		if ((v instanceof String)) {
			return (String) v;
		}
		return v.toString();
	}

	public int getContentLength() {
		if (this.servletInputStream == null)
			return super.getContentLength();
		return this.contentLength;
	}

	public ServletInputStream getInputStream() throws IOException {
		if (this.servletInputStream == null)
			return super.getInputStream();
		return this.servletInputStream;
	}
}