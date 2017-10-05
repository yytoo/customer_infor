package com.ci.gain;

public class Main {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		/*GetFilesList getFilesList = new GetFilesList();   //文件爬取电话号码和人名，公司名
		getFilesList.getFilesName();*/
		GetQqRecord getQqRecord = new GetQqRecord();    //读取qq_record并统计
		getQqRecord.getMark(202300,227697);
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); 
	}

}
