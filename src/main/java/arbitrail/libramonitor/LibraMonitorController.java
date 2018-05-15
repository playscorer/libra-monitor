package arbitrail.libramonitor;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LibraMonitorController {
	
	private final static Logger LOG = Logger.getLogger(LibraMonitorController.class);
	
	private String destLogFile = "src/main/resources/static/libra.html";
	
	@Value("${logdirpath}")
	private String LOGDIRPATH;
	
	public void refreshLogFile() {
		try {
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path rootPath = Paths.get(LOGDIRPATH);
			rootPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

			WatchKey key;
			if ((key = watchService.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					Path logFilePath = (Path) event.context();
					LOG.debug("Event kind:" + event.kind() + ". File changed : " + logFilePath + ".");
					Path logFileAbsolutePath = rootPath.resolve(logFilePath);
					Path destLogFilePath = Paths.get(destLogFile);
					Files.copy(logFileAbsolutePath, destLogFilePath);
				}
				key.reset();
			}
		} catch (Exception e) {
			LOG.error("Unexpected Exception :", e);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
		refreshLogFile();
		return "redirect:/libra.html";
    }
	
}
