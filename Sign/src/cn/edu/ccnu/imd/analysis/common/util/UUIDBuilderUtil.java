package cn.edu.ccnu.imd.analysis.common.util;

import java.util.UUID;

public class UUIDBuilderUtil {
	public String getID(){
		System.out.println("hello");
		return  UUID.randomUUID().toString();
	}
}
