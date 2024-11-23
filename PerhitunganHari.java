import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class PerhitunganHari extends JFrame {
    private JComboBox<String> comboBulan;
    private JSpinner spinnerTahun;
    private JLabel labelHasil, labelHariPertama, labelHariTerakhir;
    private JButton tombolHitung;

    public PerhitunganHari() {
        setTitle("Aplikasi Perhitungan Hari");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        setLocationRelativeTo(null);

        // Panel Bulan
        JPanel panelBulan = new JPanel(new FlowLayout());
        panelBulan.add(new JLabel("Pilih Bulan: "));
        comboBulan = new JComboBox<>();
        for (Month month : Month.values()) {
            comboBulan.addItem(month.getDisplayName(TextStyle.FULL, Locale.getDefault()));
        }
        panelBulan.add(comboBulan);

        // Panel Tahun
        JPanel panelTahun = new JPanel(new FlowLayout());
        panelTahun.add(new JLabel("Masukkan Tahun: "));
        spinnerTahun = new JSpinner(new SpinnerNumberModel(2024, 1900, 2100, 1));
        panelTahun.add(spinnerTahun);

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        tombolHitung = new JButton("Hitung Hari");
        panelTombol.add(tombolHitung);

        // Panel Hasil
        JPanel panelHasil = new JPanel(new GridLayout(3, 1));
        labelHasil = new JLabel("Jumlah hari: ");
        labelHariPertama = new JLabel("Hari pertama: ");
        labelHariTerakhir = new JLabel("Hari terakhir: ");
        panelHasil.add(labelHasil);
        panelHasil.add(labelHariPertama);
        panelHasil.add(labelHariTerakhir);

        // Tambahkan komponen ke JFrame
        add(panelBulan);
        add(panelTahun);
        add(panelTombol);
        add(panelHasil);

        // Event Listener
        tombolHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungHari();
            }
        });

        setVisible(true);
    }

    private void hitungHari() {
        try {
            int bulan = comboBulan.getSelectedIndex() + 1;
            int tahun = (int) spinnerTahun.getValue();

            LocalDate tanggal = LocalDate.of(tahun, bulan, 1);
            int jumlahHari = tanggal.lengthOfMonth();
            String hariPertama = tanggal.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
            String hariTerakhir = tanggal.withDayOfMonth(jumlahHari).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());

            labelHasil.setText("Jumlah hari: " + jumlahHari);
            labelHariPertama.setText("Hari pertama: " + hariPertama);
            labelHariTerakhir.setText("Hari terakhir: " + hariTerakhir);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PerhitunganHari::new);
    }
}
