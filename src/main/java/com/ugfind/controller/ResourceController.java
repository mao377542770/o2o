package com.ugfind.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ugfind.dao.ResourceMapper;
import com.ugfind.model.PageInfoSearch;
import com.ugfind.model.Resource;
import com.ugfind.model.ResourcePoJo;
import com.ugfind.model.ResourceType;
import com.ugfind.model.Result;
import com.ugfind.model.User;
import com.ugfind.service.ResourceService;
import com.ugfind.serviceImpl.ResourceFilePoJo;
import com.ugfind.util.LoginUtil;


@Controller
public class ResourceController {
	@javax.annotation.Resource
	private Result result;
	@javax.annotation.Resource
	private ResourceService resourceService;
	@javax.annotation.Resource
	private ResourceMapper resourceDao;
	
	
	// 进入前端电脑端
	@RequestMapping("/resource")
	public String toLogin_frontPc(HttpServletRequest request, Model model) {
		User user = LoginUtil.getUserInfo(request);
		model.addAttribute("user", user);
		//获得目前所有的资源数
		model.addAttribute("resourceTotal",resourceDao.getResourceTotal());
		return "front_pc/resource";
	}
	
	//资源平台登录
	@RequestMapping("/resource/login")
	public String toResourceLogin(){
		return "front_pc/login";
	}
	
	@RequestMapping("/resource/getResourceList")
	@ResponseBody
	public Result getResourceList(PageInfoSearch pageInfo){
		//先设置总条目
		if(pageInfo.getTotalItems() == null || pageInfo.getTotalItems() == 0){
			pageInfo.setTotalItems(resourceDao.getCountByPage(pageInfo));
		}
		List<Resource> resources =  resourceService.getResourceList(pageInfo);
		result.setResult(1, resources, pageInfo.getTotalItems()+"");
		return result;
	}

	@RequestMapping("/resource/uploadFile")
	@ResponseBody
	public Result uploadFile(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		if (!file.isEmpty()) {
			try {
				long timeMillis = System.currentTimeMillis();
				String fileName = file.getOriginalFilename(); // 这里是带后缀的 ****
				// 获得上传时的文件名
				// 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
				// )
				String filePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ "resource"
						+ File.separator
						+ timeMillis
						+ fileName.substring(fileName.lastIndexOf("."));
				File saveDir = new File(filePath);
				if (!saveDir.getParentFile().exists()) {
					saveDir.getParentFile().mkdirs();
				}
				// 转存文件
				file.transferTo(saveDir);
				// 存入数据库
				com.ugfind.model.Resource resource = new com.ugfind.model.Resource();
				resource.setFileName(fileName);
				resource.setPath(filePath);
				resource.setUserId(LoginUtil.getUserInfo(request).getId());
				resourceService.insertResource(resource);
				result.setResult(1, resource.getId(), "上传成功!"); // 返回插入后的id
			} catch (Exception e) {
				e.printStackTrace();
				result.setResult(2, null, "上传失败!");
			}
		}
		return result;
	}
	
	//获得所有文档类型类型
	@RequestMapping("/resource/getTypeList")
	@ResponseBody
	public Result getTypeList(){
		List<ResourceType> resourceTypes= resourceService.getTypeList();
		result.setResult(1, resourceTypes, null);
		return result;
	}
	
	//修改(补充)文档资料
	@RequestMapping("/resource/updateResource")
	@ResponseBody
	public Result updateResource(Resource resource,HttpServletRequest request){
		resource.setUserId(LoginUtil.getUserInfo(request).getId());
		if(resourceService.updateResource(resource)>0){
			result.setResult(1, null, null);
		}else{
			result.setResult(2, null, "提交失败,您无法修改此文档");
		}
		return result;
	}
	
	/**
	 * 根据下载次数 来获得前5位下载
	 * @return
	 */
	@RequestMapping("/resource/getResourceByDown")
	@ResponseBody
	public Result getResourceOrderByDown(){
		result.setStatus(1);
		result.setData(resourceService.getResourceOrderByDown());
		return result;
	}
	
	/**
	 * 根据id 获取资源详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/resource/getResourceInfoById")
	@ResponseBody
	public Result getResourceInfoById(Integer id){
		ResourcePoJo resourcePoJo = resourceService.getResourceInfoById(id);
		if(resourcePoJo == null){
			result.setStatus(2);
		}else{
			result.setStatus(1);
			result.setData(resourcePoJo);
		}
		return result;
	}
	
	
	
	/**
	 * 下载文件
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/resource/downResource")
	public  ResponseEntity<byte[]> downResource(Integer id) throws IOException{
			//先取到文件的数据库信息
			ResourceFilePoJo file = resourceService.getDownFile(id);
		 	HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        String fileName = new String(file.getFileName().getBytes(),"ISO-8859-1");  //这句非常关键,否则中文名无法显示
	        headers.setContentDispositionFormData("attachment",fileName);//设置文件名
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file.getFile()),
	                                          headers, HttpStatus.CREATED);
	}

	
	@RequestMapping("getResourceListByKeyWord")
	@ResponseBody
	public Result getResourceListByKeyWord(){
		return result;
	}
}
