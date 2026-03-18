package zen;

import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

public class TestNR_stanford {
	public static void main(String[] args) {
		// 加载中文命名实体识别模型
	    String serializedClassifier = "edu/stanford/nlp/models/ner/chinese.misc.distsim.crf.ser.gz";
	    AbstractSequenceClassifier<CoreLabel> classifier;
	    try {
	        classifier = CRFClassifier.getClassifier(serializedClassifier);
	        String text = "在本次会议中，毛泽东和孙七就项目进展进行了深入交流。";
	        // 进行命名实体识别
	        System.out.println("------------------------------");
	        List<List<CoreLabel>> out = classifier.classify(text);
	        for (List<CoreLabel> sentence : out) {
	            for (CoreLabel word : sentence) {
	            	System.out.println(word.word());
	                if ("PERSON".equals(word.ner())) {
	                    System.out.println("提取到的人名: " + word.word());
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
