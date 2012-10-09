package com.peoplewearus.web.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peoplewearus.web.spring.data.ProductNotFoundException;
import com.peoplewearus.web.spring.domain.Orderline;
import com.peoplewearus.web.spring.domain.Product;
import com.peoplewearus.web.spring.domain.ShoppingCart;
import com.peoplewearus.web.spring.services.ECommerceService;

@Scope("session")
@Controller
public class ShoppingCartController
{
	@Autowired
	private ECommerceService ecommerceService;

	@Autowired
	private ShoppingCart cart = new ShoppingCart();

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public ModelAndView addToCart(@RequestParam("id") String id) throws ProductNotFoundException
	{
		Product product = ecommerceService.getProduct(id);
		cart.addItem(product);
		return new ModelAndView("productAddedToCart", "product", product);
	}

	@RequestMapping("/viewCart")
	public ModelAndView viewCart()
	{
		List<Orderline> allOrderlines = cart.getAllItems();
		return new ModelAndView("cartContents", "lines", allOrderlines);
	}

}
