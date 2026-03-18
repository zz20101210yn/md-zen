package zen;

import java.time.LocalDateTime;

import cn.hutool.core.date.DateUtil;

public class TestX {
	public static void main(String[] args) {
//		boolean flag = false;
////		if(DateUtil.parseLocalDateTime("2025-09-18 00:00:00").plusMonths(1).isBefore(LocalDateTime.now())) {
////			flag = true;
////		}
//		if(LocalDateTime.now().minusMonths(1).isAfter(DateUtil.parseLocalDateTime("2025-09-17 00:00:00"))) {
//			flag = true;
//		}
//		System.out.println("isBefore:" + flag);
		String aa = "[黑|诉|险]";
		String xxString = "[黑|诉|险]哈哈哈哈";
		System.out.println(xxString.replace(aa, ""));
		final Integer x = 1;
		System.out.println(x + 1);
	}
}
