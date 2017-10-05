package com.ci.gain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ci.bean.CustomerInfor;
import com.ci.dao.CustomerInforDAO;
import com.ci.util.Tools;

public class GetFilesList {
	private CustomerInfor lastCI=null;        //保存当前行属于哪个发言qq下
	public void getFilesName(){
		String sPath = "C:/Users/Administrator/Desktop/qqtest";
		File f = new File(sPath);
		if(!f.exists()){
			System.out.println(sPath+" not exists");
		}else{
			File filesList[] = f.listFiles();
			readTxt(filesList);
		}
	}
	
	/*
	 * 开始读取单个txt文件内容
	 */
	public void readTxt(File aFiles[]){
		InputStreamReader reader = null;	
		BufferedReader br = null;
		String sLine = "";
		CustomerInfor ci = new CustomerInfor();
		CustomerInforDAO cid = new CustomerInforDAO();
		StringBuffer sbInfor = new StringBuffer();
		for(int i=0; i<aFiles.length; i++){		
			try {                                  
				reader = new InputStreamReader(new FileInputStream(aFiles[i]),"utf-8");					
				br = new BufferedReader(reader);
			
				while((sLine=br.readLine())!=null){

					ci=readTitle(sLine,aFiles[i].getName());
					if(ci.getQqNum()!=null&&!"".equals(ci.getQqNum())){    //获取到了qq号，则保存qq好和处理上一轮的聊天信息
						cid.saveCI(ci);
						if(sbInfor!=null && !"".equals(sbInfor)){   //前一轮循环已得到一段qq聊天内容，则开始处理			
							overRideFind(sbInfor.toString());
							sbInfor.delete(0, sbInfor.length());
						}		
						lastCI=ci;
					}else{   //不存在HH:mm:ss则去取电话号码或者人名/公司名
						if(!"".equals(sLine)){
							sbInfor.append(sLine).append(" ");
						}
						
					}
				}
				if(sbInfor!=null && !"".equals(sbInfor)){   //最后一段记录跑完后，因后面没有qQnum，所以无法进入if循环，需要单独处理	
					overRideFind(sbInfor.toString());
					sbInfor.delete(0, sbInfor.length());
				}	
				
			}									
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {  
		        if (reader != null) {  
		            try {  
		                reader.close();  
		            } catch (IOException e) {  
		            	e.printStackTrace();  
		            }  
		        }  
		        if(br!=null) {  
		            try {  
		            	br.close();  
		            } catch (IOException e) {  
		            	e.printStackTrace();  
		            }  
		        } 
			}
		}			
	}
	
	public CustomerInfor readTitle(String aLine,String aFileName){
		//String sPattern = "(.*?)\\((?:(\\d*)|(\\w+?\\@[A-Za-z0-9]+\\.[A-Za-z0-9]+))\\).*?\\d{1,2}:\\d{1,2}:\\d{1,2}";
		String sPattern = "(.*?)\\((?:(\\d*)|(\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+))\\).*?\\d{1,2}:\\d{1,2}:\\d{1,2}";
		CustomerInfor ci = new CustomerInfor();
		Pattern pattern = Pattern.compile(sPattern);
		Matcher matcher = pattern.matcher(aLine);
		String sQq="";
		//GetQqRecord getQqRecord = new GetQqRecord();
		if(matcher.matches()){
			if(matcher.group(2)!=null){
				sQq=matcher.group(2);         //截取qq号
			}else if(matcher.group(3)!=null){
				sQq=matcher.group(3);         //截取qq号
			}	
			//getQqRecord.getMark(sQq);
			if(matcher.group(1) != null){			
				String sQqName=matcher.group(1);       //截取qq名			
				ci.setName(sQqName);
				ci.setQqNum(sQq);
				ci.setFileName(getFileName(aFileName));		
				}else{
					//System.out.println("have not get title");					
				}
			}else{
				//System.out.println("it is not a title");				
			}
		return ci;
	}
	
	public void overRideFind(String aLine){
		//String sPatternM = "(?:(?:.*?\\D)|^|\\s)(\\d{11})(?:$|\\D|\\s)";
		//String sPatternT = "(?:(?:.*?[[\\D]&&[^\\-]])|^)(\\d{8})(?:$|\\D|\\s)";          //号码可能顶行（^),或者前面有字符(前面的最后一个不能是数字，用于区分8位以上），最后结尾为非数字字符或者无字符($)	
		//String sPatternMT = "(?:.*?\\D|^)(\\d{3,4}-\\d{8})($|\\D|\\s)";
		String sPattern = "(?:(?:^|(?:.*?[[\\D]&&[^\\-]]))(\\d{8})(?:$|\\D|\\s))|(?:(?:.*?\\D|^)(\\d{3,4}\\-\\d{8})|(\\d{11})(?:$|\\D|\\s))";
		String sLine=aLine;  //下面替换？的时候会报错，？在正则表达式中有特殊意义
		int flag=1;
		String sPhoneGroup="";
		List<String> phoneList=new ArrayList<String>();
		List<String> compList = new ArrayList<String>();
		while(flag==1){			
			Pattern pattern = Pattern.compile(sPattern);
			Matcher matcher = pattern.matcher(sLine);
			
			if(matcher.find()){	
				/*for(int i=0; i<matcher.groupCount(); i++){
					System.out.println(i+":"+matcher.group(i));
				}*/
				if((sPhoneGroup=matcher.group(1))!= null ||(sPhoneGroup=matcher.group(2))!= null||(sPhoneGroup=matcher.group(3))!= null){
					phoneList.add(sPhoneGroup);   //将电话号码放入list
					saveCustomerPhone(createList(phoneList, "phone"));
					
					//***开始处理前后20个字符是否有公司名和人名***//
					int iMatcherStart=matcher.start();  
					int iMatcherEnd=matcher.end();
					String sBefor="";
					String sEnd="";
					if((iMatcherStart-20)>0){
						sBefor=sLine.substring(iMatcherStart-20,iMatcherStart);
					}else{
						sBefor=sLine.substring(0,iMatcherStart);
					}
					for(int i=0; i<getCompAndMem(sBefor).size(); i++){
						compList.add(getCompAndMem(sBefor).get(i));
					}
					getCompAndMem(sBefor);            //将公司名或人名放入list
					                                  //判断与之前放入该list的公司名重复
					if((iMatcherEnd+20)<sLine.length()){
						sEnd=sLine.substring(iMatcherEnd,iMatcherEnd+20);
					}else{
						sEnd=sLine.substring(iMatcherEnd);
					}
					for(int i=0; i<getCompAndMem(sEnd).size(); i++){
						compList.add(getCompAndMem(sEnd).get(i));
					}
					saveCompAndName(createList(compList, "compAndName"));
					
					sLine=sLine.substring(iMatcherEnd);         //截取下一次循环的字符串组，抛弃已经匹配到号码过的部分
				}		
			}else{
				flag=0;
			}			
		}		
	}
	
	/*获取数据库中该人的电话/公司字符串，对比传入的list,筛选不存在的字符串存入一个新的list返回*/
	public String createList(List<String> aList, String aType){    //上一次循环得到的啊Lists和本次需要加入的aList.去重包括数据库源于的和该qq之前循环后留下的list
		StringBuffer string=new StringBuffer();
		CustomerInforDAO customerInforDAO = new CustomerInforDAO();
		String sSqlValue = customerInforDAO.isRepeat(lastCI, aType);
		List<String> newList = new ArrayList<String>();	
		for(int i=0; i<aList.size(); i++){
			if(!(aList.subList(i+1, aList.size())).contains(aList.get(i))){
				if(sSqlValue!=null && !"".equals(sSqlValue)){
					String[] sqlVaue=sSqlValue.split(",");
					int flag=1;
					for(int j=0; j<sqlVaue.length; j++){
						if(sqlVaue[j].equals(aList.get(i))){							
							flag=0;
							break;
						}
					}
					if(flag==1){
						newList.add(aList.get(i));
					}
				}else{
					newList.add(aList.get(i));
				}						
			}
		}
		if(newList!=null && newList.size()!=0){
			for(int i=0; i<newList.size()-1; i++){
				string.append(newList.get(i)).append(",");
			}
			string.append(newList.get(newList.size()-1));
		}
		
		if(sSqlValue!=null && !"".equals(sSqlValue) && string !=null && string.length()!=0){   //这里需要把三种情况分开来
			return sSqlValue+","+string.toString();
		}else if(sSqlValue!=null && !"".equals(sSqlValue)){
			return sSqlValue;
		}else{
			return string.toString();
		}
		
	}
	
	public void saveCustomerPhone(String aPhone){
		String sLastQqNum= lastCI.getQqNum();
		String sLastFileName = lastCI.getFileName();
		CustomerInfor cp = new CustomerInfor();
		CustomerInforDAO customerInforDAO = new CustomerInforDAO();	
		cp.setPhone(aPhone);
		cp.setQqNum(sLastQqNum);
		cp.setFileName(sLastFileName);		
		customerInforDAO.insertCP(cp);
	}
	
	public List<String> getCompAndMem(String aLine){        //读取电话行中的人名
		String sLine = aLine;
		String sLeftLine="";
		List<String> companyName=new ArrayList<String>();
		int iIsW=1;
		Tools tools = new Tools();		
		List<String> cityList = tools.getCityName();    //获取城市关键字list用于识别公司名
		List<String> throwAwayList = tools.getFactoryShortName();
		int flag=1;	
		List<String> list = new ArrayList<String>();	
		if(sLine!=null && !"".equals(sLine))	
		/*****将字符串按分隔符分段并放入list中*****/
		while(flag==1){		
			Pattern pattern = Pattern.compile("(\\s|，|。|\\-|——|：)");   
			Matcher matcher = pattern.matcher(sLine);
			if(matcher.find()){
				int iIndexStart=matcher.start();
				int iIndexEnd= matcher.end();
				list.add(sLine.substring(0,iIndexStart));
				sLeftLine = sLine.substring(iIndexEnd);
				sLine=sLeftLine;
			}else{
				flag=0;
			}
		}
		list.add(sLeftLine);		//把最后一段未匹配的内容也放到list里面去
		/*****开始对list内容逐条查询是否包含公司关键字*****/
		for(int j=0; j<list.size(); j++){			
			String sList=list.get(j);
			for(int i=0; i<cityList.size(); i++){         //检测是否包含城市名
				if(sList.indexOf(cityList.get(i))!=-1){
					iIsW=1;					
					for(int n=0; n<throwAwayList.size(); n++){          //检测是否包含需要抛弃的字符串	
						String sThrowAway=throwAwayList.get(n);
						if(sList.indexOf(sThrowAway)!=-1){	//存在则直接跳出循环不进行操作
							iIsW=0;
							break;		
						}
					}	
					if(iIsW==1){
						companyName.add(sList);						
					}
					break;              //匹配到一次就跳出循环，以防字符串中有两个关键字导致识别两次
				}				
			}
		}
		return companyName;
			
	}
	
	public void saveCompAndName(String aCAN){
		String sLastQqNum= lastCI.getQqNum();
		String sLastFileName = lastCI.getFileName();
		CustomerInfor cC = new CustomerInfor();
		CustomerInforDAO customerInforDAO = new CustomerInforDAO();	
		cC.setCompAndName(aCAN);
		cC.setQqNum(sLastQqNum);
		cC.setFileName(sLastFileName);	
		customerInforDAO.saveCC(cC);
	}
			
	public String getFileName(String aFileName){
		Pattern patternF = Pattern.compile("(.*?)\\d{1,12}");
		Matcher matcherF = patternF.matcher(aFileName);
		matcherF.find();
		String sFileName=matcherF.group(1);
		return sFileName;
	}
}
