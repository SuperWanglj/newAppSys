package cn.appsys.controller.developer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping("/deve")
public class UserController {
	@Resource
	private DevUserService devUserService;
	
	@RequestMapping("/login")
	public String login() {
		return "devlogin";
	}
	
	@RequestMapping("/develogin")
	public String develogin(@RequestParam String devCode,@RequestParam String devPassword, HttpServletRequest request  ) {
		DevUser user = null; 
		try {
			user=devUserService.login(devCode, devPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user!=null) {
			request.getSession().setAttribute(Constants.DEV_USER_SESSION, user);
			return "redirect:/deve/flatform/main";
		}else {
			request.setAttribute("error", "用户名或密码错误！");
			return "devlogin";
		}
	}
	/*
	 * 进入前台主页面
	 */
	@RequestMapping("/flatform/main")
	public String main(HttpSession session){
		if(session.getAttribute(Constants.DEV_USER_SESSION) == null){
			return "redirect:/deve/login";
		}
		return "developer/main";
	}
	@RequestMapping("/extis")
	public String extis(HttpServletRequest request) {
 		request.getSession().removeAttribute(Constants.DEV_USER_SESSION);
		return "devlogin";
	}

}
