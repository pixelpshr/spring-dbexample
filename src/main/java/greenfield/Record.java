/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenfield;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.*;

/**
 *
 * @author tdecarlo
 */
@Entity
@Table(name = "Greenfield")
public class Record {
    private Long recordId;
    private Timestamp beginTs;
    private String databundle;
    
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq-gen")
    @SequenceGenerator(name="seq-gen", sequenceName="MY_SEQ_GEN", initialValue=1, allocationSize=12)
    @Column(name = "ID")
    public Long getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
    
    @Column(name = "begin_ts", nullable = false)
    public Timestamp getBeginTs() {
        return this.beginTs;
    }
    
    public void setBeginTs(Timestamp beginTs) {
        this.beginTs = beginTs;
    }
    
    @Column(name = "databundle", nullable = true)
    public String getDatabundle() {
        return this.databundle;
    }
    
    public void setDatabundle(String databundle) {
        this.databundle = databundle;
    }

    public String toString() {
    		SimpleDateFormat sdf = new SimpleDateFormat("uuuu-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();
		
		sb.append("[").append(recordId).append("]")
		.append("[").append(sdf.format(beginTs)).append("]")
		.append(" ").append(databundle);

		return sb.toString();
    }
}
