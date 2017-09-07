package greenfield;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;

@FXMLController
public class GreenfieldControllerImpl implements GreenfieldController {
    private static Logger LOGGER = LoggerFactory.getLogger(GreenfieldControllerImpl.class);
   
    @Autowired
    private RecordRepository repository;
    
    @Override
    public void handleFileOpenAction(Event event) {
        // Playing around: Saving a record:
        Record newRecord = new Record();
        newRecord.setDatabundle("Something");
        newRecord.setBeginTs(new Timestamp(0));
        
        repository.save(newRecord);
        
        // Getting it back from the db:
        
        Iterable<Record> all = repository.findAll();
        
        all.forEach(r -> LOGGER.debug(r.toString()));
    }

}
