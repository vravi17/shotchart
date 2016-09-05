package shotchart;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;


import org.json.*;
public class Data {
	static ArrayList<Integer> arlx = new ArrayList<Integer>();
    static ArrayList<Integer> arly = new ArrayList<Integer>();
    static ArrayList<Integer> madeFlag = new ArrayList<Integer>();
    static ArrayList<Integer> madeX = new ArrayList<Integer>();
    static ArrayList<Integer> madeY = new ArrayList<Integer>();
    static ArrayList<Integer> missedX = new ArrayList<Integer>();
    static ArrayList<Integer> missedY = new ArrayList<Integer>();
	//Initialize all arraylists
    String str = new String();
    public void getData(String num) throws Exception {
    	String s = "http://stats.nba.com/stats/shotchartdetail?CFID=33&CFPARAMS=2015-16&ContextFilter=&ContextMeasure=FGA&DateFrom=&DateTo=&GameID=&GameSegment=&LastNGames=0&LeagueID=00&Location=&MeasureType=Base&Month=0&OpponentTeamID=0&Outcome=&PaceAdjust=N&PerMode=PerGame&Period=0&PlayerID=";
		s += URLEncoder.encode(num, "UTF-8");
		s+= "&PlusMinus=N&Position=&Rank=N&RookieYear=&Season=2015-16&SeasonSegment=&SeasonType=Regular+Season&TeamID=0&VsConference=&VsDivision=&mode=Advanced&showDetails=0&showShots=1&showZones=0";
		URL url = new URL(s);
	 
	    // read from the URL
	    Scanner scan = new Scanner(url.openStream());
	    
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
    }
    
    public JSONArray convert() {
    	// build a JSON object
    	JSONObject obj = new JSONObject(str);
    	JSONObject res = obj.getJSONArray("resultSets").getJSONObject(0);
	    System.out.println(res.getString("name"));
	    JSONArray header = res.getJSONArray("headers");
	    String headerString = header.toString();
	    //get all values of shot attempts
	    JSONArray shots = res.getJSONArray("rowSet");
	    return shots;
    }
    
    public void addToArrayList() {
    	JSONArray shots = convert();
    	for (int i = 0; i <shots.length(); i++) {
	    	//System.out.println(shots.length());
	    	for(int j = 0; j<shots.getJSONArray(i).length();j++) {
		    	if(j==17) {
		    		arlx.add((Integer) shots.getJSONArray(i).get(j));
		    	}
		    	  
		    	if(j==18) {
		    		arly.add((Integer) shots.getJSONArray(i).get(j));
		    	}
		    	if(j==20) {
		    		madeFlag.add((Integer) shots.getJSONArray(i).get(j));
		    	}
	    	}
	    }
    	seperateArray();
    }
    	
    public void printStuff() {
//	    System.out.println("Arraylistx contains: " + arlx.toString());
//	    System.out.println("Arraylistx size: " + arlx.size());
//	    System.out.println("Arraylisty contains: " + arly.toString());
//	    System.out.println("Arraylisty size: " + arly.size());
//	    System.out.println("Arraylisty contains: " + madeFlag.toString());
//	    System.out.println("Arraylisty size: " + madeFlag.size());
//	    System.out.println("madex contains: " + madeX.toString());
//	    System.out.println("madex size: " + madeX.size());
//	    System.out.println("madex contains: " + madeY.toString());
//	    System.out.println("madex size: " + madeY.size());
//	    System.out.println("madex contains: " + missedX.toString());
//	    System.out.println("madex size: " + missedX.size());
//	    System.out.println("madex contains: " + missedY.toString());
//	    System.out.println("madex size: " + missedY.size());
    }
	
	public ArrayList<Integer> getxList() {
		return arlx;
	}
	
	public ArrayList<Integer> getyList() {
		return arly;
	}
	
	public ArrayList<Integer> getMadeFlag()
	{
		return madeFlag;
	}
	
	public ArrayList<Integer> getMadeX()
	{
		return madeX;
	}
	
	public ArrayList<Integer> getMadeY()
	{
		return madeY;
	}
	
	public ArrayList<Integer> getMissedX()
	{
		return missedX;
	}
	
	public ArrayList<Integer> getMissedY()
	{
		return missedY;
	}
	
	public void seperateArray() {
		for(int i = 0; i<arlx.size(); i++) {
			if(madeFlag.get(i) == 1) {
				madeX.add(arlx.get(i));
				madeY.add(arly.get(i));
			} else if(madeFlag.get(i)==0) {
				missedX.add(arlx.get(i));
				missedY.add(arly.get(i));
			}
		}
	}
}
