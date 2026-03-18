package zen;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用Hutool修改JSON文件中的endValidDateTime字段（减去35天）
 */
public class TestJsonData {

    // 时间格式：YYYYMMDDHHMMSS
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public static void main(String[] args) {
        // 1. 配置文件路径（替换为你的JSON文件路径）
        String inputFilePath = "D:\\jsondata\\朱建峰40天6个月26.json";  // 原始JSON文件
        String outputFilePath = "D:\\jsondata\\out\\朱建峰40天6个月26.json"; // 处理后的JSON文件

        // 2. 读取JSON文件内容
        String jsonStr = FileUtil.readUtf8String(new File(inputFilePath));
        JSONArray jsonArray = JSONUtil.parseArray(jsonStr);

        // 3. 遍历并修改每个对象的endValidDateTime字段
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            // 获取原始结束时间字符串
            String originalEndTime = jsonObj.getStr("endValidDateTime");
            if (originalEndTime != null && !originalEndTime.isEmpty()) {
                try {
                    // 解析时间字符串为Date对象
                    Date endDate = sdf.parse(originalEndTime);
                    // 减去35天（Hutool的DateUtil工具类）
                    Date newEndDate = DateUtil.offsetDay(endDate, -40);
                    // 重新格式化为原字符串格式
                    String newEndTimeStr = sdf.format(newEndDate);
                    // 更新字段值
                    jsonObj.set("endValidDateTime", newEndTimeStr);
                } catch (ParseException e) {
                    // 异常处理：打印错误信息，跳过该条数据
                    System.err.println("解析时间失败，跳过数据：" + jsonObj.getStr("customerId") + "，错误：" + e.getMessage());
                }
            }
        }

        // 4. 将修改后的JSON写入新文件（格式化输出，便于阅读）
        String newJsonStr = JSONUtil.toJsonStr(jsonArray);
        FileUtil.writeUtf8String(newJsonStr, new File(outputFilePath));

        System.out.println("JSON文件处理完成！处理后文件路径：" + outputFilePath);
    }
}