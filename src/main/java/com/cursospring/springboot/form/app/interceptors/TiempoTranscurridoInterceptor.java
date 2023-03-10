package com.cursospring.springboot.form.app.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod metodo = (HandlerMethod) handler;
			logger.info("-------------------------------------------------------------");
			logger.info("es un método del controlador: " + metodo.getMethod().getName());
			logger.info("-------------------------------------------------------------");
		}
		logger.info("TiempoTranscurridoInterceptor: preHandle entrando...");
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long tiempoFin = System.currentTimeMillis();
		long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		long tiempoTranscurrido = tiempoFin - tiempoInicio;
		if(modelAndView != null) {
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}
		logger.info("Tiempo transcurrido: " + tiempoTranscurrido + " milisegundos");
		logger.info("------------------------------------------");
		logger.info("Interceptando: " + handler);
		logger.info("------------------------------------------");
		logger.info("TiempoTranscurridoInterceptor: postHandle saliendo...");
	}

}
