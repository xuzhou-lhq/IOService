package com.xz.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ioserver.bean.Struct_TagInfo;
import com.sun.jna.WString;
import com.xz.dao.IOserverClient;

@Service("getTagNameServic")
public class getTagNameServic {
	
	@Autowired
	private IOserverClient Client;
	
	public getTagNameServic() {
		
		Client.funcConnect("127.0.0.1", "12380");
	}
	
	@Transactional
	public ArrayList<Vector> getTagName() {
		
		
		Vector<WString> vecSubscribeTagsName = new Vector<WString>();
		vecSubscribeTagsName=Client.funcSubscribeAllTags();
		Client.setvecAsyncReadTagsName(vecSubscribeTagsName);
		Struct_TagInfo[] structTagValue = Client.getAsyncReadValue();
		
		ArrayList<Vector> IOdata=new ArrayList<Vector>();
		
		for (int i = 0; i < vecSubscribeTagsName.size(); i++) {
			WString tagName = vecSubscribeTagsName.get(i);
			Struct_TagInfo value = Client.funcGetTagValue(tagName);
				if (value != null) {
					Vector row = new Vector();
					row.add(Client.funcGetTagNameById(value.TagID));

					switch (value.TagValue.ValueType) {
					case 1:
						row.add(value.TagValue.TagValue.bitVal);
						break;
					case 3:
						row.add(value.TagValue.TagValue.i1Val);
						break;
					case 4:
						row.add(value.TagValue.TagValue.i2Val);
						break;
					case 6:
						row.add(value.TagValue.TagValue.i8Val);
						break;
					case 9:
						row.add(value.TagValue.TagValue.r4Val);
						break;
					case 10:
						row.add(value.TagValue.TagValue.r8Val);
						break;
					case 11:
						row.add(value.TagValue.TagValue.wstrVal);
						break;
					default:
						row.add("涓嶆敮鎸佺被鍨�");
						break;
					}

					Date TimeStamp = new Date(value.TimeStamp.Seconds.longValue() * 1000);
					row.add(TimeStamp.toLocaleString());
					row.add(value.QualityStamp);
					row.add(value.TagID);
					IOdata.add(row);
					
				}
			}
		return IOdata;
	}
}
