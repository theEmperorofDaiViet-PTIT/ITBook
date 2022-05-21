package jersey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;

import jersey.dao.AccountDAO;
import jersey.dao.OrderDAO;
import jersey.dao.ProductDAO;
import jersey.dao.RegisteredOrderDAO;
import jersey.entity.Account;
import jersey.entity.Product;
import jersey.form.ProductForm;
import jersey.model.AccountInfo;
import jersey.model.CartInfo;
import jersey.model.OrderDetailInfo;
import jersey.model.OrderInfo;
import jersey.model.ProductInfo;
import jersey.model.RegisteredOrderDetailInfo;
import jersey.model.RegisteredOrderInfo;
import jersey.pagination.PaginationResult;
import jersey.utils.Utils;
import jersey.validator.ProductFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional 
public class AccountController {
	
	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private RegisteredOrderDAO registeredOrderDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductFormValidator productFormValidator;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == ProductForm.class) {
			dataBinder.setValidator(productFormValidator); 
		}
		else if (target.getClass() == CartInfo.class) {

		}
	}

	// GET: Hiển thị trang login
	@RequestMapping(value = { "/account/login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping(value = { "/account/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.isEnabled());
		
		String userName = userDetails.getUsername();
		AccountInfo accountInfo = this.accountDAO.getAccountInfo(userName);

		model.addAttribute("accountInfo", accountInfo);
		return "accountInfo";
	}

	@RequestMapping(value = { "/account/admin/orderList" }, method = RequestMethod.GET)
	public String orderList(Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 10;

		PaginationResult<OrderInfo> paginationResult //
				= orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

		model.addAttribute("paginationResult", paginationResult);
		return "orderList";
	}

	// GET: Hiển thị product
	@RequestMapping(value = { "/account/admin/product" }, method = RequestMethod.GET)
	public String product(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		ProductForm productForm = null;

		if (id != null && id.length() > 0) {
			Product product = productDAO.findProduct(id);
			if (product != null) {
				productForm = new ProductForm(product);
			}
		}
		if (productForm == null) {
			productForm = new ProductForm();
			productForm.setNewProduct(true);
		}
		model.addAttribute("productForm", productForm);
		return "product";
	}

	// POST: Save product
	@RequestMapping(value = { "/account/admin/product" }, method = RequestMethod.POST)
	public String productSave(Model model,
			@ModelAttribute("productForm") 
			@Validated
			ProductForm productForm,			
			BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			for (Object object : result.getAllErrors()) {
			    if(object instanceof FieldError) {
			        FieldError fieldError = (FieldError) object;

			        System.out.println(fieldError.getCode());
			    }

			    if(object instanceof ObjectError) {
			        ObjectError objectError = (ObjectError) object;

			        System.out.println(objectError.getCode());
			    }
			}
			return "product";
		}
		try {
			productDAO.save(productForm);
		} catch (Exception e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			String message = rootCause.getMessage();
			model.addAttribute("errorMessage", message);
			System.out.println(e.toString());
			// Show product form.
			return "product";
		}

		return "redirect:/productList";
	}
	
	@RequestMapping(value = { "/account/admin/deleteProduct" }, method = RequestMethod.GET)
	public String productDeleteRequest(Model model, @RequestParam(value = "id") String id) {
				
		ProductInfo productInfo = productDAO.findProductInfo(id);
		model.addAttribute("productInfo", productInfo);
		return "deleteConfirmation";	
	}
	
	@RequestMapping(value = { "/account/admin/deleteProduct" }, method = RequestMethod.POST)
	public String productDelete(Model model, @RequestParam(value = "action") String action,
											 @RequestParam(value = "id") String id) {
		if(action.equals("Yes")) {
			try {
				productDAO.delete(id);
			} catch (Exception e) {
				Throwable rootCause = ExceptionUtils.getRootCause(e);
				String message = rootCause.getMessage();
				model.addAttribute("errorMessage", message);
				System.out.println(e.toString());
				return "redirect:/productList";
			}
		}
		return "redirect:/productList";
	}
	

	@RequestMapping(value = { "/account/admin/order" }, method = RequestMethod.GET)
	public String orderView(Model model, @RequestParam("orderId") String orderId) {
		OrderInfo orderInfo = null;
		if (orderId != null) {
			orderInfo = this.orderDAO.getOrderInfo(orderId);
		}
		if (orderInfo == null) {
			return "redirect:/admin/orderList";
		}
		List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
		orderInfo.setDetails(details);

		model.addAttribute("orderInfo", orderInfo);

		return "order";
	}
	
	@RequestMapping(value = { "/account/admin/regOrderList" }, method = RequestMethod.GET)
	public String registeredOrderList(Authentication authentication, Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 10;
			
		PaginationResult<RegisteredOrderInfo> paginationResult //
				= registeredOrderDAO.listRegisteredOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

		model.addAttribute("paginationResult", paginationResult);
		return "registeredOrderList";
	}
	
	@RequestMapping(value = { "/account/admin/regOrder" }, method = RequestMethod.GET)
	public String registeredOrderView(Model model, @RequestParam("orderId") String orderId) {
		RegisteredOrderInfo registeredOrderInfo = null;
		if (orderId != null) {
			registeredOrderInfo = this.registeredOrderDAO.getRegisteredOrderInfo(orderId);
		}
		if (registeredOrderInfo == null) {
			return "redirect:/account/admin/regOrderList";
		}
		List<RegisteredOrderDetailInfo> details = this.registeredOrderDAO.listRegisteredOrderDetailInfos(orderId);
		registeredOrderInfo.setDetails(details);

		model.addAttribute("registeredOrderInfo", registeredOrderInfo);

		return "registeredOrder";
	}
	
/////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = { "/account/customer/orderList" }, method = RequestMethod.GET)
	public String customerOrderList(Authentication authentication, Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 10;
		
		String userName = "";
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userName = userDetails.getUsername();
		
		PaginationResult<RegisteredOrderInfo> paginationResult //
				= registeredOrderDAO.listRegisteredOrderInfoByUser(userName, page, MAX_RESULT, MAX_NAVIGATION_PAGE);

		model.addAttribute("paginationResult", paginationResult);
		return "registeredOrderList";
	}

	@RequestMapping(value = { "/account/customer/order" }, method = RequestMethod.GET)
	public String customerOrderView(Model model, @RequestParam("orderId") String orderId) {
		RegisteredOrderInfo registeredOrderInfo = null;
		if (orderId != null) {
			registeredOrderInfo = this.registeredOrderDAO.getRegisteredOrderInfo(orderId);
		}
		if (registeredOrderInfo == null) {
			return "redirect:/account/customer/orderList";
		}
		List<RegisteredOrderDetailInfo> details = this.registeredOrderDAO.listRegisteredOrderDetailInfos(orderId);
		registeredOrderInfo.setDetails(details);

		model.addAttribute("registeredOrderInfo", registeredOrderInfo);

		return "registeredOrder";
	}
	
	// GET: Xem lại thông tin để xác nhận.
	@RequestMapping(value = { "/account/shoppingCartConfirmation" }, method = RequestMethod.GET)
	public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInSession(request);

		if (cartInfo == null || cartInfo.isEmpty()) {

			return "redirect:/shoppingCart";
		}
		model.addAttribute("myCart", cartInfo);
		
		String userName = "";
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userName = userDetails.getUsername();
		model.addAttribute("accountInfo",this.accountDAO.getAccountInfo(userName));

		return "accountShoppingCartConfirmation";
	}
	
	@RequestMapping(value = { "/account/shoppingCartConfirmation" }, method = RequestMethod.POST)
	public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInSession(request);

		if (cartInfo.isEmpty()) {

			return "redirect:/shoppingCart";
		}
		try {
			registeredOrderDAO.saveOrder(cartInfo);
		} catch (Exception e) {

			return "accountShoppingCartConfirmation";
		}

		// Xóa giỏ hàng khỏi session.
		Utils.removeCartInSession(request);

		// Lưu thông tin đơn hàng cuối đã xác nhận mua.
		Utils.storeLastOrderedCartInSession(request, cartInfo);

		return "redirect:/shoppingCartFinalize";
	}


}
