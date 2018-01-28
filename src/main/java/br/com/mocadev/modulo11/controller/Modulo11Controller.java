package br.com.mocadev.modulo11.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.mocadev.modulo11.model.Modulo11;

@Controller
@RequestMapping("/")
public class Modulo11Controller {

	// @Autowired
	// private Modulo11Service modulo11Service;

	List<Modulo11> listaMatriculas = new ArrayList<>();
	int count = 0;

	@GetMapping
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		Modulo11 modulo11 = new Modulo11();
		view.addObject(modulo11);
		view.addObject("modulo11s", listaMatriculas);
		// view.addObject("modulo11s", modulo11Service.findAll());
		return view;
	}

	@PostMapping
	public ModelAndView save(@Validated Modulo11 modulo11, Errors erros) {
		ModelAndView view = new ModelAndView("redirect:/");
		if (erros.hasErrors()) {

			/*
			 * adiciono a lista, pois qndo chama o layout de erro ela tende a
			 * summir
			 */
			view.addObject("modulo11s", listaMatriculas);
			
			
			/*
			 * pulo do gato para que a mensagem de erro apareça, pois ela deve
			 * só chamar o layout
			 */
			/*
			 * funciona como se tivesse retornando um string, ou seja, o caminho
			 * do layout
			 */
			view.setViewName("index");
			return view;
		}

		modulo11.setId((long) (count += 1));
		modulo11.setDigitoVerificador(calculaDigitoVerificador(modulo11));
		listaMatriculas.add(modulo11);
		// modulo11Service.save(modulo11);

		return view;
	}

	@GetMapping("/deleteAll")
	public ModelAndView delete() {

		ModelAndView view = new ModelAndView("redirect:/");
		// modulo11Service.deleteAll();
		listaMatriculas.clear();
		count = 0;

		return view;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		ModelAndView view = new ModelAndView("redirect:/");
		// modulo11Service.delete(id);
		// deleta o elemento da lista que tem o mesmo ID
		listaMatriculas.removeIf(modulo11 -> modulo11.getId() == id);

		return view;
	}

	public String calculaDigitoVerificador(Modulo11 modulo11) {
		int numDig = 1;
		int limMult = 9;
		boolean x10 = true;
		String dado = modulo11.getNumero();

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
