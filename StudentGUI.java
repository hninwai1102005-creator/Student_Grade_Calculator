
package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import Model.Student;

public class StudentGUI {

    private JFrame frame;

    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel statisticsPanel;
    private JPanel bottomPanel;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel marksLabel;
    private JLabel totalLabel;
    private JLabel highestLabel;
    private JLabel averageLabel;
    private JLabel lowestLabel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField marksField;

    private JButton calculateButton;
    private JButton clearButton;
    private JButton deleteButton;
    private JButton updateButton;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentGUI() {

        // 1. Create JFrame
        frame = new JFrame("Student Grade Calculator");
        frame.setSize(750, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // 2. Create Main Panel
        mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // 3. Create Input Panel
        inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        idLabel = new JLabel("Student ID:");
        nameLabel = new JLabel("Student Name:");
        marksLabel = new JLabel("Marks:");

        idField = new JTextField();
        nameField = new JTextField();
        marksField = new JTextField();

        inputPanel.add(idLabel);
        inputPanel.add(idField);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        inputPanel.add(marksLabel);
        inputPanel.add(marksField);

        // 4. Create Buttons
        buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 10, 10)
        );

        calculateButton = new JButton("Calculate");
        clearButton = new JButton("Clear");
        deleteButton=new JButton("Delete");
        updateButton=new JButton("Update");

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        // 5. Create Table
        String[] columns = {
            "Student ID",
            "Name",
            "Marks",
            "Grade",
            "Result"
        };

        tableModel = new DefaultTableModel(columns, 0);

        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(28);

        studentTable.setFont(new Font("Arial", Font.PLAIN, 13));

        studentTable.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13)
        );

        studentTable.getTableHeader().setBackground(
            new Color(41, 128, 185)
        );

        studentTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane =
            new JScrollPane(studentTable);

        // 6. Add Panels to Main Panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        

        // 7. Add Main Panel to JFrame
        frame.add(mainPanel);

        // 8. Calculate Button Action
        calculateButton.addActionListener(e -> calculateStudent());

        // 9. Clear Button Action
        clearButton.addActionListener(e -> clearFields());

        // 10. Show JFrame
        frame.setVisible(true);
        
        //11. Delete Button Action
        deleteButton.addActionListener(e -> deleteStudent());
        
        //12. Update Button Action
        updateButton.addActionListener(e -> updateStudent());
        
        
        
        studentTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                loadSelectedStudent();
            }

        });
        statisticsPanel=new JPanel(new GridLayout(1,4,10,10));
        totalLabel=new JLabel("Total: 0");
        averageLabel=new JLabel("Average: 0");
        highestLabel=new JLabel("Highest: 0");
        lowestLabel=new JLabel("Lowest: 0");
        
        
        statisticsPanel.add(totalLabel);
        statisticsPanel.add(averageLabel);
        statisticsPanel.add(highestLabel);
        statisticsPanel.add(lowestLabel);
        
        
        
        bottomPanel = new JPanel(new GridLayout(2, 1, 5, 10));

        bottomPanel.add(statisticsPanel);
        bottomPanel.add(buttonPanel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        
        mainPanel.setBackground(new Color(245, 247, 250));
        
        
        inputPanel.setBackground(Color.WHITE);

        inputPanel.setBorder(
            BorderFactory.createTitledBorder("Student Information")
        );
        
        inputPanel.setBackground(Color.WHITE);

        inputPanel.setBorder(
            BorderFactory.createTitledBorder("Student Information")
        );
        
        
     // Button Colors
        calculateButton.setBackground(new Color(41, 128, 185));
        calculateButton.setForeground(Color.WHITE);

        clearButton.setBackground(new Color(149, 165, 166));
        clearButton.setForeground(Color.WHITE);

        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);

        updateButton.setBackground(new Color(39, 174, 96));
        updateButton.setForeground(Color.WHITE);
        
        
        Font buttonFont = new Font("Arial", Font.BOLD, 13);

        calculateButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);

        calculateButton.setFocusPainted(false);
        clearButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);
        updateButton.setFocusPainted(false);
    }

    private void loadSelectedStudent() {
    	int selectedRow = studentTable.getSelectedRow();

        if (selectedRow != -1) {

            idField.setText(
                tableModel.getValueAt(selectedRow, 0).toString()
            );

            nameField.setText(
                tableModel.getValueAt(selectedRow, 1).toString()
            );

            marksField.setText(
                tableModel.getValueAt(selectedRow, 2).toString()
            );
        }
		
	}

	private void updateStudent() {
		int selectedRow = studentTable.getSelectedRow();

	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(
	            frame, "Please select a student!"
	        );
	        return;
	    }

	    try {

	        int id = Integer.parseInt(idField.getText());
	        String name = nameField.getText().trim();
	        double marks = Double.parseDouble(marksField.getText());

	        if (name.isEmpty()) {
	            JOptionPane.showMessageDialog(
	                frame, "Please enter student name!"
	            );
	            return;
	        }

	        Student student = new Student(id, name, marks);

	        tableModel.setValueAt(student.getId(), selectedRow, 0);
	        tableModel.setValueAt(student.getName(), selectedRow, 1);
	        tableModel.setValueAt(student.getMarks(), selectedRow, 2);
	        tableModel.setValueAt(student.getGrade(), selectedRow, 3);
	        tableModel.setValueAt(student.getResult(), selectedRow, 4);

	        JOptionPane.showMessageDialog(
	            frame, "Student updated successfully!"
	        );

	        clearFields();

	    } catch (NumberFormatException e) {

	        JOptionPane.showMessageDialog(
	            frame, "Please enter valid ID and Marks!"
	        );

	    } catch (IllegalArgumentException e) {

	        JOptionPane.showMessageDialog(
	            frame, e.getMessage()
	        );
	    }
	
	}

	private void deleteStudent() {
		int selectRow=studentTable.getSelectedRow();
		if(selectRow==-1) {
			JOptionPane.showMessageDialog(frame,"Please select a student");
			return;
		}
		tableModel.removeRow(selectRow);
		JOptionPane.showMessageDialog(frame,"Student deleted Successfully");
	}

	// Calculate Student Grade
    private void calculateStudent() {

        try {

            int id = Integer.parseInt(idField.getText());

            String name = nameField.getText().trim();

            double marks = Double.parseDouble(marksField.getText());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                    frame, "Please enter student name!"
                );
                return;
            }

            Student student = new Student(id, name, marks);

            String grade = student.getGrade();
            String result = student.getResult();

            // Add Student to JTable
            Object[] row = {
                student.getId(),
                student.getName(),
                student.getMarks(),
                grade,
                result
            };

            tableModel.addRow(row);

            // Show Result
            String message =
                "Student ID: " + student.getId()
                + "\nName: " + student.getName()
                + "\nMarks: " + student.getMarks()
                + "\nGrade: " + grade
                + "\nResult: " + result;

            JOptionPane.showMessageDialog(frame, message);

            clearFields();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                frame, "Please enter valid ID and Marks!"
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                frame, e.getMessage()
            );
        }
        updateStatistics();
        
    }

    // Clear Input Fields
    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        marksField.setText("");

        idField.requestFocusInWindow();
    }

private void updateStatistics() {

    int total = tableModel.getRowCount();

    if (total == 0) {
        totalLabel.setText("Total: 0");
        averageLabel.setText("Average: 0");
        highestLabel.setText("Highest: 0");
        lowestLabel.setText("Lowest: 0");
        return;
    }

    double sum = 0;
    double highest = Double.MIN_VALUE;
    double lowest = Double.MAX_VALUE;

    for (int i = 0; i < total; i++) {

        double marks = Double.parseDouble(
            tableModel.getValueAt(i, 2).toString()
        );

        sum += marks;

        if (marks > highest) {
            highest = marks;
        }

        if (marks < lowest) {
            lowest = marks;
        }
    }

    double average = sum / total;

    totalLabel.setText("Total: " + total);
    averageLabel.setText(
        String.format("Average: %.2f", average)
    );
    highestLabel.setText("Highest: " + highest);
    lowestLabel.setText("Lowest: " + lowest);
}

}
