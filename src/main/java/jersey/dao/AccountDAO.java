package jersey.dao;

import jersey.entity.Account;
import jersey.entity.RegisteredOrder;
import jersey.form.RegistrationForm;
import jersey.model.AccountInfo;
import jersey.model.RegisteredOrderInfo;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void save(RegistrationForm registrationForm) {

        Session session = this.sessionFactory.getCurrentSession();
        String userName = registrationForm.getUserName();

        Account account = null;

        boolean isNew = false;
        if (userName != null) {
        	account = this.findAccount(userName);
        }
        if (account == null) {
            isNew = true;
            account = new Account();
            account.setCreatedAt(new Date());
        }
        account.setUserName(userName);
        account.setPassword(registrationForm.getPassword());
        account.setUserRole("ROLE_CUSTOMER");
        account.setActive(true);
        account.setName(registrationForm.getName());
        account.setEmail(registrationForm.getEmail());
        account.setPhone(registrationForm.getPhone());
        account.setAddress(registrationForm.getAddress());

        if (isNew) {
            session.persist(account);
        }

        session.flush();
    }
    
    public Account findAccount(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Account.class, userName);
    }
    
    public AccountInfo getAccountInfo(String userName) {
    	Account account = this.findAccount(userName);
    	if(account == null) {
    		return null;
    	}
    	return new AccountInfo(account.getUserName(), account.getUserRole(), account.getName(),
    						   account.getEmail(), account.getPhone(), account.getAddress(), account.getCreatedAt());
    }
    
    public List<RegisteredOrderInfo> listRegisteredOrderInfos(String userName){
		String sql = "Select new " + RegisteredOrderInfo.class.getName() //
				+ "(ro.id, ro.createdAt, ro.orderNum , ro.total, ro.account.userName, ro.account.name, ro.account.email, ro.account.phone, ro.account.address) "//
				+ " from " + RegisteredOrder.class.getName() + " ro "//
				+ " where ro.account.userName = :userName ";

		Session session = this.sessionFactory.getCurrentSession();
		Query<RegisteredOrderInfo> query = session.createQuery(sql, RegisteredOrderInfo.class);
		query.setParameter("userName", userName);

		return query.getResultList();
    }
    
}