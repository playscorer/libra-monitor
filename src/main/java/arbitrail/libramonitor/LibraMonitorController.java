package arbitrail.libramonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LibraMonitorController {

	private final static Logger LOG = Logger.getLogger(LibraMonitorController.class);

	@Value("${logdirpath}")
	private String LOGDIRPATH;

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String index() {
		Path logPath = Paths.get(LOGDIRPATH).resolve("libra.html");
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(logPath, charset)) {
			return reader.lines().collect(Collectors.joining("\n"))
					+ "</table><br><button onclick=\"window.scrollTo(0,0)\">Top</button></body>";
		} catch (IOException ioe) {
		    LOG.error("IOException : ", ioe);
		}
		return "<meta http-equiv=\"refresh\" content=\"" + 30 + "\"><h1>No Log file</h1>";
	}
	
/*	@RequestMapping("/")
	public String index() {
		return "libra.html";
	}*/

}
