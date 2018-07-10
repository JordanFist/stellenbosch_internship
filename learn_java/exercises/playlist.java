/*A playlist is considered a repeating playlist if any of the songs contain a reference to a previous song in the playlist. Otherwise, the playlist will end with the last song which points to null.

Implement a function isRepeatingPlaylist that returns true if a playlist is repeating or false if it is not.

For example, the following code prints "true" as both songs point to each other.

Song first = new Song("Hello");
Song second = new Song("Eye of the tiger");
    
first.setNextSong(second);
second.setNextSong(first);
    
System.out.println(first.isRepeatingPlaylist());
*/


public class playlist {
	public static class Song {
        	private String name;
       		private Song nextSong;
 
        	public Song(String name) {
        		this.name = name;
        	}
    
        	public void setNextSong(Song nextSong) {
        		this.nextSong = nextSong;
        	}
    
        	public boolean isRepeatingPlaylist() {
            		Song x = nextSong;
            		Song y = x.nextSong;
           		while(x != y) {
                		if (y == null)
                    			return false;
                		y = y.nextSong;
            		}
            		return true;
        	}
    	}
    
    	public static void main(String[] args) {
        	Song first = new Song("Hello");
        	Song second = new Song("Eye of the tiger");
    		Song third = new Song("Final countdown");
		Song fourth = new Song("Can't stop");

        	first.setNextSong(second);
        	second.setNextSong(third);
		third.setNextSong(fourth);    	
		fourth.setNextSong(first); // To comment to create an unrepeating playlist
	
        	System.out.println(first.isRepeatingPlaylist());
    	}
}
