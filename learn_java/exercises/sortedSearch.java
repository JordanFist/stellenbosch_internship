/*Implement function countNumbers that accepts a sorted array of integers and counts the number of array elements that are less than the parameter lessThan.

For example, SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4) should return 2 because there are two array elements less than 4.
*/

public class sortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {
        int a = 0;
        int b = sortedArray.length;
        int mid = (a + b) / 2;
        while (a < b) {
            if (sortedArray[mid] == lessThan)
                return mid;
            if (sortedArray[mid] > lessThan)
                b = mid;
            else
                a = mid + 1;
            mid = (a + b) / 2;
        }
        return mid;
    }
    
    public static void main(String[] args) {
        System.out.println(sortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 3));
System.out.println(sortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
    }
}
