package cva.gson.request;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cva.gson.Response;
import cva.sim.ShowRoom;

@RestController
public class RequestController {

	Gson gson = new Gson();

	ShowRoom sr = new ShowRoom();
	BufferedReader in;

	@RequestMapping("/change")
	public String change(@RequestBody String text) {
		if (text.equals("")){			
			text = gson.toJson(new MapState(1));
		}
		
		MapState ms = gson.fromJson(text, MapState.class);
		
		sr.init(ms.getState());
		
		return sr.getCurrentRoom();
	}

	
	@RequestMapping("/view")
	public String view(@RequestParam(value="name", required=false, defaultValue="World") String name) {
		return sr.view(name);		
	}

	@RequestMapping("/greeting")
	public String greeting(@RequestBody String text) throws IOException {

		if (text.equals("")) {

			List<Preferece> prefereces = new ArrayList<Preferece>();
			prefereces.add(new Preferece("Temperature", "EG", "26", "Celcius"));
			prefereces.add(new Preferece("Brightness", "L", "70", "Lux"));
			prefereces.add(new Preferece("Humidty", "L", "65", "Percent"));

			UserInfo request = new UserInfo("20062124", "Park Yoeung",
					"231,326", "Online meeting", 14, 17, prefereces);

			text = gson.toJson(new Request(request));
		}

		System.out.println("input : " + text);

		Request rq = gson.fromJson(text, Request.class);

		String name = rq.getUser_info().getName();

		String preference[] = new String[3];
		String logical[] = new String[3];
		int count = 0;
		int start_time = rq.getUser_info().getStart_time()-1;
		int end_time = rq.getUser_info().getEnd_time()-1;

		for (Preferece pre : rq.getUser_info().getPrefereces()) {
			preference[count] = pre.getDegree();
			logical[count] = pre.getLogical();
			count++;
		}

		return sr.start(name, preference[0], logical[0], preference[1],
				logical[1], preference[2], logical[2], start_time, end_time);
	}
}
