package controllers.towner;


import namoo.club.comp.da.map.ClubPersistMapLifecycler;
import namoo.club.comp.dom.entity.Towner;
import namoo.club.comp.dom.lifecycle.ClubPersistLifecycler;
import namoo.club.comp.dom.lifecycle.ClubServiceLifecycler;
import namoo.club.comp.dom.service.TownerService;
import namoo.club.comp.dom.service.dto.TownerCDto;
import namoo.club.comp.service.pojo.ClubServicePojoLifecycler;
import namoo.club.util.enumtype.Gender;
import namoo.club.util.exception.NamooClubException;
import controllers.towner.cmd.TownerCommand;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.common.info_msg;
import views.html.towner.*;

public class TownerController extends Controller {
	
	public static Result registForm() {
		//
		return ok(register.render());
	}
	
	public static Result registProcess() {
		//
		Form<TownerCommand> townerForm = Form.form(TownerCommand.class);
		
		TownerCommand command = townerForm.bindFromRequest().get();
		
		if (!command.isValid()) {
			throw new NamooClubException("모든 정보를 입력하세요.");
		}
		
		String name = command.getName();
		String email = command.getEmail();
		String password = command.getPassword();
		String password2 = command.getPassword2();

		if (!password.equals(password2)) {
			throw new NamooClubException("패스워드를 확인하세요.");
		}
		
		ClubPersistLifecycler persistlifecycler = ClubPersistMapLifecycler.getInstance();
		ClubServiceLifecycler serviceLifecycler = ClubServicePojoLifecycler.getInstance(persistlifecycler); 
		TownerService townerService = serviceLifecycler.getTownerService();
		
		// 회원가입
		townerService.registTowner(new TownerCDto(name, email, password));
		
		// 부가정보 세팅 후 저장
		Towner towner = townerService.findTownerByEmail(email);
		towner.setGender(Gender.getByCode(command.getGender()));
		townerService.modifyTowner(towner);

		// 정보 메시지
		String message = "회원가입이 완료되습니다. 로그인 후 사용가능합니다.";
		String linkURL = "/towner/login";	
		
		return ok(info_msg.render(message, linkURL));
	}
	
	
}
