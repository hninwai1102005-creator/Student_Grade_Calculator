package app;
import Model.Student;
import javax.swing.SwingUtilities;
import View.StudentGUI;

public class main {

	public static void main (String[] args) {
		SwingUtilities.invokeLater(() -> {
            new StudentGUI();
        });

	}

}
