package com.ci.gain;

import java.util.List;

import com.ci.bean.CustomerAddr;
import com.ci.bean.CustomerMark;
import com.ci.dao.GetQqRecordDAO;

public class GetQqRecord {
	public void getMark(int aMin, int aMax){
		GetQqRecordDAO getQqRecordDAO = new GetQqRecordDAO();
		List<CustomerMark> cmList = getQqRecordDAO.getMarkList().get(0);
		List<CustomerAddr> caList = getQqRecordDAO.getMarkList().get(1);
		getQqRecordDAO.insertMark(cmList);
		getQqRecordDAO.insertAddr(caList);
	}
}

