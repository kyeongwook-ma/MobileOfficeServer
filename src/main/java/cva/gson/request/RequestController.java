package cva.gson.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cva.sim.ShowRoom;

@RestController
public class RequestController {

	Gson gson = new Gson();

	ShowRoom sr = new ShowRoom();
	/*
	 * @RequestMapping(value = "/request", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Response greeting(@RequestBody String json) {
	 * 
	 * System.out.println("input : " + json);
	 * 
	 * //return new Greeting(counter.incrementAndGet(),String.format(template,
	 * name)); return sr.start(); }
	 */

	
	@RequestMapping("/change")
	public void change(@RequestBody String text) {
		if (text.equals("")){			
			text = gson.toJson(new MapState(1));
		}
		
		MapState ms = gson.fromJson(text, MapState.class);
		
		sr.init(ms.getState()); 
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestBody String text) throws IOException {		

		if (text == null || text.equals("")) {
			
			List<Preferece> prefereces = new ArrayList<Preferece>();
			prefereces.add(new Preferece("Temperature","EG","26","Celcius"));
			prefereces.add(new Preferece("Brightness","L","70","Lux"));
			prefereces.add(new Preferece("Humidty","L","65","Percent"));		
			
			UserInfo request = new UserInfo("20062124","Hong Kil Dong","231,326","Online meeting",prefereces);
			
			text = gson.toJson(new Request(request));
		}
		
		System.out.println("input : " + text);

		Request rq = gson.fromJson(text, Request.class);		
		
		String name = rq.getUser_info().getName();		
		
		String preference[] = new String[3];
		String logical[] = new String[3];
		int count = 0;		
		
		for (Preferece pre : rq.getUser_info().getPrefereces()) {
			preference[count] = pre.getDegree();
			logical[count] = pre.getLogical();
			count++;
		}

		return sr.start(name, preference[0], logical[0], preference[1],
				logical[1], preference[2], logical[2]);	
	}
}
