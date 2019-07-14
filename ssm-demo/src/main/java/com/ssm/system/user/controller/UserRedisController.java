package com.ssm.system.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.system.redis.RedisBaiseTakes;
import com.ssm.system.user.model.User;

@Controller
@RequestMapping("test")
public class UserRedisController {

	@Resource(name = "seeUserRedisTakes")
	private RedisBaiseTakes seeUserRedisTakes;

	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView();
		System.out.println("hello see");
		seeUserRedisTakes.add("hello1", "abcdefg");
		mv.setViewName("hello");
		return mv;
	}

	/*@RequestMapping("/hello2.do")
	public ModelAndView hello2(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User seeUser = new User();
		seeUser.setId("1");
		seeUser.setIp(GetUserInfo.getIpAddr(request));
		seeUser.setSeeTime(CommTools.getNoewTime("yyyy-MM-dd HH:mm:ss"));
		seeUser.setSeeCount(1);
		seeUserRedisTakes.addObj("seeUser", seeUser.getId(), seeUser);
		mv.setViewName("hello");
		return mv;
	}

	@RequestMapping("/hello3.do")
	public ModelAndView hello3(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User seeUser = (User) seeUserRedisTakes.getObj("seeUser", "1");
		if (seeUser != null) {
			System.out.println(seeUser.getId() + "=======" + seeUser.getIp() + "======" + seeUser.getSeeTime()
					+ "=======" + seeUser.getSeeCount());
		}

		mv.setViewName("hello");
		return mv;
	}
*/
}
