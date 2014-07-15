package controllers.towner;


import namoo.club.comp.da.map.ClubPersistMapLifecycler;
import namoo.club.comp.dom.entity.Towner;
import namoo.club.comp.dom.lifecycle.ClubPersistLifecycler;
import namoo.club.comp.dom.lifecycle.ClubServiceLifecycler;
import namoo.club.comp.dom.service.TownerService;
import namoo.club.comp.service.pojo.ClubServicePojoLifecycler;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.common.errorPage;
import views.html.towner.login;

public class LoginController extends Controller {
	public static Result loginForm() {
		//
        return ok(login.render());
    }
	
	public static Result loginProcess() {
		//
		Form<Towner> loginForm = Form.form(Towner.class).bindFromRequest();
		
		String email = loginForm.get().getEmail();
		String password = loginForm.get().getPassword();
		
		if (loginForm.hasErrors()) {
			//
	        return badRequest(login.render());
	    } 

		ClubPersistLifecycler persistlifecycler = ClubPersistMapLifecycler.getInstance();
		ClubServiceLifecycler serviceLifecycler = ClubServicePojoLifecycler.getInstance(persistlifecycler); 
		TownerService townerService = serviceLifecycler.getTownerService();
		
		if (townerService.loginAsTowner(email, password)) {
			//
			Towner loginTowner = townerService.findTownerByEmail(email);
			session().clear();
			session("email", loginTowner.getEmail());
			
			System.out.println(loginTowner.getEmail() + " login success~!");
			
			return redirect("/club/list");
		} else {
			//
			session().clear();
			return ok(errorPage.render("로그인 실패", ""));
		}
    }
	
	public static Result logoutProcess() {
		//
		session().clear();
		
		return redirect("/towner/login");
	}
}
