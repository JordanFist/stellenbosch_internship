/*A palindrome is a word that reads the same backward or forward.

Write a function that checks if a given word is a palindrome. Character case should be ignored.

For example, isPalindrome("Deleveled") should return true as character case should be ignored, resulting in "deleveled", which is a palindrome since it reads the same backward and forward.
*/

public class palindrome {
	public static boolean isPalindrome(String word) {
		String wordLower = word.toLowerCase();
		String wordArray[] = wordLower.split("");
		for (int i = 0; i < wordArray.length / 2; ++i) {
			if (!wordArray[i].equals(wordArray[wordArray.length - i - 1])) 
				return false;
		}
		return true;
   	}
    	public static void main(String[] args) {
        	System.out.println(palindrome.isPalindrome("Deleveled"));
		System.out.println(palindrome.isPalindrome("eve"));
		System.out.println(palindrome.isPalindrome("AnnA"));
    	}
}
