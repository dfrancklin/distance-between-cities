package com.project.distance.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.project.distance.models.CitiesDistance;
import com.project.distance.models.CitiesList;
import com.project.distance.models.City;
import com.project.distance.resolvers.JsonViewResolver;
import com.project.distance.resolvers.XmlViewResolver;

/**
 * Configurantion class of the application
 * 
 * @author Diego Francklin Martins dos Santos
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={
	"com.project.distante.controllers",
	"com.project.distante.dao",
	"com.project.distance.services",
})
public class AppConfig implements WebMvcConfigurer {

	/**
	 * Configure the content negotiation
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
			.favorPathExtension(false)
			.favorParameter(false)
			.ignoreAcceptHeader(true)
			.defaultContentType(MediaType.APPLICATION_JSON_UTF8)
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("json", MediaType.APPLICATION_JSON_UTF8)
		;
	}

	/**
	 * Sets the view resolvers that the application can use
	 * 
	 * @param manager
	 * 
	 * @return ViewResolver
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		List<ViewResolver> resolvers = Arrays.asList(jsonViewResolver(), xmlViewResolver());
		
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		
		return resolver;
	}

	/**
	 * Creantes the json view resolver
	 * 
	 * @return ViewResolver
	 */
	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	/**
	 * Creantes the xml view resolver
	 * 
	 * @return ViewResolver
	 */
	@Bean
	public ViewResolver xmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(City.class, CitiesDistance.class, CitiesList.class);
		
		return new XmlViewResolver(marshaller);
	}

}
