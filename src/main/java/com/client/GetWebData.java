
package com.client;

import java.net.URL;
import java.util.Scanner;
//
public class GetWebData{
    public static void main(String[] args) throws Exception{
        String path = "file:///C:/Users/jamesliao/Documents/NetBeansProjects/JavaWeb20210531/src/main/webapp/index.html";
        URL url = new URL(path);
        String html = new Scanner(url.openStream())
                .useDelimiter("\\A")
                .next();
        System.out.println(html);
    }
            
     
}

/*
Here is what happens, with abuse of indentations

     new Scanner(                           // A new scanner is created
             new URL("http://example.com")  // the scanner takes a Stream 
                                            // which is obtained from a URL
          .openStream(),                    // - openStream returns the stream
       "UTF-8")                             // Now the scanner can parse the        
                                            // stream character by character
                                            // with UTF-8 encoding

     .useDelimiter("\\A")                   // Now the scanner set as 
                                            // delimiter the [Regexp for \A][1]
                                            // \A stands for :start of a string!

   .next();                                 // Here it returns the first(next) 
                                            // token that is before another
                                            // start of string. 
                                            // Which, I'm not sure 
                                            // what it will be
From the Java documentation

A simple text scanner which can parse primitive types and strings using regular expressions. A Scanner breaks its input into tokens using a delimiter pattern, which by default matches whitespace. The resulting tokens may then be converted into values of different types using the various next methods.

So you just replaced \A as delimiter (instead of whitespace). BUT \A has a specific meaning when evaluating as regular expression!

If your stream contains only the following text

\Ahello world!\A Goodbye!\A

Your code will return the entire line \Ahello world!\A Goodbye!\A

If you wanted to strip on the sequence of a backslash followed by a upper case A, then you should use \\\\A.

Thanks to @Faux Pas to point out that!

*/