# InScan
This repo contains the library implementation (called "InScan") for Java. It needs to be scan text (divided into elements by given characters) from a file or InputStream (console) and extract numbers or text from it.

# How to use
1. Copy the file InScan.java to your project or add inScan.jar to your build path.
2. Use it:
```
    // Get the text from the file and the characters '-' and ' ' which will divide the text into elements
    InScan inScan = new InScan("input.txt", '-', ' ');
    
    // Get the text from console(System.in) and the characters '-' and ' ' which will divide the text into elements
    // InScan inScan = new InScan(System.in, '-', ' ');
    
    // Add another character that will divide the text
    inScan.addSeparator('_'); 
    
    // Get the first string element from the stream
    String s1 = inScan.peek();
    
    // Get and remove the first string element from the stream
    String s2 = inScan.next();
    
    // true - if the next element in the stream is numeric
    boolean isNextNumber = inScan.isNextNumeric();
    
    // Get and remove the next string element from the stream, convert it to a Double
    Double valDbl = inScan.nextDouble();
    
    // Get and remove the next string element from the stream, convert it to an Integer
    Integer valInt = inScan.nextInteger();
    
    // Get and remove the next string element from the stream, convert it to a Long
    Long valLong = inScan.nextLong();
    
    // Iterate over all text in stream until the 'q' character is found
    while (!inScan.isEmpty()) {
    	if (inScan.next().equalsIgnoreCase("q"))
		break;
	}
    
    // Closes the stream
    inScan.close();
```

## License
GNU General Public License v3.0.
