/*
 *  NeoPortal - New implementation of the INBio Species and Occurrences portal.
 *  
 *  Copyright (C) 2010 INBio - Instituto Nacional de Biodiversidad, Costa Rica
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
package org.inbio.neoportal.web.controller;


import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.InputStream;


import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author arturo
 */
@Controller
@RequestMapping("/proxy")
public class ProxyController {
   
    
    @RequestMapping (
            value = "*",
            method = RequestMethod.GET
            )
    public @ResponseBody void getUrlContent (
            @RequestParam String url,
            HttpServletResponse response) throws IOException{
        try {
            
            //debug
            Logger.getLogger(
                    ProxyController.class.getName()).log(Level.INFO, "Inica carga url: {0}", url);
            
            //Inicia llamada al url
            URL urlObj = new URL(url);
            URLConnection conn = urlObj.openConnection();
            
//            conn.setDoInput(true);
//            conn.setDoOutput(false);
            
            HttpURLConnection httpUrl = (HttpURLConnection)conn;
            
//            httpUrl.setDoInput(true);
//            httpUrl.setDoOutput(false);
            
            if(httpUrl.getResponseCode() == 200){
            
                //obtiene la respuesta
                InputStream is = null;
                is = conn.getInputStream();

                //modifica el content type
                response.setContentType(conn.getContentType());

                IOUtils.copy(is, response.getOutputStream());
                
            }
            else
                response.setStatus(httpUrl.getResponseCode());
            
            //httpUrl.

        } catch (SocketException ex) {
            //problema para traer los datos
            Logger.getLogger(
                ProxyController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        catch (Exception ex){
            Logger.getLogger(
                ProxyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
