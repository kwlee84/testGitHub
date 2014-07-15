package controllers.club;

import java.util.List;

import namoo.club.comp.da.map.ClubPersistMapLifecycler;
import namoo.club.comp.dom.entity.Club;
import namoo.club.comp.dom.lifecycle.ClubPersistLifecycler;
import namoo.club.comp.dom.lifecycle.ClubServiceLifecycler;
import namoo.club.comp.dom.service.ClubService;
import namoo.club.comp.service.pojo.ClubServicePojoLifecycler;

import org.springframework.util.StringUtils;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.club.*;

public class ClubController extends Controller {
	//
	public static Result main() {
		//
		ClubPersistLifecycler persistlifecycler = ClubPersistMapLifecycler.getInstance();
		ClubServiceLifecycler serviceLifecycler = ClubServicePojoLifecycler.getInstance(persistlifecycler); 
		ClubService clubService = serviceLifecycler.getClubService();
		
		String loginEmail = session().get("email");
		
		if (!StringUtils.isEmpty(loginEmail)) {
			// 로그인 : 가입/미가입 클럽 목록
			List<Club> joined = clubService.findJoinedClubs(loginEmail);
			List<Club> unjoined = clubService.findUnjoinedClubs(loginEmail);

			return ok(club_list.render(joined, unjoined, null));
		} else {
			// 비로그인 : 전체 클럽 목록 
			List<Club> allClubs = clubService.findAllClubs();
			return ok(club_list.render(null, null, allClubs));
		}
    }
}
