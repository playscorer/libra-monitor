package arbitrail.libramonitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LibraMonitorController {

	@RequestMapping("/")
	public String index() {
		return "libra.html";
	}

}
