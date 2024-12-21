import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * ListPanel displays the list of computers based on state
 */
public class ListPanel  extends JPanel {
    private final Database database;
    private final State state;
    private final JTable table;
    private final DefaultTableModel tableModel;

    /**
     * Constructor
     * @param database the database
     * @param state the state
     * @param callback the callback
     */
    public ListPanel(Database database, State state, ListPanelCallback callback){
        this.database = database;
        this.state = state;
        this.setLayout(new BorderLayout());
        // set the table model to have the columns: Category, Type, ID, Brand, CPU Family, Price($)
        this.tableModel = new DefaultTableModel(new String[] {"Category", "Type", "ID", "Brand", "CPU Family", "Price($)"}, 0);
        // the table model is set to the table
        this.table = new JTable(tableModel);
        // adding a listener when the user select a computer
        this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // get the row that is selected
                int selectedRow = table.getSelectedRow();
                // make sure the row is valid
                if (selectedRow >= 0){
                    // get the id of the computer
                    String id = (String) tableModel.getValueAt(selectedRow, 2);
                    // find the computer in the database
                    Computer computer = database.getComputerById(id);
                    // set the selected computer in the state
                    state.setSelectedComputer(computer);
                    // call callback to parent that selection has changed
                    callback.selectionChange();
                    // otherwise set the selected computer to null
                } else {
                    // remember that the selected computer is null (nothing is selected)
                    state.setSelectedComputer(null);
                    // then call the callback to parent that selection has changed
                    callback.selectionChange();
                }
            }
        });

        // create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        // add it to the center of the panel
        this.add(scrollPane, BorderLayout.CENTER);
        // populate the table which adds all the computers to the table
        populateTable();
    }

    /**
     * method to populate the table with computers
     */
    public void populateTable(){
        // clear previous data
        tableModel.setRowCount(0);

        // for each computer in the database that matches the category and type add it to the table
        for (Computer computer : database.getComputers(state.getSelectedCategory(), state.getSelectedType())){
            String[] row = new String[]{
                    computer.getCategory(),
                    computer.getType(),
                    computer.getId(),
                    computer.getBrand(),
                    computer.getCpuFamily(),
                    // turn the price into a string with 1 decimal place
                    String.format("%.1f", computer.getPrice())
            };
            // add the row to the table
            tableModel.addRow(row);
        }
    }
}
