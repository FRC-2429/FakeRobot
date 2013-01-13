
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ethan_000
 */
public class RecordingCrio implements NamedSendable, ITableListener{

    ITable table;
    
    @Override
    public String getName() {
        return "RecordingThingy";
    }

    @Override
    public void initTable(ITable itable) {
       
       
       this.table = itable;
       if (table != null)
       {
           table.addTableListener(this);
           table.putString("FileStatus","NotChecked");
           table.putString("File","exampleFileName.txt");
           table.putString("Status", "waiting");
           
           
       }
    }

    @Override
    public ITable getTable() {
        return table;
    }

    @Override
    public String getSmartDashboardType() {
        return "RecordingType";
    }

    
    Random r = new Random();
    boolean checkFile(String file)
    {
       return r.nextBoolean(); 
    }
    
    
    
    @Override
    public void valueChanged(ITable itable, String string, Object o, boolean bln) {
        if (string.equals("Command"))
        {
            String typeOfCommand = (String) o;
            switch (typeOfCommand)
            {
                case "Stop":
                    table.putString("Status", "Waiting");
                    break;
                    
                case "Record":
                   table.putString("Status", "Recording");
                    break;
                    
                case "Play":
                   table.putString("Status", "Playing");
                   break; 
            }
        }
        else if (string.equals("File"))
        {
            
            String fileName = (String) o;
            
            if (checkFile(fileName))
            {
                table.putString("FileStatus", "Exists");
            }

            else
            {
                table.putString("FileStatus", "Does not exist");
            }
        }
    }
    
}
