package com.ugfind.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.Attestation;
import com.ugfind.model.Bespoke;
import com.ugfind.model.BespokeConfig;
import com.ugfind.model.BespokePageConfig;
import com.ugfind.model.BespokeParameter;
import com.ugfind.model.Boardroom;
import com.ugfind.model.BoardroomVo;
import com.ugfind.model.Result;
import com.ugfind.model.User;
import com.ugfind.service.AttestationService;
import com.ugfind.service.BespokeService;
import com.ugfind.service.BoardroomService;
import com.ugfind.service.UserService;
import com.ugfind.util.LoginUtil;
import com.ugfind.util.StringUtil;

@Controller
@RequestMapping("/office")
public class OfficeController {
	@Autowired
	private AttestationService attestationService;
	@Autowired
	private BespokeService bespokeService;
	@Autowired
	private BoardroomService boardroomService;
	@Autowired
	private UserService userService;
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 老师提交认证信息
	 * @param atteatation
	 * @return
	 */
	@RequestMapping("/submitAttestation")
	@ResponseBody
	public Result submitAttestation(HttpServletRequest request,Attestation atteatation){
		Result result = new Result();
		try {
			User user = LoginUtil.getUserInfo(request);
			if(user == null){
				result.setStatus(3);
				result.setMsg("请确认是否登录");
			}else{
				atteatation.setSchoolId(user.getSchoolId());
				atteatation.setApplyDate(sdf.format(new Date()));
				attestationService.insertSelective(atteatation);
				result.setStatus(1);
			}
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 获取不同审批状态的认证申请
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAttestationByState")
	@ResponseBody
	public Result getAllAttestation(HttpServletRequest request){
		Result result  = new Result();
		Integer state = Integer.valueOf(request.getParameter("state"));
		Integer schoolId = Integer.valueOf(request.getParameter("schoolId"));
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("atteState", state);
		map.put("schoolId", schoolId);
		List<Attestation> attestationList = attestationService.getAllAttestation(map);
		result.setData(attestationList);
		return result;
	}
	
	/**
	 * 修改认证申请/通过认证 或者  拒绝认证
	 * @param attestation
	 * @return
	 */
	@RequestMapping("/updateAttestation")
	@ResponseBody
	public Result updateAttestation(Attestation attestation){
		Result result = new Result();
		try {
			attestation.setAtteDate(sdf.format(new Date()));
			attestationService.updateByPrimaryKeySelective(attestation);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	/**
	 * 删除被拒绝的申请
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAttestation")
	@ResponseBody
	public Result deleteAttestation(Integer id){
		Result result = new Result();
		try {
			attestationService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 获取本校的会室
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllBoardroom")
	@ResponseBody
	public Result getAllBoardroom(HttpServletRequest request){
		Result result = new Result();
		Integer schoolId = Integer.valueOf(request.getParameter("schoolId"));
		List<Boardroom> boardroomList = boardroomService.getAllBoardroom(schoolId);
		result.setData(boardroomList);
		result.setStatus(1);
		return result;
	}
	/**
	 * 管理会室
	 * @param room
	 * @return
	 */
	@RequestMapping("/manageRoom")
	@ResponseBody
	public Result manageRoom(Boardroom room){
		Result result = new Result();
		if(room.getId() !=null){
			//id存在，修改
			try {
				boardroomService.updateByPrimaryKeySelective(room);
				result.setStatus(1);
			} catch (Exception e) {
				result.setStatus(2);
			}
		}else{
			//增加
			boardroomService.insertSelective(room);
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 删除会室
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRomm")
	@ResponseBody
	public Result deleteRomm(HttpServletRequest request){
		Result result = new Result();
		Integer roomId = Integer.valueOf(request.getParameter("roomId"));
		try {
			boardroomService.deleteByPrimaryKey(roomId);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 根据会室获取会室所有的当前时间段的预约情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/getBespokeByRoom")
	@ResponseBody
	public Result getBespokeByRoom(HttpServletRequest request){
		Result result = new Result();
		Integer roomId = Integer.valueOf(request.getParameter("roomId"));
		String dateStartStr = request.getParameter("startDate");
		String dateEndStr = request.getParameter("endDate");
		BespokeParameter para = new BespokeParameter();
		para.setRoomId(roomId);
		try {
			Date startTime= sdf.parse(dateStartStr);
			para.setStartTime(startTime);
			Date endTime = sdf.parse(dateEndStr);
			para.setEndTime(endTime);
		} catch (ParseException e) {
			result.setMsg("时间格式不正确");
			e.printStackTrace();
		}
		List<Bespoke> bespokeList = bespokeService.getBespokeByRoom(para);
		result.setData(bespokeList);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 管理员后台获取会室的预约情况
	 * @param roomId
	 * @return
	 */
	@RequestMapping("/getBespokeBack")
	@ResponseBody
	public Result getBespokeBack(Integer roomId){
		Result result = new Result();
		BespokeConfig config = new BespokeConfig();
		config.setRoomId(roomId);
		config.setThisDay(new Date(getStartTime()));
		List<Bespoke> bespokeList = bespokeService.getBespokeBack(config);
		result.setData(bespokeList);
		result.setStatus(1);
		return result;
	}
	/**
	 * 获取今天的开始时间
	 * @return
	 */
	private Long getStartTime(){  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0); 
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime().getTime();  
    } 
	
	/**
	 * 判断预约时间是否可以使用
	 * @param request
	 * @return
	 */
	@RequestMapping("/judgeTime")
	@ResponseBody
	public Result judgeTime(HttpServletRequest request){
		Result result = new Result();
		Integer roomId = Integer.valueOf(request.getParameter("roomId"));
		String dateStartStr = request.getParameter("startDate");
		Date startTime;
		BespokeParameter para = new BespokeParameter();
		try {
			startTime = sdf.parse(dateStartStr);
			para.setStartTime(startTime);
		} catch (ParseException e) {
		}
		para.setRoomId(roomId);
		int count = bespokeService.judgeTime(para);
		if(count>0){
			result.setStatus(2);
		}else{
			result.setStatus(1);
		}
		return result;
	}
	
	/**
	 * 后台管理员删除预约记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteBespokeBack")
	@ResponseBody
	public Result deleteBespoke(HttpServletRequest request){
		Result result = new Result();
		Integer id = Integer.valueOf(request.getParameter("bespokeId"));
		try {
			bespokeService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 会室预约
	 * @param request
	 * @return
	 */
	@RequestMapping("/bespokeRoom")
	@ResponseBody
	public Result bespokeRoom(HttpServletRequest request){
		Result result = new Result();
		Integer roomId = Integer.valueOf(request.getParameter("roomId"));
		String dateStartStr = request.getParameter("startDate");
		String dateEndStr = request.getParameter("endDate");
		int a1 = Integer.valueOf(dateStartStr.substring(0,4));
		int a2 = Integer.valueOf(dateEndStr.substring(0,4));
		//System.out.println(a1!=a2);
		if((Integer.valueOf(dateStartStr.substring(8, 10)) != Integer.valueOf(dateEndStr.substring(8, 10)))||(Integer.valueOf(dateStartStr.substring(5, 7)) != Integer.valueOf(dateEndStr.substring(5, 7)))||(a1!=a2)){
			result.setStatus(2);
			result.setMsg("时间不合法，请选择正确的时间，开始时间和结束时间必须是同一天。");
		}else{
			Integer userId = Integer.valueOf(request.getParameter("userId"));
			BespokeParameter para = new BespokeParameter();
			Bespoke bespoke = new Bespoke();
			bespoke.setAppUser(userId);
			bespoke.setRoomId(roomId);
			bespoke.setCreateTime(sdf.format(new Date()));
			para.setRoomId(roomId);
			try {
				Date startTime= sdf.parse(dateStartStr);
				para.setStartTime(startTime);
				bespoke.setStartTime(startTime);
				Date endTime = sdf.parse(dateEndStr);
				para.setEndTime(endTime);
				bespoke.setEndTime(endTime);
				bespoke.setCreateTime(sdf.format(new Date()));;
				//先查看所选时间段是否已经被预约
				int count = bespokeService.checkBespokeState(para);
				if(count>0){
					//已经被预约
					result.setStatus(2);
					result.setMsg("本会室该时间段已经被预约了！");
				}else{
					//没有被预约则开始预约
					bespokeService.insertSelective(bespoke);
					result.setStatus(1);
				}
			} catch (ParseException e) {
				result.setStatus(2);
				result.setMsg("预约失败");
			}
		}
		return result;
	}
	
	/**
	 * front
	 * 根据日期获取会室情况
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoomReservationList")
	@ResponseBody
	public Result getRoomReservationList(HttpServletRequest request,Integer schoolId, String selectDate){
		Result result = new Result();
		try {
			BespokeParameter para = new BespokeParameter();
			para.setSchoolId(schoolId);
			if(!StringUtil.isEmpty(selectDate)){
				para.setStartTime(sdf.parse(selectDate + " 00:00:00"));
				para.setEndTime(sdf.parse(selectDate + " 23:59:59"));
			}
			List<BoardroomVo> list = bespokeService.getFrontRoomList(para);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class , new com.ugfind.util.DateJSONValueProcessor("yyyy-MM-dd HH:mm"));
			result.setResult(1, JSONArray.fromObject(list,jsonConfig), null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(2);
		}
		return result;
	}
	/**
	 * front
	 * 删除会室预约情况(时间小于当前日期的就不允许删除)
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRoomReservation")
	@ResponseBody
	public Result deleteRoomReservation(HttpServletRequest request,Integer id){
		Result result = new Result();
		try {
			Bespoke m = bespokeService.selectByPrimaryKey(id);
			if(m != null){
				User user = LoginUtil.getUserInfo(request);
				if(user!=null){
					//判断是否是自己的预约记录
					if(user.getId() != m.getAppUser()){
						result.setResult(2, null, "暂不允许删除");
						return result;
					}
				}else{
					result.setResult(2, null, "暂不允许删除");
					return result;
				}
				
			/*	if(m.getStartTime().getTime() <= new Date().getTime()){
					result.setResult(2, null, "不允许删除当前时间以前的记录");
					return result;
				}*/
				
				Bespoke record = new Bespoke();
				record.setId(id);
				record.setDeleteUserId(user.getId());
				record.setDeleteTime(sdf.format(new Date()));
				record.setState(0);//预约有效状态  0已删除  1有效
				int n = bespokeService.updateByPrimaryKeySelective(record);
				if(n > 0){
					result.setResult(1, null, null);
				}else{
					result.setResult(2, null, "删除失败");
				}
				
			}else{
				//未查到该记录
				result.setResult(2, null, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(2, null, "删除失败");
		}
		return result;
	}
	
	/**
	 * front
	 * 新增会议预约记录
	 * @param request
	 * @param roomId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping("/addReservation")
	@ResponseBody
	public Result bespokeRoom(HttpServletRequest request,int roomId,String startTime,String endTime){
		Result result = new Result();
		try {
			Boardroom room = boardroomService.selectByPrimaryKey(roomId);
			if(room == null){
				result.setResult(2, null, "会室信息有误，预约失败");
				return result;
			}
			
			User user = LoginUtil.getUserInfo(request);
			if(user == null){
				result.setResult(2, null, "请确认是否登录");
				return result;
			}
			//attestationState 老师身份验证字段,默认0未认证，1通过认证2拒绝认证
			/*if(user.getAttestationState()== null || user.getAttestationState() != 1){
				result.setResult(2, null, "仅允许老师预约!");
				return result;
			}*/
			//userType 3代表老师 ;1代表管理员
			User tempUser = userService.getUserById(user.getId());
			if(tempUser == null){
				result.setResult(2, null, "查询无结果，预约失败");
				return result;
			}
			if(tempUser.getUserType() != null){
				if(tempUser.getUserType() == 3 || tempUser.getUserType() == 1){
					//判断该老师及会议室是否同属一个学校
					int sid = user.getSchoolId();
					int rid = room.getSchoolId();
					if(sid != rid){
						result.setResult(2, null, "所属学校有误，不允许预约");
						return result;
					}
					
					BespokeParameter para = new BespokeParameter();
					
					para.setRoomId(roomId);
					para.setStartTime(sdf.parse(startTime));
					para.setEndTime(sdf.parse(endTime));
					
					//先查看所选时间段是否已经被预约
					int count = bespokeService.checkBespokeState(para);
					if(count>0){
						//已经被预约
						result.setStatus(2);
						result.setMsg("本会室该时间段已经被预约了！");
					}else{
						//没有被预约则开始预约
						Bespoke bespoke = new Bespoke();
						bespoke.setAppUser(tempUser.getId());
						bespoke.setRoomId(roomId);
						bespoke.setStartTime(para.getStartTime());
						bespoke.setEndTime(para.getEndTime());
						bespoke.setCreateTime(sdf.format(new Date()));
						int n = bespokeService.insertSelective(bespoke);
						if(n > 0){
							result.setStatus(1);
						}else{
							result.setStatus(2);
							result.setMsg("预约失败!");
						}
					}
				}else{
					result.setResult(2, null, "仅允许老师及管理员预约!");
				}
			}else{
				result.setResult(2, null, "仅允许老师及管理员预约!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(2);
			result.setMsg("预约失败!");
		}
		return result;
	}
	
	/**
	 * 根据申请用户id获取认证老师的信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAttestation")
	@ResponseBody
	public Result getAttestation(HttpServletRequest request){
		Result result = new Result();
		try {
			User user = LoginUtil.getUserInfo(request);
			if(user == null){
				result.setResult(10, null, "请确认是否登录");
				return result;
			}
			Attestation att = attestationService.selectByApplyUserId(user.getId());
			result.setResult(1, att, null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(2, null, null);
		}
		return result;
	}
	/**
	 * 根据用户id获取认证老师的状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserAttestation")
	@ResponseBody
	public Result getUserAttestation(HttpServletRequest request){
		Result result = new Result();
		try {
			User user = LoginUtil.getUserInfo(request);
			if(user == null){
				result.setResult(10, null, "请确认是否登录");
				return result;
			}
			User user2 = userService.getUserAttestationInfo(user.getId());
			result.setResult(1, user2, null);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(2, null, null);
		}
		return result;
	}
	/**
	 * 根据会室id获取该会室预约详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/getReservationDetail")
	@ResponseBody
	public Result getRoomReservationDetail(HttpServletRequest request,Integer roomId,String selectDate){
		Result result = new Result();
		try {
			if(StringUtil.isEmpty(selectDate) || roomId == null){
				result.setResult(2, null, "参数有空");
			}else{
				String startTime = selectDate + " 00:00:00";
				String endTime = selectDate + " 23:59:59";
				List<Bespoke> list = bespokeService.getBespokeBackByRoomIdAndDate(roomId, startTime, endTime);
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class , new com.ugfind.util.DateJSONValueProcessor("yyyy-MM-dd HH:mm"));
				result.setResult(1, JSONArray.fromObject(list,jsonConfig), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(2, null, null);
		}
		return result;
	}
	
	/**
	 * 删除会室预约记录（修改删除状态为0）
	 * @param bsId
	 * @return
	 */
	@RequestMapping("/changeDeleteState")
	@ResponseBody
	public Result changeDeleteState(Integer bsId,HttpServletRequest request){
		Result result = new Result();
		Bespoke bespoke = new Bespoke();
		User user = LoginUtil.getUserInfo(request);
		bespoke.setId(bsId);
		bespoke.setDeleteUserId(user.getId());
		bespoke.setDeleteTime(sdf.format(new Date()));
		bespoke.setState(0);
		try {
			bespokeService.updateByPrimaryKeySelective(bespoke);
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 *已经删除的列表 
	 * @param pageConfig
	 * @return
	 */
	@RequestMapping("/getDeleteList")
	@ResponseBody
	public Result getDeleteList(BespokePageConfig pageConfig){
		Result result = new Result();
		//获取总条数
		Integer totalCount = bespokeService.getDeleteCount(pageConfig.getSchoolId());
		result.setMsg(totalCount+"");
		pageConfig.setCurrentIndex(pageConfig.getCurrentPage()*pageConfig.getItemsPerPage());
		List<Bespoke> bespokeList = bespokeService.selectDeleteRecord(pageConfig);
		if(bespokeList!=null){
			result.setStatus(1);
			result.setData(bespokeList);
		}else{
			result.setStatus(2);
		}
		return result;
	}
}
