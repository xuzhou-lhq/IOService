package com.xz.Controller;

import java.util.ArrayList;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.ioserver.bean.Struct_TagInfo;
import com.sun.jna.WString;
import com.xz.service.getTagNameServic;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
public class getTagNameController {
	
	@Autowired
    private getTagNameServic getTagNameService;
	
	
	
	@RequestMapping(value="/getTags")
	public @ResponseBody ArrayList<Vector> getTagName1() {
		ArrayList<Vector> TagsName = new ArrayList<Vector>();
		TagsName=getTagNameService.getTagName();
		String jsonArray = null;
		//String jsonArray = gosn.
		System.out.println(jsonArray);
		return TagsName;
	  }
	


}
