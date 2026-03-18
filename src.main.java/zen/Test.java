package zen;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RegExUtils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

public class Test {
	public static void main(String[] args) {
		//
//		String string = RegExUtils.removeAll("何燕康A2","[a-zA-Z0-9]");
//		System.out.println(string.length());
		
//		String msg = "黑话3发的方法**你说的大的发放^^";
//		Integer startIdx = msg.lastIndexOf("**");
//		Integer endIdx = msg.lastIndexOf("^^");
//		System.out.println(msg.substring(startIdx + 2, endIdx));
		
		
//		String text = "u003cthink\\u003e\\n嗯，用户给了一个挺长的文本，里面有很多人名和一些称呼。他的要求是从这段文本中提取出人名，并以JSON格式输出。\\n\\n首先，我需要仔细阅读整个文本，找出所有的人名。看起来里面有一些编号，比如A1, A2等等，后面跟着不同的职位或头衔。比如“兰慧星A1-助教”这样的结构，所以人名应该是在每个条前面。\\n\\n然后，我要注意有时候人名后面可能会有多个字符或者符号，比如“张静静【公众号】”这里的张静静应该是其中一人名。还有像“神光投顾-任宝乐”，这里任宝乐是人名。\\n\\n接下来，我需要把这些名字提取出来，并确保每个都是独立的条目。可能有些重复的名字需要保留多次出现的情况。例如，“李博文A1-助教”和“李博文A2-助教”都提取出来，作为不同的条目。\\n\\n还要注意的是，文本中有提到“此号不用”，这应该不是人名，而是提醒的内容，所以不需要提取。还有各种联系方式，像“请➕13003209497”这些应该是不包含在人名中的。\\n\\n最后，整理完所有的人名之后，按照用户的要求将它们组织成一个JSON格式的数据结构，确保每个名字都以键值对的形式出现，并且排序一致。这样用户就可以清晰地看到提取到的所有人名了。\\n\\u003c/think\\u003e\\n\\n```json\\n[\\n    \\\"兰慧星A1-助教\\\",\\n    \\\"兰慧星A2-助教\\\",\\n    \\\"李博文A1-助教\\\",\\n    \\\"李博文A2-助教\\\",\\n    \\\"高级顾问-刘盼盼\\\",\\n    \\\"神光投顾-任宝乐\\\",\\n    \\\"杨远琴A2\\\",\\n    \\\"金牌助理-骆守城A6\\\",\\n    \\\"张静静【公众号】\\\",\\n    \\\"张静静\\\",\\n    \\\"此号不再使用！请➕13003209497\\\",\\n    \\\"神光高级助教-何正朝A3\\\",\\n    \\\"神光吴煜来\\\",\\n    \\\"神光宋建立助教A22\\\",\\n    \\\"神光宋建立助教A11\\\",\\n    \\\"孙楠楠A1-神光高级助教\\\",\\n    \\\"阮春辉A1【把我置顶】\\\",\\n    \\\"神光王世昌CYGA1\\\",\\n    \\\"神光刘德润YY\\\",\\n    \\\"神光字青云TJHA2\\\",\\n    \\\"神光字青云TJH\\\",\\n    \\\"神光黄助教A1\\\",\\n    \\\"神光黄埔A2\\\",\\n    \\\"神光王助教A1\\\",\\n    \\\"神光关助教A1\\\",\\n    \\\"神光李涛A2\\\",\\n    \\\"神光助教-樊\\\",\\n    \\\"神光李涛A1\\\",\\n    \\\"神光张喜雷A1\\\",\\n    \\\"颜薇薇-神光助教A22\\\",\\n    \\\"王创维助教A11\\\",\\n    \\\"李博文A5-助教\\\",\\n    \\\"李博文A6-助教\\\",\\n    \\\"华浩浩A1-助教\\\",\\n    \\\"华浩浩A2-助教\\\",\\n    \\\"金牌助理-骆守城A2\\\",\\n    \\\"李博文A7-助教\\\",\\n    \\\"李博文A8-助教\\\",\\n    \\\"颜薇薇-神光助教A33\\\",\\n    \\\"金牌高级助教–杨迅\\\",\\n    \\\"李博文A9-助教\\\",\\n    \\\"李博文A10-助教\\\",\\n    \\\"李博文A10-助教\\\",\\n    \\\"李博文A11-助教\\\",\\n    \\\"金牌助理-骆守城B2\\\",\\n    \\\"神光吴洪A2\\\",\\n    \\\"神光高级助教—黄国树A1\\\",\\n    \\\"神光高级助教-伍天泽A1\\\",\\n    \\\"神光高级助教—王雅丽A1\\\",\\n    \\\"神光高级助教-周山云A1\\\",\\n    \\\"神光助教刘泽宇A1\\\"\\n]\\n```";
//
//		text = text.replace("\\n", "");
//		text = text.replace(" ", "");
////		text = text.replace("\n", "").trim();
//		String regex = "\\p{C}";
////		text = text.replaceAll(regex, "");
////        result = result.trim();
////        System.out.println(result);
//		
////		text = text.replace("\n",text);
////		System.out.println(text);
////		text = text.replaceAll("\n",text);
////		System.out.println(text);
//		
//        // 正则表达式匹配 JSON 对象或数组
//        regex = "\\{.*?\\}|\\[.*?\\]";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(text);
//        // 提取并打印 JSON 数据
//        while (matcher.find()) {
//            System.out.println("Extracted JSON: " + matcher.group());
//            break;
//        }
//        
//        
//        
//        String ssString = "你说济南啊";
//        System.out.println(ssString.contains("济1"));
//        
//        
//        StringBuilder st = new StringBuilder("'^13916399540~');");
//        int sIdx = st.indexOf("^") + 1;
//        int eIdx = st.indexOf("~");
//        System.out.println(st.substring(sIdx, eIdx));
//        
//        System.out.println("-----------------------xxxxxxxxxxxxxxxx-------------------");
//
//        Integer sa = null;
//        if(sa != null && sa == 1) {
//        	System.out.println("hahahhaaa");
//        }else {
//        	System.out.println("hihihihih");
//        }
//        
//        
//        System.out.println(System.currentTimeMillis());
//		DateTime tDateTime = DateUtil.offsetDay(new Date(), - Integer.valueOf("1"));
//		System.out.println(DateUtil.format(tDateTime, DatePattern.PURE_DATETIME_PATTERN));
		
		
//        String format = "yyyy/MM/dd HH:mm:ss";  // 对应的格式串
//        
//		String d1 = "2026/01/20 00:00:00";
//		String d2 = "2026/01/21 00:59:59";
//		Date date1 = DateUtil.parse(d1, format);
//		Date date2 = DateUtil.parse(d2, format);
//		System.out.println(DateUtil.betweenDay(date1, date2, true));
//		String idNum = "342623199808250618";
//		if(idNum.matches("^[1-9]\\d{16}[0-9Xx]$")){
//			System.out.println("xxxx");
//		}
//		System.out.println("gg");
		
		System.out.println("52bda3b2bb21e06730eaf4214345df6511e37da1dcca750e1169ed614a7cad71".length());
	}
	
	
}
