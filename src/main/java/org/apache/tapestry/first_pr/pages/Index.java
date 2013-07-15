package org.apache.tapestry.first_pr.pages;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;

import first_pr.entities.Users;

/**
 * Start page of application first_pr.
 */
public class Index
{
	@Property
	private String login;
	@Property
	private String password;
    @Inject
    private Session session;
    @InjectPage
    private Reg reg;
    
    public Object onSubmitFromLoginForm() {
	    Criteria criteria = session.createCriteria(Users.class)
	        .add(Restrictions.eq("login", login))
	        .add(Restrictions.eq("password", password));
	    if (!criteria.list().isEmpty()) {
			Index.showNotifyPane("Login exist!","Successfully",JOptionPane.INFORMATION_MESSAGE);
	        return true;
	    }
		Index.showNotifyPane("Login not exist! Go to Reg.","Error!", JOptionPane.ERROR_MESSAGE);
		return reg;    
	}
   
    public Object onSubmitFromInfoUser() {
	    Criteria criteria = session.createCriteria(Users.class)
	        .add(Restrictions.eq("login", login));
	    if (!criteria.list().isEmpty()) {
			Index.showNotifyPane("Login: "+login+"\n"+ 
					"Name: "+ Users.name + "\n" +
					"Password: "+ Users.password,
					"INFO:", JOptionPane.INFORMATION_MESSAGE);
	        return true;
	    }
		Index.showNotifyPane("Login not exist!","Error!", JOptionPane.ERROR_MESSAGE);
	    return false;    
	}
    
   
    public static void showNotifyPane(String message, String title, int msg) {
    	   JOptionPane pane = new JOptionPane(message, msg);
    	   JDialog dialog = pane.createDialog(title);
    	   dialog.setAlwaysOnTop(true);
    	   dialog.setVisible(true);
    	}
}
