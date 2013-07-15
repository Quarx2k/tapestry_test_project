package org.apache.tapestry.first_pr.pages;

import javax.swing.JOptionPane;

import first_pr.entities.Users;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.Property;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
public class Reg {
	
    @Property    
    private Users user;

    @InjectPage
    private Index index;
    
    @Inject
    private Session session;
    
	@Property
	private String login;
	
	@CommitAfter
	public Object onSubmitFromUser() {
	    Criteria criteria = session.createCriteria(Users.class)
		        .add(Restrictions.eq("login", user.getLogin()));
		if (user.getName() == null || 
				user.getPassword() == null || 
				user.getLogin() == null) 
		{ 
			Index.showNotifyPane("All fields should be filled!","Error!", JOptionPane.ERROR_MESSAGE);
			return false; 
		}
		if (!criteria.list().isEmpty()) {	
			Index.showNotifyPane("Login Already Exist!","Error!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		Index.showNotifyPane("User Registred!","Successfully!", JOptionPane.INFORMATION_MESSAGE);
    	session.saveOrUpdate(user);
    	return index;
	}
}
