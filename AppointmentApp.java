import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AppointmentApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Appointment Scheduler");
            frame.setSize(400, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JLabel label = new JLabel("Appointment notifications will pop up!");
            frame.add(label, BorderLayout.CENTER);

            frame.setVisible(true);

            // Path to your CSV file
            String csvFilePath = "appointments.csv";
            List<Appointment> appointments = CSVReader.readAppointments(csvFilePath);

            // Schedule notifications
            NotificationScheduler scheduler = new NotificationScheduler(appointments);
            scheduler.scheduleNotifications();
        });
    }
}
