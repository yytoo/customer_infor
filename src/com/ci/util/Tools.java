package com.ci.util;

import java.util.ArrayList;
import java.util.List;

import com.ci.dao.ToolsDao;

public class Tools {
	public  String[] getFamilyName(){
		String sFN="小姐、先生、女士、王、李、张、刘、陈、杨、黄、赵、吴、周、徐、孙、马、朱、胡、郭、何、高、林、郑、谢、罗、梁、宋、唐、许、韩、冯、邓、曹、彭、曾、肖、田、董、袁、潘、于、蒋、蔡、余、杜、叶、程、苏、魏、吕、丁、任、沈、 姚、卢、姜、崔、钟、谭、陆、汪、范、金、石、廖、贾、夏、韦、付、方、白、邹、孟、熊、秦、邱、江、尹、薛、闫、段、雷、侯、龙、史、陶、黎、贺、顾、 毛、郝、龚、邵、万、钱、严、覃、武、戴、莫、孔、向、汤";
		String fn[] = sFN.split("、");
		return fn;		
	}
	
	public List<String> getFactoryShortName(){
		ToolsDao toolsDao = new ToolsDao();
		List<String> factoryShortNames = new ArrayList<String>();
		factoryShortNames=toolsDao.getFactoryShortName();
		String[] throwAway={"电话","热线","微信"};   //需要抛弃的字符串关键字
		for(int i=0; i<throwAway.length; i++){
			factoryShortNames.add(throwAway[i]);
		}
		return factoryShortNames;	
	}
	
	public List<String> getCityName(){
		ToolsDao toolsDao = new ToolsDao();
		List<String> cityList = new ArrayList<String>();
		List<String> cityListNew = new ArrayList<String>();
		cityList=toolsDao.getCityName();
		for(int i=0; i<cityList.size(); i++){
			String sCityName=cityList.get(i);
			sCityName=sCityName.substring(0,sCityName.length()-1);
			cityListNew.add(sCityName);
		}
		String[] sKeyWord={"公司","集团"};      //添加额外的识别公司的关键字
		for(int j=0; j<sKeyWord.length; j++){
			cityListNew.add(sKeyWord[j]);
		}	
		String fn[]=getFamilyName();
		for(int k=0; k<fn.length; k++){
			cityListNew.add(fn[k]);
		}
		return cityListNew;
	}
	
}
