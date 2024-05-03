package all_Data;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class TestClass {
	
	static List<String> filesSenpara = new ArrayList<String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		filesSenpara = readJsonFile("Senpara");
//		String data = filesSenpara.get(1);
//		
//		System.out.println(data);
		writeJsonFile();
	}
	
	private static List<String> readJsonFile(String param) {
		List<String> fileNames = new ArrayList<String>();
		JSONParser parser = new JSONParser();
	      try {
	    	  
	    	  String directory = "D:/business_all_stock/Senpara/files.json";
	    	  if(param == "Boroghor")
	    		  directory = "D:/business_all_stock/Boroghor/files.json";
	    	  
	    	  JSONArray files = (JSONArray) parser.parse(new FileReader(directory));
//	    	  JSON Array Size
//	    	  System.out.println(files.size());
//	    	  int size = files.size();
	    	  
	    	  for (Object file : files)
	    	  {
	    	    JSONObject fileData = (JSONObject) file;

	    	    String fileName = (String) fileData.get("fileName");
//	    	    System.out.println(fileName);

	    	    String color = (String) fileData.get("color");
//	    	    System.out.println(color);
	    	    
//	    	    filesSenpara.add(fileName);
	    	    fileNames.add(fileName);

	    	    
//	    	    String job = (String) person.get("job");
//	    	    System.out.println(job);
//
//	    	    JSONArray cars = (JSONArray) person.get("cars");
//
//	    	    for (Object c : cars)
//	    	    {
//	    	      System.out.println(c+"");
//	    	    }
	    	  }
//	    	 System.out.println(filesSenpara);
	         
//	    	 https://www.tutorialspoint.com/how-can-we-read-a-json-file-in-java#
//		     Object obj = parser.parse(new FileReader("D:/business_all_stock/Senpara/files.json"));
//		     System.out.println(obj);
//		     [{"fileName":"8Lfresh","color":"yellow"},{"fileName":"5Lfresh","color":"yellow"}]
	    	 
//	         JSONObject jsonObject = (JSONObject)obj;
//	         System.out.println(jsonObject);
//	         String name = (String)jsonObject.get("Name");
//	         String course = (String)jsonObject.get("Course");
//	         JSONArray subjects = (JSONArray)jsonObject.get("Subjects");
//	         System.out.println("Name: " + name);
//	         System.out.println("Course: " + course);
//	         System.out.println("Subjects:");
//	         Iterator iterator = subjects.iterator();
//	         while (iterator.hasNext()) {
//	            System.out.println(iterator.next());
//	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return fileNames;
	}


	private static void writeJsonFile() {
		//Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Inserting key-value pairs into the json object
//	      jsonObject.put("ID", "1");
//	      jsonObject.put("First_Name", "Shikhar");
//	      jsonObject.put("Last_Name", "Dhawan");
//	      jsonObject.put("Date_Of_Birth", "1981-12-05");
//	      jsonObject.put("Place_Of_Birth", "Delhi");
//	      jsonObject.put("Country", "India");
	      
	      JSONArray testArray = new JSONArray();
	      JSONObject jsonArrayObject = new JSONObject();
	      //Inserting key-value pairs into the json object
	      jsonArrayObject.put("ID", "1");
	      jsonArrayObject.put("First_Name", "Shikhar");
	      testArray.add(jsonArrayObject);
	      jsonObject.put("summary", testArray);
	     
	      try {
	         FileWriter file = new FileWriter("D:/business_all_stock/Senpara/history/2024-03-05.json");
	         file.write(jsonObject.toString());
	         file.close();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      System.out.println("JSON file created: "+jsonObject);
	}

}
