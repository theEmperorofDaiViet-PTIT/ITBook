package jersey.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import jersey.entity.Account;
import jersey.entity.RegisteredOrder;
import jersey.entity.RegisteredOrderDetail;
import jersey.entity.Product;
import jersey.model.CartInfo;
import jersey.model.CartLineInfo;
import jersey.model.RegisteredOrderDetailInfo;
import jersey.model.RegisteredOrderInfo;
import jersey.pagination.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RegisteredOrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private ProductDAO productDAO;
	
	private int getMaxOrderNum() {
		String sql = "Select max(ro.orderNum) from " + RegisteredOrder.class.getName() + " ro ";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Integer> query = session.createQuery(sql, Integer.class);
		Integer value = (Integer) query.getSingleResult();
		if (value == null) {
			return 0;
		}
		return value;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void saveOrder(CartInfo cartInfo) {
		Session session = this.sessionFactory.getCurrentSession();

		int orderNum = this.getMaxOrderNum() + 1;
		RegisteredOrder registeredOrder = new RegisteredOrder();

		registeredOrder.setId(UUID.randomUUID().toString());
		
		String userName = "";
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userName = userDetails.getUsername();
		
		Account account = this.accountDAO.findAccount(userName);
		registeredOrder.setId(UUID.randomUUID().toString());
		registeredOrder.setAccount(account);
		registeredOrder.setOrderNum(orderNum);
		registeredOrder.setCreatedAt(new Date());
		registeredOrder.setTotal(cartInfo.getTotal());

		session.persist(registeredOrder);

		List<CartLineInfo> lines = cartInfo.getCartLines();

		for (CartLineInfo line : lines) {
			RegisteredOrderDetail detail = new RegisteredOrderDetail();
			detail.setId(UUID.randomUUID().toString());
			detail.setRegisteredOrder(registeredOrder);
			detail.setSubTotal(line.getSubTotal());
			detail.setPrice(line.getProductInfo().getPrice());
			detail.setQuantity(line.getQuantity());

			String code = line.getProductInfo().getId();
			Product product = this.productDAO.findProduct(code);
			detail.setProduct(product);

			session.persist(detail);
		}

		// Order Number!
		cartInfo.setOrderNum(orderNum);
		// Flush
		session.flush();
	}
	
	// @page = 1, 2, ...
	public PaginationResult<RegisteredOrderInfo> listRegisteredOrderInfo(int page, int maxResult, int maxNavigationPage) {
		String sql = "Select new " + RegisteredOrderInfo.class.getName()//
				+ "(rord.id, rord.createdAt, rord.orderNum, rord.total, "
				+ " rord.account.userName, rord.account.name, rord.account.email, rord.account.phone, rord.account.address) " + " from "
				+ RegisteredOrder.class.getName() + " rord "//
				+ " order by rord.orderNum desc";

		Session session = this.sessionFactory.getCurrentSession();
		Query<RegisteredOrderInfo> query = session.createQuery(sql, RegisteredOrderInfo.class);
		return new PaginationResult<RegisteredOrderInfo>(query, page, maxResult, maxNavigationPage);
	}
	
	public PaginationResult<RegisteredOrderInfo> listRegisteredOrderInfoByUser(String userName, int page, int maxResult, int maxNavigationPage) {
		String sql = "Select new " + RegisteredOrderInfo.class.getName()//
				+ "(rord.id, rord.createdAt, rord.orderNum, rord.total, "
				+ " rord.account.userName, rord.account.name, rord.account.email, rord.account.phone, rord.account.address) " + " from "
				+ RegisteredOrder.class.getName() + " rord "//
				+ " where rord.account.userName = :userName "
				+ " order by rord.orderNum desc";

		Session session = this.sessionFactory.getCurrentSession();
		Query<RegisteredOrderInfo> query = session.createQuery(sql, RegisteredOrderInfo.class);
		query.setParameter("userName", userName);
		return new PaginationResult<RegisteredOrderInfo>(query, page, maxResult, maxNavigationPage);
	}
	
	public RegisteredOrder findRegisteredOrder(String registeredOrderId) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.find(RegisteredOrder.class, registeredOrderId);
	}
	
	public RegisteredOrderInfo getRegisteredOrderInfo(String registeredOrderId) {
		RegisteredOrder registeredOrder = this.findRegisteredOrder(registeredOrderId);
		if (registeredOrder == null) {
			return null;
		}
		return new RegisteredOrderInfo(registeredOrder.getId(), registeredOrder.getCreatedAt(), //
				registeredOrder.getOrderNum(), registeredOrder.getTotal(), registeredOrder.getAccount().getUserName(),
				registeredOrder.getAccount().getName(), registeredOrder.getAccount().getEmail(),
				registeredOrder.getAccount().getPhone(), registeredOrder.getAccount().getAddress());
	}

	public List<RegisteredOrderDetailInfo> listRegisteredOrderDetailInfos(String registeredOrderId) {
		String sql = "Select new " + RegisteredOrderDetailInfo.class.getName() //
				+ "(d.id, d.product.id, d.product.title , d.quantity, d.price, d.subTotal) "//
				+ " from " + RegisteredOrderDetail.class.getName() + " d "//
				+ " where d.registeredOrder.id = :registeredOrderId ";

		Session session = this.sessionFactory.getCurrentSession();
		Query<RegisteredOrderDetailInfo> query = session.createQuery(sql, RegisteredOrderDetailInfo.class);
		query.setParameter("registeredOrderId", registeredOrderId);

		return query.getResultList();
	}
}
