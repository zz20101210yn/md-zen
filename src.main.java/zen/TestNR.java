package zen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

public class TestNR {
	public static List<String> extractNames(String text) {
        List<String> names = new ArrayList<>();
        List<Term> termList = HanLP.segment(text);
        for (Term term : termList) {
        	String nature = term.nature.toString();
            if ("nr".equals(nature) && nature.length() >= 2) {
                names.add(term.word);
            }
        }
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s2.length(),s1.length());
            }
        });
        return names;
    }

    public static void main(String[] args) {
        String text = "【管理员】牛延飞alisa_name胡铭基";
        List<String> names = extractNames(text);
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println("-------done------");
    }
}
