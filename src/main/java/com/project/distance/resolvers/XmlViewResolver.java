package com.project.distance.resolvers;

import java.util.Locale;

import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

/**
 * Creates a xml view resolver
 * 
 * @author Diego Francklin Martins dos Santos
 */
public class XmlViewResolver implements ViewResolver {

	private Marshaller marshaller;

	public XmlViewResolver(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	/**
	 * Returns a xml view
	 * 
	 * @return View
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MarshallingView view = new MarshallingView();
		
		view.setMarshaller(marshaller);
		
		return view;
	}

}
