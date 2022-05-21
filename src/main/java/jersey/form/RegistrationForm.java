package jersey.form;

import jersey.entity.Account;

public class RegistrationForm {
	
    private String userName;
    private String password;
    private String userRole;
    private boolean active;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    private boolean newAccount = false;

    public RegistrationForm() {
        this.newAccount = true;
    }

    public RegistrationForm(Account account) {
        this.userName = account.getUserName();
        this.password = account.getPassword();
        this.userRole = account.getUserRole();
        this.active = account.isActive();
        this.name = account.getName();
        this.email = account.getEmail();
        this.phone = account.getPhone();
        this.address = account.getAddress();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isNewAccount() {
        return newAccount;
    }

    public void setNewAccount(boolean newAccount) {
        this.newAccount = newAccount;
    }
    
}