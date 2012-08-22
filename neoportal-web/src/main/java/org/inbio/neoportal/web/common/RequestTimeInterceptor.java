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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * @author avargas
 *
 */
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(RequestTimeInterceptor.class);
	private StopWatch watch = new StopWatch();
	 
	//before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response, Object handler)
	    throws Exception {
 
		if(!(handler instanceof ResourceHttpRequestHandler))
		//logger.info("Start Request");
			watch.start(new StringBuilder(
					request.getRequestURI()).append(
							"?").append(
									request.getQueryString()).append(
									" [Proccess request]").toString());

		return true;
	}
 
	//after the handler is executed
	public void postHandle(
		HttpServletRequest request, HttpServletResponse response, 
		Object handler, ModelAndView modelAndView)
		throws Exception {
 
		if(!(handler instanceof ResourceHttpRequestHandler)){
			watch.stop();
			watch.start(request.getRequestURI() + " [Rendering view]");
		}
		
	}
	
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		if(!(handler instanceof ResourceHttpRequestHandler)){
			watch.stop();
			logger.info(watch.prettyPrint());
			//restart watch after every print
			this.watch = new StopWatch();
		}
	}
	
}
