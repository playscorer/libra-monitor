package arbitrail.libramonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
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

/*	private Path getLogFile() {
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path rootPath = Paths.get(LOGDIRPATH);
			rootPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

			WatchKey key;
			if ((key = watchService.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					Path logFilePath = (Path) event.context();
					LOG.debug("Event kind:" + event.kind() + ". File changed : " + logFilePath + ".");
					return rootPath.resolve(logFilePath);
				}
				key.reset();
			}
		} catch (Exception e) {
			LOG.error("Unexpected Exception :", e);
		}
		return null;
	}*/

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String index() {
		//Path logPath = getLogFile();
		Path logPath = Paths.get(LOGDIRPATH).resolve("libra.html");
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(logPath, charset)) {
			return reader.lines().collect(Collectors.joining("\n"));
		} catch (IOException ioe) {
		    LOG.error("IOException : ", ioe);
		}
		return "<meta http-equiv=\"refresh\" content=\"" + 30 + "\"><h1>No Log file</h1>";
	}

}
