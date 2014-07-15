package controllers;

import play.mvc.*;

public class HomeController extends Controller {

    public static Result index() {
        return redirect("/towner/login");
    }
    //로컬
}
 
