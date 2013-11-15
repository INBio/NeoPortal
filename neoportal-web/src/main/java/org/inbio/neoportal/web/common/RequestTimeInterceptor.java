/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2012 INBio - Instituto Nacional de Biodiversidad, Costa Rica
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.neoportal.web.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * @author avargas
 *
 */
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(RequestTimeInterceptor.class);
	 
	//before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object handler)
	    throws Exception {
	
		if(!(handler instanceof ResourceHttpRequestHandler)){
		  long startTime = System.currentTimeMillis();
		  request.setAttribute("startTime", startTime);
		}

		return true;
	}
 
	//after the handler is executed
	public void postHandle(
		HttpServletRequest request, HttpServletResponse response, 
		Object handler, ModelAndView modelAndView)
		throws Exception {
 
		if(!(handler instanceof ResourceHttpRequestHandler)){
			long renderTime = System.currentTimeMillis();
			request.setAttribute("renderTime", renderTime);
		}
		
	}
	
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		if(!(handler instanceof ResourceHttpRequestHandler)){
		  long renderTime, processTime, viewTime;
		  
		  long stopTime = System.currentTimeMillis();
          long startTime = Long.parseLong(request.getAttribute("startTime").toString());
          long totalTime = stopTime - startTime;
    
          DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
          // get current date time with Date()
          Date date = new Date();
          
          StringBuilder sb = new StringBuilder();
          sb.append(request.getRemoteHost());
          sb.append(" [");
          sb.append(dateFormat.format(date));
          sb.append("] ");
          sb.append(request.getRequestURI());
          if (request.getQueryString() != null) sb.append("?" + request.getQueryString());
          sb.append(" ");

          Object renderTimeAttribute = request.getAttribute("renderTime");
          
          if (renderTimeAttribute != null) {
            renderTime = Long.parseLong(request.getAttribute("renderTime").toString());
    
            processTime = renderTime - startTime;
            viewTime = stopTime - renderTime;
            
            sb.append("[process: ");
            sb.append(processTime);
            sb.append("] ");
            sb.append("[render: ");
            sb.append(viewTime);
            sb.append("] ");
    
          }
          
          sb.append("[total: ");
          sb.append(totalTime);
          sb.append("] ");
  
          logger.info(sb);
		}
	}
	
}
