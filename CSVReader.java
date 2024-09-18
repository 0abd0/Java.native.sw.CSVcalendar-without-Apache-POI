import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Appointment> readAppointments(String filePath) {
        List<Appointment> appointments = new ArrayList<>();
        String line;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length >= 7) {
                    // Read date from the first column
                    LocalDateTime date = LocalDateTime.parse(columns[0], dateFormatter);
                    // Loop through the 6 appointments for each row
                    for (int i = 1; i <= 6; i++) {
                        String timeStr = columns[i].trim();
                        // Parse the time (in AM/PM format)
                        LocalDateTime appointmentTime = LocalDateTime.parse(columns[0] + " " + timeStr,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));
                        appointments.add(new Appointment(appointmentTime));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
