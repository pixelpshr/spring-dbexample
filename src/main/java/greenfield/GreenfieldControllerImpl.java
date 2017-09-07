package greenfield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;

@FXMLController
public class GreenfieldControllerImpl implements GreenfieldController {
    private static Logger LOGGER = LoggerFactory.getLogger(GreenfieldControllerImpl.class);
    
    @Override
    public void handleFileOpenAction(Event event) {
        LOGGER.debug("FileOpen");
        
    }

}
