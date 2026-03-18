package zen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.LineHandler;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;

public class TestSM4_CSV_ID_NO_CUMPU {
	static Integer total = 0;
	static int lineNum = 0;
	public static void main(String[] args) {
        
        // 128位密钥 (16字节) - 实际项目中应从安全渠道获取
        //String key = "|5f3Q4-#\"Kf&h[E%"; // 16个字符 = 16字节 = 128位
        String key = "|5f3Q4-#\"Kf&h[E%";
        
        // 初始化SM4实例 (默认使用ECB模式/PKCS5Padding填充)
        SM4 sm4 = SmUtil.sm4(key.getBytes());

        String filePath = "D:\\idno\\sfjs.csv";
        //-- 点石成金 犇牛战法 量价趋势擒龙 成林俱乐部 私颜社 主力掘金 万维龙头战法
        
        File file = FileUtil.file(filePath);
        
        try {
        	final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\idno\\chg\\sfjs.csv"), StandardCharsets.UTF_8));
			FileUtil.readLines(file, Charset.forName("utf-8"), new LineHandler() {
			    
			    @Override
			    public void handle(String line) {
			        lineNum++;
			        if (line.trim().isEmpty()) {
			            System.out.println("忽略空行");
			            return;
			        }
			        String lineString =  line.replace("\"", "");
			        if(lineNum != 1) {
			        	try {
				        	String descrts = sm4.decryptStr(lineString);
				        	total = total + calculateAge(descrts);
//				        	writer.write(line + "," + sm4.decryptStr(parts[0]));
							writer.newLine();
							writer.flush();
						} catch (IOException e) {
						}
			        }
			        
			        System.out.println("row: " + lineNum);
			    }
			});
			System.out.println("total:" + total);
			System.out.println("line:" + (lineNum - 1));
			System.out.println("avg" + total/(lineNum - 1));
		} catch (IORuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("文件处理完成");
    }

	
	// 15位身份证出生日期格式（yyMMdd）
    private static final DateTimeFormatter FORMAT_15 = DateTimeFormatter.ofPattern("yyMMdd");
    // 18位身份证出生日期格式（yyyyMMdd）
    private static final DateTimeFormatter FORMAT_18 = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 根据身份证号计算当前年龄（周岁，精准到天）
     * @param idCard 身份证号（15位/18位）
     * @return 年龄（异常返回-1）
     */
    public static int calculateAge(String idCard) {
        // 1. 校验身份证号非空
        if (idCard == null || idCard.trim().isEmpty()) {
            System.out.println("身份证号不能为空");
            return -1;
        }
        idCard = idCard.trim();

        // 2. 提取出生日期
        LocalDate birthDate = getBirthDateFromIdCard(idCard);
        if (birthDate == null) {
            return -1;
        }

        // 3. 计算当前年龄（Period自动计算周岁，精准到天）
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDate, now);
        return period.getYears();
    }

    /**
     * 从身份证号提取出生日期
     * @param idCard 身份证号
     * @return 出生日期（异常返回null）
     */
    private static LocalDate getBirthDateFromIdCard(String idCard) {
        try {
            String birthStr;
            if (idCard.length() == 18) {
                // 18位身份证：第7-14位是yyyyMMdd
                birthStr = idCard.substring(6, 14);
                return LocalDate.parse(birthStr, FORMAT_18);
            } else if (idCard.length() == 15) {
                // 15位身份证：第7-12位是yyMMdd，补全为19yyMMdd
                birthStr = "19" + idCard.substring(6, 12);
                return LocalDate.parse(birthStr, FORMAT_18);
            } else {
                System.out.println("身份证号长度必须是15位或18位：" + idCard);
                return null;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("身份证号格式错误，无法提取出生日期：" + idCard);
            return null;
        } catch (DateTimeParseException e) {
            System.out.println("出生日期解析失败，身份证号格式异常：" + idCard);
            return null;
        }
    }
}
