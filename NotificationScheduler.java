import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationScheduler {
    private final List<Appointment> appointments;

    public NotificationScheduler(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void scheduleNotifications() {
        Timer timer = new Timer();
        for (Appointment appointment : appointments) {
            // Schedule notification 10 minutes before the appointment time
            long delay = appointment.getTime().minusMinutes(10)
                    .atZone(java.time.ZoneId.systemDefault())
                    .toInstant().toEpochMilli() - System.currentTimeMillis();

            if (delay > 0) {
                timer.schedule(new NotificationTask(appointment), delay);
            }
        }
    }

    static class NotificationTask extends TimerTask {
        private final Appointment appointment;

        public NotificationTask(Appointment appointment) {
            this.appointment = appointment;
        }

        @Override
        public void run() {
            SwingUtilities.invokeLater(() -> showNotification(appointment));
        }

        private void showNotification(Appointment appointment) {
            JOptionPane.showMessageDialog(null,
                    "You have an appointment at " + appointment.getTime().toLocalTime(),
                    "Appointment Reminder",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
