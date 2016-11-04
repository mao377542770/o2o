package com.ugfind.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ugfind.model.Activity;
import com.ugfind.model.ActivityConfig;
import com.ugfind.model.Applymodule;
import com.ugfind.model.Consult;
import com.ugfind.model.ConsultPage;
import com.ugfind.model.Deadline;
import com.ugfind.model.Librarynews;
import com.ugfind.model.Librarynote;
import com.ugfind.model.Literature;
import com.ugfind.model.LiteratureConfig;
import com.ugfind.model.Literaturetype;
import com.ugfind.model.Module;
import com.ugfind.model.NewsPageConfig;
import com.ugfind.model.Result;
import com.ugfind.model.Rules;
import com.ugfind.model.User;
import com.ugfind.model.Voteoption;
import com.ugfind.service.ActivityService;
import com.ugfind.service.ActivityvoteService;
import com.ugfind.service.ApplymoduleService;
import com.ugfind.service.ConsultService;
import com.ugfind.service.DeadlineService;
import com.ugfind.service.LibrarynewsService;
import com.ugfind.service.LibrarynoteService;
import com.ugfind.service.LiteratureService;
import com.ugfind.service.LiteratureTypeService;
import com.ugfind.service.ModuleService;
import com.ugfind.service.RulesService;
import com.ugfind.service.VoteoptionService;
import com.ugfind.util.LoginUtil;

@Controller
@RequestMapping("/library")
public class LibraryController {
	@Autowired
	private LibrarynewsService libraryService;
	@Autowired
	private LiteratureService literatureService;
	@Autowired
	private LiteratureTypeService literatureTypeService;
	@Autowired
	private DeadlineService deadLineService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ApplymoduleService applyModuleService;
	@Autowired
	private LibrarynoteService libraryNoteService;
	@Autowired
	private RulesService rulesService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActivityvoteService activityVoteService;
	@Autowired
	private VoteoptionService voteOptionService;
	@Autowired
	private ConsultService consultService;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 分页获取公告列表
	 * @param newsPage
	 * @return
	 */
	@RequestMapping("/getNewsList")
	@ResponseBody
	public Result getNewsList(NewsPageConfig newsPage){
		Result result = new Result();
		//获取总条数
		Integer totalCount = libraryService.getSchoolNewsCount(newsPage);
		result.setMsg(totalCount+"");
		//公告
		newsPage.setCurrentIndex(newsPage.getCurrentPage()*newsPage.getItemsPerPage());
		List<Librarynews> newsList = libraryService.getNewsBypage(newsPage);
		if(newsList!=null){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		result.setData(newsList);
		return result;
	}
	
	/**
	 * 获得新闻详情
	 * @return
	 */
	@RequestMapping("/getNewsInfo")
	@ResponseBody
	public Result getNewsInfo(Integer newsId){
		Result result = new Result();
		Librarynews librarynews =libraryService.getNewsInfo(newsId);
		result.setResult(1,librarynews, null);
		return result;
	}
	
	
	/**
	 * 添加公告
	 * @param news
	 * @return
	 */
	@RequestMapping("/addNews")
	@ResponseBody
	public Result addNews(Librarynews news){
		Result result = new Result();
		try {
			news.setPublishDate(sdf.format(new Date()));
			libraryService.insertSelective(news);
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(2);
		}
		return result;
	}
	/**
	 * 修改一条公告
	 * @param news
	 * @return
	 */
	@RequestMapping("/updateNews")
	@ResponseBody
	public Result updateNews(Librarynews news){
		Result result = new Result();
		try {
			libraryService.updateByPrimaryKeySelective(news);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 删除一条公告
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteNews")
	@ResponseBody
	public Result deleteNews(HttpServletRequest request){
		Result result = new Result();
		Integer id  = Integer.valueOf(request.getParameter("newsId"));
		try {
			libraryService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	
	/**
	 * 图书馆公告阅读次数加1
	 * @return
	 */
	@RequestMapping("/libraryNewsView")
	@ResponseBody
	public Result libraryNewsView(Integer newsId){
		Result result = new Result();
		libraryService.updateLibraryNewsViewCount(newsId);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 获取所有文献类型
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllType")
	@ResponseBody
	public Result getLiteratureType(HttpServletRequest request){
		Result result  = new Result();
		Integer schoolId = Integer.valueOf(request.getParameter("schoolId"));
		List<Literaturetype> typeList = literatureTypeService.getAllType(schoolId);
		result.setData(typeList);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 添加文献类型
	 * @param type
	 * @return
	 */
	@RequestMapping("/addLiType")
	@ResponseBody
	public Result addLiteratureType(Literaturetype type){
		Result result  = new Result();
		literatureTypeService.insertSelective(type);
		Integer id = type.getId();
		result.setStatus(1);
		result.setMsg(id+"");
		return result;
	}
	/**
	 * 修改文献类型
	 * @param type
	 * @return
	 */
	@RequestMapping("/updateLiType")
	@ResponseBody
	public Result updateLiteratureType(Literaturetype type){
		Result result = new Result();
		try {
			int n = literatureTypeService.updateByPrimaryKeySelective(type);
			if(n>0){
				result.setStatus(1);
			}
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 删除文献类型
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteLiType")
	@ResponseBody
	public Result deleteLiType(HttpServletRequest request){
		Result result = new Result();
		Integer id  = Integer.valueOf(request.getParameter("id"));
		try {
			literatureTypeService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 添加文献
	 * @param literature
	 * @return
	 */
	@RequestMapping("/addLiterature")
	@ResponseBody
	public Result addLiterature(Literature literature){
		Result result = new Result();
		literatureService.insertSelective(literature);
		Integer id = literature.getId();
		result.setMsg(id+"");
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 删除一条文献
	 * @param literature
	 * @return
	 */
	@RequestMapping("/deleteLiter")
	@ResponseBody
	public Result deleteLiterature(Literature literature){
		Result result = new Result();
		try {
			literatureService.deleteByPrimaryKey(literature.getId());
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 修改一条文献记录
	 * @param liter
	 * @return
	 */
	@RequestMapping("/updateLiterature")
	@ResponseBody
	public Result updateLiterature(Literature liter){
		Result result  = new Result();
		try {
			literatureService.updateByPrimaryKeySelective(liter);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 获取文献列表
	 * @param literCon
	 * @return
	 */
	@RequestMapping("/getAllLiterature")
	@ResponseBody
	public Result getAllLiterature(LiteratureConfig literCon){
		Result result = new Result();
		Integer count = literatureService.getLiteratureCount(literCon);
		literCon.setCurrentIndex(literCon.getCurrentPage()*literCon.getItemsPerPage());
		List<Literature> literatureList = literatureService.getAllLiterature(literCon);
		if(literatureList !=null){
			result.setStatus(1);
			result.setData(literatureList);
			result.setMsg(count+"");
		}
		return result;
	}
	
	/**
	 * 获取部门已经开通的功能
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMyModule")
	@ResponseBody
	public Result getDepartModule(HttpServletRequest request){
		Result result = new Result();
		Integer schoolId = Integer.valueOf(request.getParameter("schoolId"));
		Integer deptId = Integer.valueOf(request.getParameter("deptId"));
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("schoolId", schoolId);
		map.put("deptId", deptId);
		List<Deadline> deadLineList = deadLineService.getMyAllModule(map);
		result.setData(deadLineList);
		result.setStatus(1);
		return result;
	}
	/**
	 * 获取本部门所有可以开通的功能
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDeptAllModule")
	@ResponseBody
	public Result getDeptAllModule(HttpServletRequest request){
		Result result = new Result();
		Integer deptId = Integer.valueOf(request.getParameter("deptId"));
		List<Module> moduleList = moduleService.getAllModule(deptId);
		result.setData(moduleList);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 修改排序
	 * @param deadLine
	 * @return
	 */
	@RequestMapping("/saveSequence")
	@ResponseBody
	public Result saveSequence(@RequestBody  List<Deadline> deadLine){
		Result result = new Result();
		try {
			deadLineService.updateSequence(deadLine);
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 申请开通新模块
	 * @param module
	 * @return
	 */
	@RequestMapping("/applyModule")
	@ResponseBody
	public Result applyModule(Applymodule module){
		Result result  = new Result();
		applyModuleService.insertSelective(module);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 修改增加入馆须知或者开放时间
	 * @param note
	 * @return
	 */
	@RequestMapping("/manageLibraryNote")
	@ResponseBody
	public Result manageLibraryNote(Librarynote note){
		Result result = new Result();
		if(note.getId()!=null){
			//存在id,修改
			try {
				libraryNoteService.updateByPrimaryKeySelective(note);
				result.setStatus(1);
			} catch (Exception e) {
				result.setStatus(2);
			}
		}else{
			//不存在id 插入
			libraryNoteService.insertSelective(note);
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 获取本院本部门的入馆须知和开放时间
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPresention")
	@ResponseBody
	public Result getPresention(HttpServletRequest request){
		Result result = new Result();
		Integer deptId = Integer.valueOf(request.getParameter("deptId"));//部门id
		Integer schoolId = Integer.valueOf(request.getParameter("schoolId"));//学校id
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("deptId", deptId);
		map.put("schoolId", schoolId);
		Librarynote note = libraryNoteService.getNoteBydeptAndSchool(map);
		result.setData(note);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 获取本部门所有的规章制度
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRules")
	@ResponseBody
	public Result getRules(HttpServletRequest request){
		Result result = new Result();
		Integer deptId = Integer.valueOf(request.getParameter("deptId"));//部门id
		Integer schoolId = Integer.valueOf(request.getParameter("schoolId"));//学校id
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("deptId", deptId);
		map.put("schoolId", schoolId);
		List<Rules> rulesList = rulesService.getRules(map);
		result.setData(rulesList);
		result.setStatus(1);
		return result;
	}
	/**
	 * 管理规章制度
	 * @param rule
	 * @return
	 */
	@RequestMapping("/manageRule")
	@ResponseBody
	public Result manageRule(Rules rule){
		Result result = new Result();
		if(rule.getId() !=null){
			try {
				rulesService.updateByPrimaryKeySelective(rule);//修改
				result.setStatus(1);
			} catch (Exception e) {
				result.setStatus(2);
			}
		}else{
			rulesService.insertSelective(rule);//插入
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 删除一条
	 * @param request
	 * @return 
	 * @return
	 */
	@RequestMapping("/getRuleById")
	@ResponseBody
	public Result ResultgetRuleById(Integer id){
		Result result = new Result();
		Rules rule = rulesService.selectByPrimaryKey(id);
		result.setStatus(1);
		result.setData(rule);
		return result;
	}
	
	
	/**
	 * 删除一条
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRule")
	@ResponseBody
	public Result deleteRule(HttpServletRequest request){
		Result result = new Result();
		Integer id = Integer.valueOf(request.getParameter("id"));
		try {
			rulesService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 获取活动公告列表或者投票公告列表
	 * @param acConfig
	 * @return
	 */
	@RequestMapping("/getActivity")
	@ResponseBody
	public Result getAllActivity(ActivityConfig acConfig){
		Result result = new Result();
		int count = activityService.getAcCount(acConfig);
		result.setMsg(count+"");
		acConfig.setCurrentIndex(acConfig.getCurrentPage() * acConfig.getItemsPerPage());
		List<Activity> activityList = activityService.getAllActivity(acConfig);
		result.setData(activityList);
		return result;
	}
	
	/**
	 * 管理活动和投票
	 * @param activity
	 * @return
	 */
	@RequestMapping("/manageActivity")
	@ResponseBody
	public Result manageActivity(Activity activity){
		Result result = new Result();
		if(activity.getId() !=null){
			try { //修改
				activityService.updateByPrimaryKeySelective(activity);
				result.setStatus(1);
			} catch (Exception e) {
				result.setStatus(2);
			}
		}else{//增加
			activity.setPublishDate(sdf.format(new Date()));
			activityService.insertSelective(activity);
			Integer id = activity.getId();
			result.setMsg(id+"");
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 删除活动投票
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteActivity")
	@ResponseBody
	public Result deleteActivity(HttpServletRequest request){
		Result result = new Result();
		Integer id = Integer.valueOf(request.getParameter("id"));
		try {
			activityService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 增加一次阅览数
	 * @param request
	 * @return
	 */
	@RequestMapping("/addViewCount")
	@ResponseBody
	public Result addViewCount(HttpServletRequest request){
		Result result = new Result();
		Integer id = Integer.valueOf(request.getParameter("id"));
		activityService.addViewCount(id);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 位投票公告添加投票项
	 * @param option
	 * @return
	 */
	@RequestMapping("/addOption")
	@ResponseBody
	public Result addVoteOption(Voteoption option){
		Result result = new Result();
		voteOptionService.insertSelective(option);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 修改投票项
	 * @param option
	 * @return
	 */
	@RequestMapping("/updateOption")
	@ResponseBody
	public Result updateOption(Voteoption option){
		Result result = new Result();
		try {
			voteOptionService.updateByPrimaryKeySelective(option);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 删除一个投票项
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteOption")
	@ResponseBody
	public Result deleteOption(HttpServletRequest request){
		Result result = new Result();
		Integer id = Integer.valueOf(request.getParameter("id"));
		try {
			voteOptionService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 获取某一个投票的投票项
	 * @param request
	 * @return
	 */
	@RequestMapping("/getOPtionsByActivity")
	@ResponseBody
	public Result getOPtionsByActivity(HttpServletRequest request){
		Result result = new Result();
		Integer activityId = Integer.valueOf(request.getParameter("activityId"));
		List<Voteoption> options = voteOptionService.selectOptionsByActivityId(activityId);
		result.setData(options);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws FileUploadException 
	 */
	@RequestMapping("/fileUpload")
	@ResponseBody
	public Result uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, FileUploadException{
		Result result = new Result();
		if (!file.isEmpty()) {
			 try {
				 String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
				 String folderName = sdf1.format(new Date()).toString();
				 String newFileName = Long.toString(new Date().getTime());
				 String path2 = "upload/" + folderName + "/"+ newFileName +"."+ prefix;
	             String filePath = request.getSession().getServletContext().getRealPath("/") + path2;
	                File saveDir = new File(filePath);
	                if (!saveDir.getParentFile().exists())
	                    saveDir.getParentFile().mkdirs();
	                // 转存文件
	                file.transferTo(saveDir);
	                result.setResult(1,path2,"上传成功");
	            } catch (Exception e) {
	                e.printStackTrace();
	                result.setResult(2,null,"上传失败");
	            }
	        }
		return result;
	}
	
	/**
	 * 管理员获取本部门的所有咨询
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDeptConsult")
	@ResponseBody
	public Result getDeptConsult(ConsultPage conPage){
		Result result = new Result();
		Integer count = consultService.getConsultCount(conPage);
		List<Consult> consultList = consultService.getMyDeptConsult(conPage);
		result.setData(consultList);
		result.setMsg(count+"");
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 管理员回复用户提出的咨询/修改咨询内容
	 * @param consult
	 * @return
	 */
	@RequestMapping("/answerConsult")
	@ResponseBody
	public Result answerConsult(Consult consult){
		Result result = new Result();
		Date replyTime  = new Date();
		consult.setReplyTime(replyTime);
		try {
			consultService.updateByPrimaryKeySelective(consult);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 学生获取自己提出的所有咨询
	 * @param request
	 * @return
	 */
	@RequestMapping("/stuGetConsult")
	@ResponseBody
	public Result stuGetConsult(HttpServletRequest request){
		Result result = new Result();
		try {
//			Integer userId = Integer.valueOf(request.getParameter("userId"));
			Integer userId = null;
			User user = LoginUtil.getUserInfo(request);
			if(user == null){
				result.setResult(2, null, "请确认是否登录");
				return result;
			}
			userId = user.getId();
			List<Consult> consultList = consultService.getMyconsult(userId);
			result.setStatus(1);
			result.setData(consultList);
		} catch (NumberFormatException e) {
			result.setStatus(2);
			result.setData(null);
		}
		return result;
	}
	
	/**
	 * 获得咨询详情
	 * @return
	 */
	@RequestMapping("/getConsultInfo")
	@ResponseBody
	public Result getConsultInfo(Integer id){
		Result result = new Result();
		Consult consult = consultService.getConsultInfo(id);
		result.setResult(1, consult, null);
		return result;
	}
	
	/**
	 * 获得咨询详情
	 * @return
	 */
	@RequestMapping("/saveConsult")
	@ResponseBody
	public Result saveConsult(Consult consult){
		Result result = new Result();
		consult.setAskTime(new Date(System.currentTimeMillis()));
		if(consultService.saveConsult(consult) > 0){
			result.setResult(1, consult, null);
		}else{
			result.setStatus(2);
		}
		return result;
	}
	
}
