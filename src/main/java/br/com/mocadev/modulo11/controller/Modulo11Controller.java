package br.com.mocadev.modulo11.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mocadev.modulo11.model.Modulo11;

@Controller
@RequestMapping("/")
public class Modulo11Controller {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Modulo11 sayHello(
			@RequestParam(value = "numero", required = false, defaultValue = "Stranger") String numero) {
		return new Modulo11(counter.incrementAndGet(), String.format(template, numero));
	}

	
	@RequestMapping(value = "teste/{dado}/{numDig}/{limMult}/{x10}", method = RequestMethod.GET)
	public @ResponseBody String calculaDigitoMod1(@PathVariable String dado,@PathVariable int numDig,@PathVariable int limMult,@PathVariable boolean x10) {

		int mult, soma, i, n, dig;

		if (!x10)
			numDig = 1;
		for (n = 1; n <= numDig; n++) {
			soma = 0;
			mult = 2;
			for (i = dado.length() - 1; i >= 0; i--) {
				soma += (mult * Integer.parseInt(dado.substring(i, i + 1)));
				if (++mult > limMult)
					mult = 2;
			}
			if (x10) {
				dig = ((soma * 10) % 11) % 10;
			} else {
				dig = soma % 11;
			}
			if (dig == 10) {
				dado += "X";
			} else {
				dado += String.valueOf(dig);
			}
		}
		return dado.substring(dado.length() - numDig, dado.length());
	}

}
