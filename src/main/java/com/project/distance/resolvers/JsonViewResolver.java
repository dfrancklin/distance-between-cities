package com.project.distance.resolvers;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Creates a json view resolver
 * 
 * @author Diego Francklin Martins dos Santos
 */
public class JsonViewResolver implements ViewResolver {

	/**
	 * Returns a json view
	 * 
	 * @return View
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		
		return view;
	}

}
