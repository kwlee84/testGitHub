/*
 * COPYRIGHT (c) Nextree Consulting 2014
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hyunohkim@nextree.co.kr">Kim, HyunOh</a>
 * @since 2014. 6. 15.
 */

package controllers.towner.cmd;

import static org.springframework.util.StringUtils.isEmpty;

public class TownerCommand {
	//
	private String name;
	private String email;
	private String password;
	private String password2;
	private String gender;
	
	//--------------------------------------------------------------------------
	
	public boolean isValid() {
		//
		if (isEmpty(name) || isEmpty(email) || isEmpty(password) || isEmpty(password2)) {
			return false;
		}
		return true;
	}
	
	//--------------------------------------------------------------------------
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
