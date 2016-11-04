package com.ugfind.controller;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DiffuseRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.MarbleRippleFilterFactory;
import com.github.bingoohuang.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.ugfind.dao.ContactMapper;
import com.ugfind.dao.SchooldeptMapper;
import com.ugfind.model.Contact;
import com.ugfind.model.Result;
import com.ugfind.model.SchoolServiceInfo;
import com.ugfind.model.Schoolvisits;
import com.ugfind.model.User;
import com.ugfind.service.SchoolService;
import com.ugfind.service.UserService;
import com.ugfind.util.LoginUtil;
import com.ugfind.util.SendMail;

@Controller
public class IndexController {
		@Resource
		private SchooldeptMapper schooldeptDao;
		@Resource 
		private SchoolService schoolService;
		@Resource
		private ContactMapper contactDao;
		@Resource
		private UserService userService;
		
	 	//patchca 验证码生成工具
    	private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		@RequestMapping("/back/login")
		public String toLogin(){
			return "back/login";
		}
		
		@RequestMapping("/front/login")
		public String toLogin_front(){
			return "front/login";
		}

		//进入前端手机
		@RequestMapping("/front")
		public String toLogin_front(HttpServletRequest request,Model model){
			User user = LoginUtil.getUserInfo(request);
			if(user!=null){
				Schoolvisits schoolvisits = schoolService.selectSchoolVisitsBySchoolId(user.getSchoolId());
				//查询该学校的访问详情
				model.addAttribute("schoolInfo", schoolvisits);
			}
			return "front/index";
		}
		
		//进入前端电脑端
		@RequestMapping("/front_pc")
		public String toLogin_frontPc(HttpServletRequest request,Model model){
			User user = LoginUtil.getUserInfo(request);
			if(user!=null){
				Schoolvisits schoolvisits = schoolService.selectSchoolVisitsBySchoolId(user.getSchoolId());
				//查询该学校的访问详情
				model.addAttribute("schoolInfo", schoolvisits);
			}
			return "front_pc/index";
		}
		
		//增加一次访问量
		@RequestMapping("/front/addSchoolVisits")
		@ResponseBody
		public Result addSchoolVisits(Integer schoolId){
			schoolService.addSchoolVisits(schoolId);
			return null;
		}
		
		/**
		 * 该请求用于分割后台用户进入哪一个部门
		 * @param request
		 * @return
		 */
		@RequestMapping("/back")
		public String toNews(HttpServletRequest request,Model model){
			User user = LoginUtil.getUserInfo(request);
			SchoolServiceInfo schoolInfo = null;
			String path = "back/";
			if(user!=null){
				schoolInfo = schooldeptDao.getSchoolServiceInfoByUserId(user.getId());
				if(schoolInfo == null){
					return path + "login";
				}
				model.addAttribute("schoolInfo", schoolInfo);
			}else{
				return path + "login";
			}
			switch (schoolInfo.getDeptId()) {
			case 1:    //图书馆
				path = path +"library";
				break;
			case 2: 	//教务处
				break;
			case 3: 	//就业区
				path = path +"employment";
				break;
			case 4: 	//办公区
				path = path +"office";
				break;
			case 5:		//学生会
				break;
			default:
				break;
			}
			return path;
		}
		
		//生成二维码
	    @RequestMapping("/patchca")
	    public void crimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	final Random random = new Random();
	        cs.setColorFactory(new ColorFactory() {
	            public Color getColor(int x) {
	                int[] c = new int[3];
	                int i = random.nextInt(c.length);
	                for (int fi = 0; fi < c.length; fi++) {
	                    if (fi == i) {
	                        c[fi] = random.nextInt(71);
	                    } else {
	                        c[fi] = random.nextInt(256);
	                    }
	                }
	                return new Color(c[0], c[1], c[2]);
	            }
	        });
	        RandomWordFactory wf = new RandomWordFactory();
	        wf.setCharacters("23456789abcdefghigkmnpqrstuvwxyzABCDEFGHIGKLMNPQRSTUVWXYZ");
	        wf.setMaxLength(4);
	        wf.setMinLength(4);
	        cs.setWordFactory(wf);
	    	
	        switch (random.nextInt(5)) {
	            case 0:
	                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
	                break;
	            case 1:
	                cs.setFilterFactory(new MarbleRippleFilterFactory());
	                break;
	            case 2:
	                cs.setFilterFactory(new DoubleRippleFilterFactory());
	                break;
	            case 3:
	                cs.setFilterFactory(new WobbleRippleFilterFactory());
	                break;
	            case 4:
	                cs.setFilterFactory(new DiffuseRippleFilterFactory());
	                break;
	        }
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	            session = request.getSession();
	        }
	        setResponseHeaders(response);
	        String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
	        session.setAttribute("captchaToken", token);
	    }
	    
	    //设置验证码http头
	    protected void setResponseHeaders(HttpServletResponse response) {
	        response.setContentType("image/png");
	        response.setHeader("Cache-Control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        long time = System.currentTimeMillis();
	        response.setDateHeader("Last-Modified", time);
	        response.setDateHeader("Date", time);
	        response.setDateHeader("Expires", time);
	    }
	    
	    //发送联系我们的信息
	    @RequestMapping("/setContactUs")
	    @ResponseBody
	    public Result setContactUs(Contact contact){
	    	Result result = new Result();
	    	contact.setCreateDate(sdf.format(new Date()));
	    	if(contactDao.insertSelective(contact) > 0){
	    		result.setStatus(1);
	    	}else{
	    		result.setStatus(2);
	    	}
			return result;
	    }
	    
	    //获得密码邮箱验证
	    @RequestMapping("/getForgetPwdCode")
	    @ResponseBody
	    public Result getForgetPwdCode(HttpServletRequest request,String userName){
	    	Result result = new Result();
	    	try {
				SendMail.send_email(request.getSession(false),userName);
				result.setStatus(1);
			} catch (Exception e) {
				e.printStackTrace();
				result.setStatus(2);
			}
	    	return result;
	    }
	    
	   @RequestMapping("updatePwdByCode")
	   @ResponseBody
		    public Result updatePwdByCode(HttpSession session,String userName,String passWord,String code){
		    	Result result = new Result();
		    	if(userService.updatePwdByCode(session,userName,passWord,code)>0){
		    		result.setStatus(1);
		    	}else{
		    		result.setStatus(2);
		    	}
				return result;
		    }
	   /**
	    * 查看当前学校是否开通了办公区
	    * @param schoolId
	    * @return
	    */
	   @RequestMapping("/back/isOpenDept")
	   @ResponseBody
	   public Result isOpenDept(Integer schoolId){
		   Result result = new Result();
		   Integer count = schooldeptDao.countOfDept(schoolId);
		   if(count>0){
			   //已开通并且服务时间未过期
			   result.setStatus(1);
		   }else{
			   //未开通或者服务时间到期
			   result.setStatus(2);
		   }
		   return result;
	   }
}
