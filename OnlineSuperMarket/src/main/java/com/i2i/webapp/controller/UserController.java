package com.i2i.webapp.controller;

import com.i2i.Constants;
import com.i2i.dao.SearchException;
import com.i2i.exception.ApplicationException;
import com.i2i.service.CartService;
import com.i2i.service.CategoryService;
import com.i2i.service.ProductService;
import com.i2i.service.PurchaseOrderService;
import com.i2i.service.UserManager;
import com.i2i.service.UserService;
import com.i2i.service.impl.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.i2i.model.Cart;
import com.i2i.model.Product;
import com.i2i.model.Subcategory;
import com.i2i.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Simple class to retrieve a list of users from the database.
 * <p/>
 * <p>
 * <a href="UserController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
public class UserController {
    private UserManager userManager = null;
    
    @Autowired
    UserService userService;
	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	private User user = null;
	private List<Product> products = null;
	private List<Product> productFilter = null;
	private List<Cart> cartList = null;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @RequestMapping(value = "/admin/users*", method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(Constants.USER_LIST, userManager.search(query));
            System.out.println(userManager.search(query));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(userManager.getUsers());
        }
        return new ModelAndView("admin/userList", model.asMap());
    }
    
	/**
	 * <P> This method used redirect the request to home page </p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/homePage")
	public ModelAndView homePage() {
        System.out.println("home page");
	    try {
	        products = productService.getProductDetails();
	        System.out.println(products);
	    } catch(ApplicationException e) {
	    	e.printStackTrace();
	    }
		return new ModelAndView("homePage");
	}
	
	@RequestMapping("/AdminHome")
	public ModelAndView redirectAdminPage() {
        System.out.println("admin page");
	        //products = productService.getProductDetails();
		return new ModelAndView("adminHome");
	}
	
	/**
	 * <p> This method used to redirect request to category insert.</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectCategoryInsert")
	public ModelAndView redirectToCategoryInsert() {
		Map<String, Object> model = new HashMap<String, Object>();
		try { 
			model.put("category", categoryService.getCategoryDetails());
			
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("insertCategory",model);
	}
	
	/**
	 * <p>This method used to redirect request to update category</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectCategoryUpdate")
	public ModelAndView redirectToCategoryUpdate() {
		Map<String, Object> model = new HashMap<String, Object>();
		try { 
			model.put("category", categoryService.getCategoryDetails());
			return new ModelAndView("updateCategory",model);
		} catch (ApplicationException e){
			
		}
		return null;
	}
	
	/**
	 * <p>This method used to redirect request to delete category</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectCategoryDelete")
	public ModelAndView redirectToCategoryDelete() {
		Map<String, Object> model = new HashMap<String, Object>();
		try { 
			model.put("category", categoryService.getCategoryDetails());
			return new ModelAndView("deleteCategory",model);
		} catch (ApplicationException e) {
			
		}
		return null;
	}
	
	/**
	 * This method gets the category details to be inserted.
	 * @param name 
	 *    Name of the category.
	 * @return insertSubCategory
	 *    Redirects to insert Subcategory page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/categoryInsert",method = RequestMethod.POST)
	public ModelAndView insertCategory(@RequestParam("categoryName") String name) {
		try {
			System.out.println("Category insert");
			int createdBy = 0;
			categoryService.addCategory(name, createdBy);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		 return new ModelAndView("adminHome");
	}
	
	/**
	 * This method lists the category details.
	 * @return viewCategories
	 *    Redirects to viewCategories page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/viewCategories")
	public ModelAndView viewCategories() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("category", categoryService.getCategoryDetails());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("viewCategories", model);
	}
	
	/**
	 * This method updates the category details.
	 * @param oldName 
	 *    Existing name of the category.
	 * @param newName 
	 *    Name of the category to be updated.
	 * @return insertCategory
	 *    Redirects to insert category page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/categoryUpdate",method = RequestMethod.POST)
	public ModelAndView updateCategory(@RequestParam("existingCategoryName") String oldName, 
		                           @RequestParam("categoryName") String newName) {
		try {
			 int modifiedBy = 0;
			 categoryService.modifyCategoryNameByName(oldName, newName, modifiedBy);
			 return new ModelAndView("insertCategory");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		 return new ModelAndView("adminHome");
	} 
	
	/**
	 * This method deletes the category by its name.
	 * @param name
	 *    Name of the category to be deleted.
	 * @return updateCategory
	 *    Redirects to update category page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/categoryDelete",method = RequestMethod.POST)
	public ModelAndView deleteCategory(@RequestParam("categoryName") String name) {
		try {
			categoryService.removeCategoryByName(name);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("adminHome");
	}
	
	/**
	 * <p> This method used redirect the request to product filter page </p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/fruits")
	public ModelAndView redirectToFruits() {
		Map<String, Object> model = new HashMap<String, Object>();
		productFilter = new ArrayList<Product>();
		for(Product product : products) {
			Subcategory subcategory = product.getSubcategory();
			if("Fruits".equals(subcategory.getName())) {
				productFilter.add(product);
			}
		}
		model.put("productFilter", productFilter);
		return new ModelAndView("productFilter",model);
	}
	
	/**
	 * <p> This method used to get product details.</p>
	 * @param productName It holds product's name.
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/product")
	public ModelAndView getProduct(@RequestParam("productName") String productName) {
		System.out.println(productService);
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			Product product = productService.findProductByName(productName);
        	model.put("product", product);
			return new ModelAndView("product",model);
		} catch (ApplicationException e){
     	  }
		return null;
	}
	
	/**
	 * <p> This method used to save cart details.</p>
	 * @param quantity It holds product quantity.
	 * @param price It holds product price.
	 * @param object It holds product object.
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping(value= "/saveCart", method = RequestMethod.POST)
	public ModelAndView saveCart(@RequestParam("quantity") int quantity,@RequestParam("productPrice") double price ,
			@RequestParam("product") Object object) {
        try {
        	 Cart cart = new Cart();
        	String name = object.toString();
            java.sql.Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
            cart.setCreatedAt(time);
            cart.setCreatedBy(000);
            Product product = productService.findProductByName(name);
            double total = quantity * price;
            cart.setTotalPrice(total);
            cart.setQuantity(quantity);
            Set<Cart> carts = new HashSet<Cart>();
        	cart.setProduct(product);
            carts.add(cart);
            product.setCarts(carts);
            System.out.println(cart);
        	cartService.addCart(cart);
    		Map<String, Object> model = new HashMap<String, Object>();
        	if("Fruits".equals(cart.getProduct().getSubcategory().getName())){
        		model.put("productFilter", productFilter);
        		return new ModelAndView("productFilter",model);
        	}
        } catch (ApplicationException e) {
        	e.printStackTrace();
        }
        return null;
	}
	
	/**
	 * <p> This method is used to get cart details.</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/cart")
	public ModelAndView getCart() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {

			cartList = cartService.getCartDetails();
			model.put("cart", cartList);
		return new ModelAndView("cart",model);
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * <p> This method used to remove cart.</p>
	 * @param cartId It holds cart id. 
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/removeCart")
	public ModelAndView removeCart(int cartId) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			System.out.println(cartId);
			Cart cart = cartService.findCartById(cartId);
			cartService.removeCart(cart);
			cartList = cartService.getCartDetails();
			model.put("cart", cartList);
			return new ModelAndView("cart",model);
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * <p> This method used to place the order</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/order")
	public ModelAndView placeOrder(final HttpServletRequest request) {
		double total = 0;
		String userName = request.getRemoteUser();
		System.out.println(userName);
		user = userService.getUserByUsername(userName);
		Map<String, Object> model = new HashMap<String, Object>();
		for(Cart cart : cartList){
			total = total + cart.getTotalPrice();
		}
		model.put("user",user);
		model.put("cart", cartList);
		model.put("total",total);
		return new ModelAndView("placeOrder",model);
	}
	

	
}
