package org.inbio.neoportal.web.view;

import java.beans.PropertyDescriptor;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class CSVview extends AbstractView {

		
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BufferedWriter writer = new BufferedWriter(response.getWriter());

		response.setContentType("text/csv"); 
		response.setHeader("Content-Disposition","attachment; filename=\"export.csv\"");

		List list = (List) model.get("data");
		List<String> columns = (List) model.get("columns");
				
		Class classFirstDTO = list.get(0).getClass();
		
		StringBuilder stringB = new StringBuilder();
		
		for (String column : columns) {
			stringB.append(column);
			stringB.append(",");
		}
		
		stringB.deleteCharAt(stringB.length()-1);
		
		//write header line
		writer.write(stringB.toString());
		writer.newLine();
		
		Logger.getLogger(
				this.getClass().getName()).log(
						Level.INFO, "Exporting {0} records", list.size());
		
		//
		for (Object item : list) {
			stringB = new StringBuilder();
			for (String column : columns) {
				stringB.append("\"");
				PropertyDescriptor property = new PropertyDescriptor(column, classFirstDTO);
				stringB.append(property.getReadMethod().invoke(item));
				stringB.append("\"");
				stringB.append(",");
			}
			stringB.deleteCharAt(stringB.length()-1);
			
			//write header line
			writer.write(stringB.toString());
			writer.newLine();
		}
		
		Logger.getLogger(
				this.getClass().getName()).log(
						Level.INFO, "Last line: {0}", stringB.toString());
		
				
		writer.flush();
		writer.close();
	}

}
