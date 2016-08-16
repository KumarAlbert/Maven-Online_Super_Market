package com.i2i.webapp.controller;

import com.i2i.Constants;
import com.i2i.dao.SearchException;
import com.i2i.exception.ApplicationException;
import com.i2i.service.CartService;
import com.i2i.service.CategoryService;
import com.i2i.service.ProductService;
import com.i2i.service.PurchaseOrderService;
import com.i2i.service.SubcategoryService;
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
import com.i2i.model.Role;
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
	SubcategoryService subcategoryService;
	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	private Cart cart = null;
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
	public ModelAndView homePage(final HttpServletRequest request) {
	    try {
	    	String userName = request.getRemoteUser();
			System.out.println(userName);
			user = userService.getUserByUsername(userName);
	        products = productService.getProductDetails();
	        System.out.println(products);
	        if(null != user){
	        	Set<Role> roles = user.getRoles();
	        	for(Role role : roles){
	        		if("ROLE_ADMIN".equals(role.getName())){
	        			return new ModelAndView("adminHome");
	        		}
	        	}
	        }
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
	@RequestMapping(value = "/product",method = RequestMethod.POST)
	public ModelAndView getProduct(String productName) {
		System.out.println(productService);
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			Product product = productService.findProductByName(productName);
        	model.put("product", product);
			return new ModelAndView("product",model);
		} catch (ApplicationException e){
			e.printStackTrace();
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
			@RequestParam("product") String productName) {
        try {
        	cart = new Cart();
            java.sql.Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());  
            cart.setCreatedAt(time);
            cart.setCreatedBy(user.getId());
            System.out.println(productName);
            Product product = productService.findProductByName(productName);
            double total = quantity * price;
            cart.setTotalPrice(total);
            cart.setQuantity(quantity);
        	cart.setProduct(product);
            System.out.println(cart);
        	cartService.addCart(cart);
    		Map<String, Object> model = new HashMap<String, Object>();
        	model.put("productFilter", productFilter);
        	return new ModelAndView("productFilter",model);
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
			cart = cartService.findCartById(cartId);
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
	public ModelAndView placeOrder() {
		double total = 0;
		Map<String, Object> model = new HashMap<String, Object>();
		for(Cart cart : cartList){
			total = total + cart.getTotalPrice();
		}
		model.put("user",user);
		model.put("cart", cartList);
		model.put("total",total);
		return new ModelAndView("placeOrder",model);
	}
	
	/**
	 * <p> This method is used to confirm the order.</p> 
	 * @param total It holds total price of order.
	 * @param paymentType It holds payment type of order.
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/confirmOrder")
	public ModelAndView confirmOrder(@RequestParam("total") double total,@RequestParam("paymentType") String paymentType) {
		Boolean status = true;
		long createdBy = user.getId();
		Product product;
		String productName;
		int productQty;
		int productStock ;
		long modifiedBy = user.getId();
		try {
			purchaseOrderService.addPurchaseOrder(user, total, paymentType, status, createdBy,cartList);
			for(Cart cart : cartList) {
				product = cart.getProduct();
				productName = product.getName();
				productQty = cart.getQuantity();
				productStock = product.getStock();
				productStock = productStock - productQty;
				productService.modifyProductStockByName(productName, productStock ,modifiedBy);
			}
			return new ModelAndView("Success");
		} catch(ApplicationException e) {
			e.printStackTrace();
		}
		return null;
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
		} catch (ApplicationException e){
			e.printStackTrace();
		}
		return new ModelAndView("updateCategory",model);
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
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("deleteCategory",model);
	}
	
	/**
	 * <p>This method used to redirect request to insert subcategory</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectSubcategoryInsert")
	public ModelAndView redirectToSubcategoryInsert() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("subcategory", subcategoryService.getSubcategoryDetails());
			return new ModelAndView("insertSubcategory",model);
		} catch(ApplicationException e){
			e.printStackTrace();
		}
		return new ModelAndView("insertSubcategory",model);
	}
	
	/**
	 * <p>This method used to redirect request to insert product</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectProductInsert")
	public ModelAndView redirectToProductInsert() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("product",productService.getProductDetails());
		} catch(ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("insertProduct",model);
	}
	
	/**
	 * <p>This method used to redirect request to update subcategory</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectSubcategoryUpdate")
	public ModelAndView redirectToSubcategoryUpdate() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("subcategory", subcategoryService.getSubcategoryDetails());
		} catch(ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("updateSubcategory",model);
	}
	
	/**
	 * <p>This method used to redirect request to update product</p>
	 * @return ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectProductUpdate")
	public ModelAndView redirectToProductUpdate() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("product",productService.getProductDetails());
		} catch(ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("updateProduct",model);
	}
	
	/**
	 * <p>This method used to redirect request to delete subcategory</p>
	 * @return  ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectSubcategoryDelete")
	public ModelAndView redirectToSubcategoryDelete() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("subcategory", subcategoryService.getSubcategoryDetails());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("deleteSubcategory",model);
	}
	
	/**
	 * <p>This method used to redirect request to delete product</p>
	 * @return  ModelAndView It returns model for response.
	 */
	@RequestMapping("/redirectProductDelete")
	public ModelAndView redirectToProductDelete() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("product",productService.getProductDetails());
		} catch(ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("deleteProduct");
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
	 * This method gets the subcategory details to be inserted.
	 * @param name 
	 *    Name of the category.
	 * @param subcategoryName 
	 *    Name of the subcategory.
	 * @return insertProduct
	 *    Redirects to insert product page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/subcategoryInsert",method = RequestMethod.POST)
	public ModelAndView insertSubcategory(@RequestParam("categoryName") String categoryName, 
		                           @RequestParam("subcategoryName") String subcategoryName) {
		try {
			int createdBy = 0;
			subcategoryService.addSubcategory(categoryName, subcategoryName , createdBy);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("adminHome");
	} 
	
	/**
	 * This method lists the subcategory details.
	 * @return viewSubcategories
	 *    Redirects to viewSubcategories page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/viewSubcategories")
	public ModelAndView viewSubcategories() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("subcategory", subcategoryService.getSubcategoryDetails());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("viewSubcategories", model);
	}
	
	/**
	 * This method updates the subcategory details.
	 * @param oldName 
	 *    Existing name of the subcategory.
	 * @param newName 
	 *    Name of the subcategory to be updated.
	 * @return insertSubCategory
	 *    Redirects to insert Subcategory page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/subcategoryUpdate",method = RequestMethod.POST)
	public ModelAndView updateSubcategory(@RequestParam("existingSubcategoryName") String oldName, 
		                           @RequestParam("subCategoryName") String newName) {
		try {
			int modifiedBy = 0;
			subcategoryService.modifySubcategoryNameByName(oldName, newName,modifiedBy);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("adminHome");
	}
	
	/**
	 * This method deletes the subcategory by its name.
	 * @param name
	 *    Name of the subcategory to be deleted.
	 * @return updateSubcategory
	 *    Redirects to update subcategory page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/subcategoryDelete",method = RequestMethod.POST)
	public ModelAndView deleteSubcategory(@RequestParam("name") String name) {	
		try {
			subcategoryService.removeSubcategoryByName(name);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("adminHome");
	}
	

	/**
	 * This method gets the product details to be inserted.
	 * @param subcategoryName 
	 *    Name of the subcategory.
	 * @param productName 
	 *    Name of the product.
	 * @param description 
	 *    Description of the product.
	 * @param imageurl 
	 *    imageurl of the product.
	 * @param stock 
	 *    stock of the product.
	 * @param price 
	 *    price of the product.
	 * @return insertCategory
	 *    Redirects to insert category page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/productInsert",method = RequestMethod.POST)
	public ModelAndView insertProduct(@RequestParam("subcategoryName") String subcategoryName, @RequestParam("productName") String name,
		                          @RequestParam("description") String description, @RequestParam("imageUrl") String imageUrl,
		                          @RequestParam("stock") int stock , @RequestParam("price") double price) {
		try {
			int createdBy = 0;
			productService.addProduct(name, description, subcategoryName, imageUrl, stock, price, createdBy);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("adminHome");
	} 
	
	/**
	 * This method lists the product details.
	 * @return viewProducts
	 *    Redirects to viewProducts page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/viewProducts")
	public ModelAndView viewProducts() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("product",productService.getProductDetails());
			return new ModelAndView("viewProducts", model);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("viewProducts", model);
	}
	
	
	/**
	 * This method updates the product details.
	 * @param name 
	 *    Name of the product.
	 * @param newValue 
	 *    Product details to be updated.
	 * @param choice 
	 *    Property of the product which is chosen. 
	 * @return insertProduct
	 *    Redirects to insert product page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/productUpdate",method = RequestMethod.POST)
	public ModelAndView updateProduct(@RequestParam("productName") String name, @RequestParam("newValue") String newValue,
		                          @RequestParam("choice") String choice) {
		try {
			int modifiedBy = 0;
			switch(choice) {
				case "name":
					productService.modifyProductNameByName(name, newValue, modifiedBy);
					break;
				case "description":
					productService.modifyProductDescriptionByName(name, newValue, modifiedBy);
					break;
				case "imageUrl":
					productService.modifyProductImageUrlByName(name, newValue, modifiedBy);
					break;
				case "stock":
					int stock = Integer.parseInt(newValue);
					productService.modifyProductStockByName(name, stock, modifiedBy);
					break;
				case "price":
					double price = Double.parseDouble(newValue);
					productService.modifyProductPriceByName(name, price, modifiedBy);
					break; 
				}	
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
    return new ModelAndView("adminHome");
	}
	
	/**
	 * This method deletes the product by its name.
	 * @param name
	 *    Name of the product to be deleted.
	 * @return insertProduct
	 *    Redirects to update insertProduct page.
     * @throws ApplicationException
     *     If there is any interruption occurred in the database.
	 */
	@RequestMapping(value="/productDelete",method = RequestMethod.POST)
	public ModelAndView deleteProduct(@RequestParam("productName") String name) {
		try {
			productService.removeProductByName(name);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return new ModelAndView("adminHome");
	}
}
