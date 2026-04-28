package Main;

import java.util.LinkedHashMap;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class code {

	public static void main(String[] args) {
		Map<String, String[]> dic = new LinkedHashMap<>();
		Random random = new Random();
	    
	    dic.put("(?i)i feel (.*)", new String[]{
	        "Why do you feel $1?",
	        "Has $1 always been a part of you?",
	        "Tell me more about being $1.",
	        "Is it common for you to feel $1?"
	    });
	    
	    dic.put("(?i)i am (.*)", new String[]{
	    	"How long have you been $1?",
	        "Do you enjoy being $1?",
	        "What does it mean to be $1?"
	    });
	    
	    dic.put("(?i)i'm (.*)", new String[]{
		    "How long have you been $1?",
		    "Do you enjoy being $1?",
		    "What does it mean to be $1?"
		});
	    
	    dic.put("(?i).*mother|father|brother|sister|parent.*", new String[]{
	    	"Tell me more about your family.",
	    	"How do you feel about your relatives?",
	    	"Does your family influence these feelings?"
	    });

	    dic.put("(?i).*friend.*", new String[]{
	    	"Tell me more about your friends.",
	    	"What do your friends think about this?",
	    	"Do you feel you can trust them?"
	    });
	    
	    dic.put("(?i).*always.*", new String[]{
	    	"Can you think of a specific example?",
	    	"When exactly?",
	    	"Really, always?"
	    });

	    dic.put("(?i).*never.*", new String[]{
	    	"Why do you say never?",
	    	"Has there really never been a time?",
	    	"Are you sure?"
	    });
	    
	    dic.put("(?i)i think (.*)", new String[]{
	    	"Do you really think so?",
	    	"What makes you think $1?",
	    	"Are you unsure about $1?"
	    });

	    dic.put("(?i)i believe (.*)", new String[]{
	    	"Why do you believe that?",
	    	"Is it important to you that $1?",
	    	"What else do you believe?"
	    });
	    
	    dic.put("(?i)^yes$", new String[]{
	    	"You seem quite sure.",
	    	"Can you elaborate on that?",
	    	"I understand. Please go on."
	    });

	    dic.put("(?i)^no$", new String[]{
	    	"Why not?",
	    	"Are you being a bit negative?",
	    	"Does saying 'no' make you feel in control?"
	    });
	    
	    dic.put("(?i).*hello|hi*", new String[]{
		    "Hi",
		    "Hello"
		});
	    
	    dic.put("default", new String[]{
	    	    "Can you elaborate on that?",
	    	    "Why do you say that right now?",
	    	    "What does that suggest to you?",
	    	    "I see. And how does that make you feel?",
	    	    "Does that topic bother you?",
	    	    "Why is that important to you?",
	    	    "Tell me more about it.",
	    	    "Interesting. Please, go on.",
	    	    "What else comes to mind when you say that?",
	    	    "Does thinking about that lead to anything else?"
	    	});
		
		try (Scanner sc = new Scanner(System.in)) {
			String input = "", response;
			
			while(!input.equalsIgnoreCase("goodbye")) {
				System.out.print("You: ");
				input = sc.nextLine();
				response = dialog_logic(dic, input, random);
				System.out.println("ELIZA: " + response);
			}
		}
		
	}
	
	private static String dialog_logic(Map<String, String[]> dic, String user_entry, Random random) {
		for (Map.Entry<String, String[]> entry : dic.entrySet()) {
			Pattern p = Pattern.compile(entry.getKey());
			Matcher m = p.matcher(user_entry);
			
			if (m.find()) {
				String[] options = entry.getValue();
				int random_index = random.nextInt(options.length);
				
				String chosen_response = options[random_index];
				if (m.groupCount() >= 1) {
	                String capturedPart = m.group(1);
	                String transformedPart = transform(capturedPart);
	                return chosen_response.replace("$1", transformedPart);
	            }
				return chosen_response;
			}
		}
		String[] socraticOptions = dic.get("default");
		int random_index = random.nextInt(socraticOptions.length);
	    return socraticOptions[random_index];
	}
	
	private static String transform(String text) {
	    Map<String, String> swaps = new HashMap<>();
	    swaps.put("i", "you");
	    swaps.put("me", "you");
	    swaps.put("my", "your");
	    swaps.put("am", "are");
	    swaps.put("you", "I");
	    swaps.put("your", "my");
	    swaps.put("mine", "yours");

	    String[] words = text.split("\\s+");
	    StringBuilder result = new StringBuilder();

	    for (String word : words) {
	        String cleanWord = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
	        String replacement = swaps.get(cleanWord);

	        if (replacement != null) {
	            result.append(replacement).append(" ");
	        } else {
	            result.append(word).append(" ");
	        }
	    }

	    return result.toString().trim();
	}

}
