package zen;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

public class TestNR_Ansj {
	public static List<String> extractNames(String text) {
        List<String> names = new ArrayList<>();
        Result result = NlpAnalysis.parse(text);
        // 获取分词结果
        List<Term> terms = result.getTerms();
        // 遍历分词结果，提取人名
        for (Term term : terms) {
            // 判断是否为中文人名（nr 表示人名）
            if ("nr".equals(term.getNatureStr())) {
                System.out.println("提取到人名: " + term.getName());
            }
        }
        return names;
    }

    public static void main(String[] args) {
        String text = "哈哈哈哈牛飞翔A1\r\n";
        List<String> names = extractNames(text);
        for (String name : names) {
            System.out.println(name);
        }
    }
}
