package zen;

import java.util.ArrayList;
import java.util.List;

public class TestComb {
	/**
     * 生成长度为k的所有组合
     * @param chars 字符数组
     * @param k 组合长度
     * @return 指定长度的所有组合
     */
    public static List<String> generateCombinations(char[] chars, int k) {
        List<String> result = new ArrayList<>();
        if (k <= 0 || k > chars.length) {
            return result;
        }
        backtrack(chars, k, new StringBuilder(), 0, result);
        return result;
    }
    
    private static void backtrack(char[] chars, int k, StringBuilder current, 
                                 int start, List<String> result) {
        if (current.length() == k) {
            result.add(current.toString());
            return;
        }
        
        for (int i = start; i < chars.length; i++) {
            current.append(chars[i]);
            backtrack(chars, k, current, i + 1, result);
            current.deleteCharAt(current.length() - 1);
        }
    }
    
    /**
     * 生成所有长度的组合
     */
    public static List<String> generateAllCombinations(char[] chars) {
        List<String> result = new ArrayList<>();
        for (int k = 1; k <= chars.length; k++) {
            result.addAll(generateCombinations(chars, k));
        }
        return result;
    }
    
    public static void main(String[] args) {
//        char[] chars = {'4', '2', '3'};
//        
//        // 生成长度为2的组合
//        System.out.println("长度为2的组合:");
//        List<String> length2 = generateCombinations(chars, 2);
//        for (String comb : length2) {
//            System.out.println(comb);
//        }
//        
//        // 生成所有组合
//        System.out.println("\n所有组合:");
//        List<String> all = generateAllCombinations(chars);
//        for (String comb : all) {
//            System.out.println(comb);
//        }
    	
    	String textString = "423^黑|诉|险^etC7x6BwAAg7bCHhBrBY5vnY9KtOwwWw";
    	String []arr= textString.split("\\^");
    	System.out.println(arr[1]);
    }
}
