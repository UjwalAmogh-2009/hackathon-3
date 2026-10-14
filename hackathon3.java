class Appointment {
    private String patientName;
    private String patientId;
    private String doctorName;
    private String appointmentType;
    private double consultationFee;
    private String status;

    public Appointment(String patientName, String patientId, String doctorName) {
        this(patientName, patientId, doctorName, "General", 500.00);
    }

    public Appointment(String patientName, String patientId, String doctorName, String appointmentType) {
        this(patientName, patientId, doctorName, appointmentType,
                appointmentType.equalsIgnoreCase("Specialist") ? 800.00 : 500.00);
    }

    public Appointment(String patientName, String patientId, String doctorName,
            String appointmentType, double consultationFee) {
        this.patientName = patientName.trim();
        this.patientId = patientId.trim();
        this.doctorName = doctorName.trim();
        this.appointmentType = appointmentType.trim();
        this.consultationFee = consultationFee;
        this.status = validate() ? "Confirmed" : "Rejected";
    }

    private boolean validate() {
        if (patientName.isEmpty() || doctorName.isEmpty())
            return false;
        if (patientId.length() < 2 || patientId.charAt(0) != 'P')
            return false;
        return consultationFee > 0;
    }

    // Compare patient names (case-insensitive) to verify records
    public boolean verifyPatient(String name) {
        return patientName.equalsIgnoreCase(name.trim());
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public String getStatus() {
        return status;
    }

    public void display() {
        System.out.println(String.format("%-18s : %s", "Patient Name", patientName.toUpperCase()));
        System.out.println(String.format("%-18s : %s", "Patient ID", patientId));
        System.out.println(String.format("%-18s : %s", "Doctor Name", doctorName.toUpperCase()));
        System.out.println(String.format("%-18s : %s", "Appointment Type", appointmentType.toUpperCase()));
        System.out.println(String.format("%-18s : Rs. %.2f", "Consultation Fee", consultationFee));
        System.out.println(String.format("%-18s : %s", "Appointment Status", status));
    }

    public void display(boolean showSeparator) {
        display();
        if (showSeparator)
            System.out.println("--------------------");
    }

    public static double calculateTotal(Appointment a) {
        return a.getStatus().equals("Confirmed") ? a.getConsultationFee() : 0;
    }

    public static double calculateTotal(Appointment[] list) {
        double total = 0;
        for (Appointment a : list)
            total += calculateTotal(a);
        return total;
    }
}

public class hackathon3 {
    public static void main(String[] args) {
        System.out.println("========== CLINIC APPOINTMENT SYSTEM ==========");

        Appointment a1 = new Appointment("Priya Sharma", "P101", "Dr. Kumar");
        Appointment a2 = new Appointment("Rahul Verma", "P102", "Dr. Meena", "Specialist", 800.00);

        Appointment[] list = { a1, a2 };
        for (Appointment a : list)
            a.display();

        System.out.println("---------- APPOINTMENT SUMMARY ----------");
        System.out.println("Total Appointments : " + list.length);
        System.out.println(String.format("Total Consultation Charges : Rs. %.2f", Appointment.calculateTotal(list)));
    }
}