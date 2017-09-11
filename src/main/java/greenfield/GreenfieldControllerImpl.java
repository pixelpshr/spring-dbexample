package greenfield;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

@SuppressWarnings("restriction")
@FXMLController
public class GreenfieldControllerImpl implements GreenfieldController {
    private static Logger LOGGER = LoggerFactory.getLogger(GreenfieldControllerImpl.class);
   
    @Autowired
    private RecordRepository repository;

    @FXML
    private Label filenameLabel;

    @FXML
    private TextArea sourceTextArea;
    
    @FXML
    private ListView<String> sourceRecordList;
        
    
    public void handleFileOpenActionReskenet(Event event) {
        // Playing around: Saving a record:
        Record newRecord = new Record();
        newRecord.setDatabundle("Something");
        newRecord.setBeginTs(new Timestamp(0));
        
        repository.save(newRecord);
        
        // Getting it back from the db:
        
        Iterable<Record> all = repository.findAll();
        
        all.forEach(r -> LOGGER.debug(r.toString()));
    }

	@Override
    public void handleFileOpenAction(Event event) {
        
        FileChooser fc = new FileChooser();
        fc.setTitle("Get Text");
        fc.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Comma Separated Value Files", "*.csv"),
                new ExtensionFilter("All Files", "*.*"));
        File phil = fc.showOpenDialog(null);
        if (phil != null) {
        		filenameLabel.setText(phil.getName());
            try {
                Scanner scanner = new Scanner(phil).useDelimiter("\\n");
                while (scanner.hasNext()) {
                    String content = scanner.next();
                    sourceTextArea.appendText(content + "\n");
                    //databaseLoader.createData(content);
                    
                    repository.save(createDataRecord(content));
                    
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
        		filenameLabel.setText("null");
        }
        
        // Getting it back from the db:
        
        Iterable<Record> all = repository.findAll();

        ObservableList<String> records = 
            FXCollections.observableArrayList();
        
        all.forEach(r -> records.add(r.toString()));
        
        sourceRecordList.setItems(records);
        
        all.forEach(r -> LOGGER.debug(r.toString()));
        
    }

    public Record createDataRecord(String newRecord) {
        Record record = new Record();

        // parse the incoming string
        String timestampStr = newRecord.substring(0, newRecord.indexOf(" ", 13)).trim().replace("T", " ");
        String databundle = newRecord.substring(timestampStr.length()).trim();
        
        Timestamp timestamp = Timestamp.valueOf(timestampStr);

        System.out.println("timestamp: " + timestampStr);
        
        record.setBeginTs(timestamp);
        record.setDatabundle(databundle);
        return record;

    }

}
